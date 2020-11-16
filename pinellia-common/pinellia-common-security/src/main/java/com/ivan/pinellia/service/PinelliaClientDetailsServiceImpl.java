package com.ivan.pinellia.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * <p></p>
 *
 * @author chenyf
 * @className PinelliaClientDetailsServiceImpl
 * @since 2020/11/11 10:34
 */

@Component
public class PinelliaClientDetailsServiceImpl extends JdbcClientDetailsService {
    public PinelliaClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }
}
