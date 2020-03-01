package com.ivan.pinellia.secure.constant;

/**
 * <p>认证模块常量类</p>
 *
 * @author user
 * @className SecureConstant
 * @since 2020/3/1 12:45 下午
 */
public interface SecureConstant {
    /**
     * 认证请求头
     */
    String BASIC_HEADER_KEY = "Authorization";

    /**
     * 认证请求头前缀
     */
    String BASIC_HEADER_PREFIX = "Basic ";

    /**
     * 认证请求头前缀
     */
    String BASIC_HEADER_PREFIX_EXT = "Basic%20";

    /**
     * pinellia_user表字段
     */
    String USER_FIELDS = "user_id, account, `password`, `name`, real_name, `status` ";

    /**
     * pinellia_client查询语句
     */
    String BASE_STATEMENT = "select " + USER_FIELDS + " from pinellia_user";

    /**
     * pinellia_client查询排序
     */
    String DEFAULT_FIND_STATEMENT = BASE_STATEMENT + " order by user_id";

    /**
     * 查询client_id
     */
    String DEFAULT_SELECT_STATEMENT = BASE_STATEMENT + " where is_deleted = 0 and account = ?";
}
