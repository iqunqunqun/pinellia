package com.ivan.pinellia.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p></p>
 *
 * @author chenyf
 * @version 1.0
 * @className ActivitiConfig
 * @date 2019/10/11 13:45
 */


public class ActivitiConfig {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(transactionManager);
        return spec;
    }
}
