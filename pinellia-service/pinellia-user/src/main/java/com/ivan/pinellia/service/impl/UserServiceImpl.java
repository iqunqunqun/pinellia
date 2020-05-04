package com.ivan.pinellia.service.impl;

import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mapper.UserMapper;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
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
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

}
