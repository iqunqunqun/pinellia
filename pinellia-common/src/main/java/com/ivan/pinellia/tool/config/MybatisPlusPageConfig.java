package com.ivan.pinellia.tool.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 分页助手
 * @create 2019-12-26 10:02 下午
 */

@EnableTransactionManagement
@Configuration
@MapperScan("com.ivan.pinellia.mapper*")
public class MybatisPlusPageConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
         paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }
}
