package com.ivan.pinellia.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.ivan.pinellia.dto.UserDTO;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mapper.UserMapper;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import com.ivan.pinellia.vo.UserVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-05-04
 */
@Service
@DS(value = "master")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Override

    public boolean saveUser(UserDTO userDTO) {

        User user = new User();
        BeanUtil.copyProperties(userDTO, user);

        if (StrUtil.isNotBlank(userDTO.getPassword())) {
            user.setPassword(ENCODER.encode(userDTO.getPassword()));
        }


        return this.save(user);
    }

    @Override
    public User detail(Integer id) {

        return this.getById(id);
    }
}
