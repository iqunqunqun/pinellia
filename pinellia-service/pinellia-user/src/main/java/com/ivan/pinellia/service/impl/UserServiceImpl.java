package com.ivan.pinellia.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mapper.UserMapper;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.utils.DigestUtil;
import com.ivan.pinellia.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2019-12-29
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getUserDetailById(Integer uid) {
        return this.getById(uid);
    }

    @Override
    public boolean submit(User user) {
        if (StrUtil.isNotBlank(user.getPassword())) {
            user.setPassword(DigestUtil.encrypt(user.getPassword()));
        }
        Integer cnt = baseMapper.selectCount(Wrappers.<User>query().lambda().eq(User::getName, user.getName()));
        if (cnt > 0) {
            throw new ApiException("当前用户已存在!");
        }
        return saveOrUpdate(user);
    }

    @Override
    public boolean grant(String userIds, String roleIds) {
        User user = new User();
        user.setRoleId(roleIds);
        List<Integer> integers = Func.toIntList(userIds);
        return this.update(user, Wrappers.<User>update().lambda().in(User::getUserId, integers));
    }

    @Override
    public List<String> getRoleName(String roleIds) {
        return baseMapper.getRoleName(StrUtil.splitToArray(roleIds, ','));
    }

    @Override
    public List<String> getDeptName(String deptIds) {
        return baseMapper.getDeptName(StrUtil.splitToArray(deptIds, ','));
    }


}
