package com.ivan.pinellia.service;

import com.ivan.pinellia.dto.UserDTO;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mybatis.base.BaseService;
import com.ivan.pinellia.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ivan
 * @since 2020-05-04
 */
public interface IUserService extends BaseService<User> {

    /**
     * 保存员工
     * @param userDTO
     * @return
     */
    boolean saveUser(UserDTO userDTO);

    /**
     * 查询员工详情
     * @param id
     * @return
     */
    User detail(Integer id);
}
