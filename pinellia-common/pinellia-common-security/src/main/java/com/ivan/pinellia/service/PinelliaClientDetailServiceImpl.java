package com.ivan.pinellia.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.oauth2.common.util.DefaultJdbcListFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * <p></p>
 *
 * @author ivan
 * @className PinelliaClientDetailServiceImpl
 * @since 2020/11/12 21:46
 */

@Component
public class PinelliaClientDetailServiceImpl extends JdbcClientDetailsService {

    public PinelliaClientDetailServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    @SneakyThrows
    @DS("config")
    public ClientDetails loadClientByClientId(String clientId)  {
        return super.loadClientByClientId(clientId);
    }

}
