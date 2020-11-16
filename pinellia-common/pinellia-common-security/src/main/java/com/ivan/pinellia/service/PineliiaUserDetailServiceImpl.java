package com.ivan.pinellia.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.feign.IUserClient;
import com.ivan.pinellia.vo.UserInfo;
import com.ivan.pinellia.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

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
        UserInfo userInfo = userClient.userInfo(username).getData();

        if (ObjectUtil.isNotNull(userInfo)) {
            return getUserDetails(userInfo);
        }

        throw new UsernameNotFoundException("用户不存在");
    }

    /**
     * 组装登录用户信息
     * @param userInfo
     * @return
     */
    private PinelliaUser getUserDetails(UserInfo userInfo) {

        User user = userInfo.getUser();

        Set<String> set = new HashSet<>();

        CollectionUtil.addAll(set, userInfo.getPermissions());
        CollectionUtil.addAll(set, userInfo.getRoles());

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(set.toArray(new String[0]));

        return new PinelliaUser(user.getUserId(), user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }


}
