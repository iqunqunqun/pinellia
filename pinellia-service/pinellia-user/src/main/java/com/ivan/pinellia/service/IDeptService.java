package com.ivan.pinellia.service;

import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mybatis.base.BaseService;
import com.ivan.pinellia.vo.DeptVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface IDeptService extends BaseService<Dept> {

    /**
     * 树形结构
     *
     * @return
     */
    List<DeptVO> tree();

    /**
     * <p>获取部门组织架构</p>
     *
     * @return com.ivan.pinellia.vo.DeptVO
     * @author ivan chen
     * @since 2020/2/15 5:58 下午
     */
    List<DeptVO> organization();

    /**
     *
     *
     * @return
     * @author ivan chen
     * @since 2020/2/15 9:24 下午
     */
    List<User> allDept(Integer deptId);

    /**
     *
     * 導出部門Excel
     * @date 2020/2/22 12:41 下午
     * @param response:
     * @return void
     * @author chenyifan
     */
    void exportExcel(HttpServletResponse response);

    /**
     *
     * 导入Excel
     * @date 2020/2/22 5:32 下午
     * @param file:
     * @return void
     * @author chenyifan
     */
    void importExcel(MultipartFile file);
}
