package com.ivan.pinellia.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author chenyf
 * @className ActivitiConfiguration
 * @since 2020/2/26 16:56
 */
@Configuration
@Slf4j
public class ActivitiConfiguration {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Autowired
    private UUIDGenerator uuidGenerator;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(){
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(platformTransactionManager);
        spec.setDatabaseSchemaUpdate("true");
        // 设置自动创建表
        spec.setDatabaseSchemaUpdate("true");
        // 在创建引擎对象的时候自动创建表
        spec.buildProcessEngine();
        Resource[] resources = null;
        // 启动自动部署流程
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:bpmn/*.bpmn");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        spec.setDeploymentResources(resources);
        return spec;
    }



    @Bean
    public ProcessEngineConfigurationImpl processEngineConfigurationImpl(ProcessEngineConfigurationImpl processEngineConfigurationImpl){
        //设置ProcessEngineConfigurationImpl里的uuidGenerator
        processEngineConfigurationImpl.setIdGenerator(uuidGenerator);
        //设置DbSqlSessionFactory的uuidGenerator，否则流程id，任务id，实例id依然是用DbIdGenerator生成
        processEngineConfigurationImpl.getDbSqlSessionFactory().setIdGenerator(uuidGenerator);

        return processEngineConfigurationImpl;
    }


    @Bean
    public ProcessEngineFactoryBean processEngine(){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return processEngineFactoryBean;
    }


    @Bean
    public RepositoryService repositoryService() throws Exception{
        return processEngine().getObject().getRepositoryService();
    }
    @Bean
    public RuntimeService runtimeService() throws Exception{
        return processEngine().getObject().getRuntimeService();
    }
    @Bean
    public TaskService taskService() throws Exception{
        return processEngine().getObject().getTaskService();
    }
    @Bean
    public HistoryService historyService() throws Exception{
        return processEngine().getObject().getHistoryService();
    }

    @Bean
    public ManagementService managementService() throws Exception {
        return processEngine().getObject().getManagementService();
    }

}

