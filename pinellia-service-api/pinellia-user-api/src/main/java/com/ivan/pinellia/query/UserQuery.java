package com.ivan.pinellia.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>用户查询</p>
 *
 * @author user
 * @className UserQuery
 * @since 2020/3/9 9:23 下午
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
public class UserQuery {
    /**
     * 主键
     */
    private Integer userId;

    /**
     * 账号
     */
    private String account;
}
