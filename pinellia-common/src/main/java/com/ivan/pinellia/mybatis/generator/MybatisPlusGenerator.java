package com.ivan.pinellia.mybatis.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 代码生成器
 * @create 2019-12-26 9:50 下午
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("/Users/user/Documents/OA内部服务/chenyf/mybatis-plus-generator");
        gc.setAuthor("ivan");
        gc.setOpen(false);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.100.93.25:3306/pinellia?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("aini1314liliqun");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.ivan.pinellia");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 必须设置
        mpg.setCfg(cfg);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntityBuilderModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);

        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("create_time, update_time, create_by, update_by, is_deleted");
        // 公共父类
        strategy.setSuperEntityClass("com.ivan.pinellia.mybatis.base.BaseEntity");
        strategy.setSuperServiceClass("com.ivan.pinellia.mybatis.base.BaseService");
        strategy.setSuperServiceImplClass("com.ivan.pinellia.mybatis.base.BaseServiceImpl");
        strategy.setInclude(new String[]{"pinellia_dept"});
//        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("pinellia_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
