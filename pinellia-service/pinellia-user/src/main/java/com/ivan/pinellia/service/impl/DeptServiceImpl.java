package com.ivan.pinellia.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.excel.DeptExcel;
import com.ivan.pinellia.listener.DeptExcelListener;
import com.ivan.pinellia.mapper.DeptMapper;
import com.ivan.pinellia.service.IDeptService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.tool.excel.ExcelDataListener;
import com.ivan.pinellia.tool.node.ForestNodeMerger;
import com.ivan.pinellia.tool.utils.BeanUtil;
import com.ivan.pinellia.tool.utils.ExcelUtil;
import com.ivan.pinellia.vo.DeptVO;
import com.ivan.pinellia.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {


    @Autowired
    private IUserService userService;


    @Override
    public List<DeptVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }

    @Override
    public List<DeptVO> organization() {
        List<DeptVO> tree = baseMapper.tree();
        tree.forEach(deptVO -> {
            int headquarterPeople = this.userService.count(Wrappers.<User>lambdaQuery().eq(User::getDeptId, deptVO.getDeptId()));
            int otherPeople = 0;
            deptVO.setHeadquarterPeople(headquarterPeople);
            List<Dept> depts = Lists.newArrayList();

            List<Dept> childDeptList = this.selectChildDept(depts, deptVO.getId());
            List<Integer> collect = childDeptList.stream().map(Dept::getDeptId).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(collect)) {
                otherPeople = this.userService.count(Wrappers.<User>lambdaQuery().in(User::getDeptId, collect));
            }

            deptVO.setOtherPeople(otherPeople);

            deptVO.setTotalPeople(headquarterPeople + otherPeople);

        });

        return ForestNodeMerger.merge(tree);
    }


    @Override
    public List<User> allDept(Integer deptId) {
        List<Dept> list = Lists.newArrayList();
        List<Dept> depts = this.selectChildDept(list, deptId);
        List<Integer> deptIdCollect = Lists.newArrayList();
        deptIdCollect.add(deptId);
        if (CollectionUtil.isNotEmpty(depts)) {
            List<Integer> collect = depts.stream().map(Dept::getDeptId).collect(Collectors.toList());
            deptIdCollect.addAll(collect);
        }
        return this.userService.list(Wrappers.<User>lambdaQuery().in(User::getDeptId, deptIdCollect));

    }


    @Override
    public void exportExcel(HttpServletResponse response) {
        List<DeptExcel> list = Lists.newArrayList();
        List<Dept> deptList = this.list();
        deptList.forEach(dept -> {
            DeptExcel excel = BeanUtil.copy(dept, DeptExcel.class);
            list.add(excel);
        });
        ExcelUtil<DeptExcel> excel = new ExcelUtil<>("excel", DeptExcel.class, list);
        excel.write(response, excel);
    }

    @Override
    public void importExcel(MultipartFile file) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        ExcelUtil<DeptExcel> excelUtil = new ExcelUtil<DeptExcel>(DeptExcel.class);
        List<DeptExcel> excelList = excelUtil.read(file, excelUtil, new ExcelDataListener<>());
        ArrayList<Dept> deptList = Lists.newArrayList();
        excelList.forEach(deptExcel -> {
            Dept dept = BeanUtil.copy(deptExcel, Dept.class);
            deptList.add(dept);
        });
        this.saveBatch(deptList);
    }


    /**
     * @author chenyifan
     * @description TODO
     * @date 2020/2/16 10:43 上午 
     * @param depts: 	
     * @param id:
     * @return java.util.List<com.ivan.pinellia.entity.Dept>
     */
    private List<Dept> selectChildDept(List<Dept> depts, Integer id) {
        List<Dept> list = this.list(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId, id));
        list.forEach(dept -> {
            depts.add(dept);
            this.selectChildDept(depts, dept.getDeptId());
        });
        return depts;
    }

}
