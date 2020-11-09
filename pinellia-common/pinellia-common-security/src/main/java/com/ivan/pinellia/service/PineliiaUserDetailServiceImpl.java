package com.ivan.pinellia.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ivan.pinellia.feign.IUserClient;
import com.ivan.pinellia.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p></p>
 *
 * @author ivan
 * @className PineliiaUserDetailServiceImpl
 * @since 2020/11/9 22:23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PineliiaUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserVO userVO = userClient.userInfo(username).getData();

        if (ObjectUtil.isNotNull(userVO)) {
            return getUserDetails(userVO);
        }

        throw new UsernameNotFoundException("用户不存在");
    }

    /**
     * 组装登录用户信息
     * @param userVO
     * @return
     */
    private PinelliaUser getUserDetails(UserVO userVO) {

        Collection<? extends GrantedAuthority> noAuthorities = AuthorityUtils.NO_AUTHORITIES;

        return new PinelliaUser(userVO.getUserId(), userVO.getUsername(), userVO.getPassword(), StrUtil.equals(userVO.getLockFlag(), "0"), true, true, true, noAuthorities);
    }


}
