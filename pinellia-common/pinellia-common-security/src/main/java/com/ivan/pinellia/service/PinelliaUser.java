package com.ivan.pinellia.service;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p></p>
 *
 * @author ivan
 * @className PinelliaUser
 * @since 2020/11/9 22:03
 */
@Getter
@Setter
public class PinelliaUser extends User {

    @ApiModelProperty("用户ID")
    private Integer userId;

    public PinelliaUser(Integer userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }
}
