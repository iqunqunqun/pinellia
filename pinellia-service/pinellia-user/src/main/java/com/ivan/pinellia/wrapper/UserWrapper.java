package com.ivan.pinellia.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mybatis.support.BaseEntityWrapper;
import com.ivan.pinellia.vo.UserVO;

/**
 * <p></p>
 *
 * @author ivan
 * @className UserWrapper
 * @since 2020/7/23 22:46
 */
public class UserWrapper extends BaseEntityWrapper<User, UserVO> {

    public static UserWrapper build() {
        return new UserWrapper();
    }

    @Override
    public UserVO entityVO(User entity) {
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(entity, userVO);
        return userVO;
    }
}
