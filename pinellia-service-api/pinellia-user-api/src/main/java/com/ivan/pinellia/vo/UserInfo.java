package com.ivan.pinellia.vo;

import com.ivan.pinellia.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p></p>
 *
 * @author ivan
 * @className UserInfo
 * @since 2020/11/15 23:51
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo {

    private User user;

    private String[] roles;

    private String[] permissions;

}
