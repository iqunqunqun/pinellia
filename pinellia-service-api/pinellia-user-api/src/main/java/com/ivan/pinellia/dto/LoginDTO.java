package com.ivan.pinellia.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * <p>登录数据传输对象</p>
 *
 * @author user
 * @className LoginDTO
 * @since 2020/3/1 9:57 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginDTO {
    /**
     * 用户名
     */
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @NotEmpty
    private String password;
}
