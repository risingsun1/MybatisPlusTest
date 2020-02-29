package com.jtexplorer.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis Plus 自动生成 *Mapper.xml 、*.java 、 *Mapper.java 、*Service.java 、 *ServiceImpl.java 、*Controller.java
 * 注意需要jdk8
 */
@Slf4j
public class MybatisPlusAutoTest {

    //包名
    final static String packagePath = "com.jtexplorer";
    //文件生成的作者
    final static String authorName = "xu.wang";
    //数据库连接
    final static String jdbcUrl = "jdbc:mysql://localhost:3306/testTable?useUnicode=true&useSSL=false&characterEncoding=utf8";
    //数据库驱动
    final static String jdbcDriver = "com.mysql.jdbc.Driver";
    //数据库账号
    final static String jdbcAccount = "root";
    //数据库密码
    final static String jdbcPassword = "";

    //controller的自定义模板
    final static String controllerTemplate = "template/controller4jt.java";

    @Test
    public void generationTest(){

        //数据库表名的列表，需要用英文逗号','隔开。
        String tables = "article,exam,user,role";
        automaticGeneration(tables);
    }

    /**
     * 自动生成
     * @param tables 数据库表名的列表，需要用英文逗号','隔开。
     */
    public void automaticGeneration(String tables){

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(authorName);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(jdbcUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(jdbcDriver);
        dsc.setUsername(jdbcAccount);
        dsc.setPassword(jdbcPassword);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent(packagePath);
//        pc.setController("controller");
        mpg.setPackageInfo(pc);

// 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/sqlmap/" + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

         /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//         templateConfig.setEntity("templates/entity.java");
//         templateConfig.setService("templates/service.java");
//         templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController(controllerTemplate);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //下划线改驼峰式命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude((tables).split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }

}
