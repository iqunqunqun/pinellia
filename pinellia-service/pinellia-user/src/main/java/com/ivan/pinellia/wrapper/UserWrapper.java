package com.ivan.pinellia.wrapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mybatis.support.BaseEntityWrapper;
import com.ivan.pinellia.service.IDictService;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.tool.utils.SpringUtil;
import com.ivan.pinellia.vo.UserVO;


import java.util.List;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 包装类
 * @create 2020-01-05 3:14 下午
 */

public class UserWrapper extends BaseEntityWrapper<User, UserVO> {

    private static IUserService userService;

    private static IDictService dictService;

    static {
        userService = SpringUtil.getBean(IUserService.class);
        dictService = SpringUtil.getBean(IDictService.class);
    }

    public static UserWrapper build() {
        return new UserWrapper();
    }

    @Override
    public UserVO entityVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        List<String> roleName = userService.getRoleName(user.getRoleId());
        List<String> deptName = userService.getDeptName(user.getDeptId());
        userVO.setRoleName(StrUtil.join(",", roleName));
        userVO.setDeptName(StrUtil.join(",", deptName));
        String dict = dictService.getValue("sex", user.getSex());
        userVO.setSexName(dict);
        return userVO;
    }
}