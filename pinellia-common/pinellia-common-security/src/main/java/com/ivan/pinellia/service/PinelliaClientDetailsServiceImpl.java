package com.ivan.pinellia.service;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * <p></p>
 *
 * @author chenyf
 * @className PinelliaClientDetailsServiceImpl
 * @since 2020/11/11 10:34
 */
public class PinelliaClientDetailsServiceImpl extends JdbcClientDetailsService {
    public PinelliaClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }
}
