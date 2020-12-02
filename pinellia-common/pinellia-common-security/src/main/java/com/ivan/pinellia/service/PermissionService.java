package com.ivan.pinellia.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <p>接口权限校验</p>
 *
 * @author ivan
 * @className PermissionService
 * @since 2020/11/15 23:38
 */
@Slf4j
@Component("auth")
public class PermissionService {

    public boolean hasAuth(String permission) {

        if (StrUtil.isBlank(permission)) {
            return false;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (ObjectUtil.isNotNull(authentication) || CollectionUtil.isEmpty(authentication.getAuthorities())) {

            log.info("*****************Authority Scope*****************");
            log.info("[所需权限] - {}", permission);
            log.info("[当前权限] - {}", authentication.getAuthorities().toString());
            log.info("*****************Authority Scope*****************");

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            return authorities.stream().anyMatch(p -> p.getAuthority().equals(permission));
        }
        return false;
    }
}
