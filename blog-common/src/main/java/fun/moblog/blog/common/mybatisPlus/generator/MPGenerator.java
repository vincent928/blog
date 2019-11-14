package fun.moblog.blog.common.mybatisPlus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XuJing
 * @Date: 2019/4/2 17:34
 */
@Slf4j
public class MPGenerator {

    /**
     * @param condition
     */
    public static void generator(GeneratorCondition condition) {
        if (null == condition) {
            log.error("请传入正确的参数");
            return;
        }
        String projectPath;
        if (condition.getIsMultiModule()) {
            if (StringUtils.isBlank(condition.getModelName())) {
                log.error("请输入模块名");
                return;
            }
            File file = new File(condition.getModelName());
            projectPath = file.getAbsolutePath();
        } else {
            projectPath = System.getProperty("user.dir");
        }

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //生成文件的输出目录 判断系统
        String osName = System.getProperty("os.name");
        if (osName != null && osName.contains("Mac")) {
            globalConfig.setOutputDir(projectPath + "/src/main/java");
        } else {
            globalConfig.setOutputDir(projectPath + "\\src\\main\\java");
        }
        globalConfig.setFileOverride(condition.getIsFileOverride());// 是否覆盖文件
        globalConfig.setActiveRecord(false);// 开启 activeRecord 模式
        globalConfig.setSwagger2(false);
        globalConfig.setEnableCache(false);// XML 二级缓存
        globalConfig.setBaseResultMap(true);// XML ResultMap
        globalConfig.setBaseColumnList(true);// XML columList
        globalConfig.setOpen(false);
        //.setKotlin(true) 是否生成 kotlin 代码
        if (StringUtils.isBlank(condition.getAuthor())) {
            log.error("请输入作者信息");
            return;
        }
        globalConfig.setAuthor(condition.getAuthor());
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        autoGenerator.setGlobalConfig(globalConfig);

        if (StringUtils.isBlank(condition.getUsername())) {
            log.error("请配置数据库用户名");
            return;
        }
        if (StringUtils.isBlank(condition.getPassword())) {
            log.error("请配置数据库密码");
            return;
        }
        if (StringUtils.isBlank(condition.getUrl())) {
            log.error("请配置数据库url");
            return;
        }

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);// 数据库类型
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(condition.getUsername());
        dataSourceConfig.setPassword(condition.getPassword());
        dataSourceConfig.setUrl(condition.getUrl());
        autoGenerator.setDataSource(dataSourceConfig);

        if (null == condition.getTableName() || condition.getTableName().length == 0) {
            log.error("请填写要生成的表");
            return;
        }
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // .setCapitalMode(true)// 全局大写命名
        // .setDbColumnUnderline(true)//全局下划线命名
        strategyConfig.setTablePrefix(condition.getTablePre());// 此处可以修改为您的表前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategyConfig.setInclude(condition.getTableName());// 需要生成的表
        // .setExclude(new String[]{"test"}) // 排除生成的表
        // 自定义 mapper 父类
        strategyConfig.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // 自定义 controller 父类
        //strategyConfig.setSuperControllerClass("cn.com.citydo.starter.web.controller.BaseController");
        //自定义entity父类
        if (StringUtils.isNoneBlank(condition.getBaseEntity())) {
            //父类
            String superEntityClass = null;
            // 自定义实体，公共字段
            String[] superEntityColumns = null;
            switch (condition.getBaseEntity()) {
                case "date":
                    superEntityClass = "cn.com.citydo.starter.mybatisPlus.entity.DateEntity";
                    superEntityColumns = new String[]{"create_time", "update_time"};
                    break;
                case "logicDel":
                    superEntityClass = "cn.com.citydo.starter.mybatisPlus.entity.LogicDelEntity";
                    superEntityColumns = new String[]{"is_del"};
                    break;
                case "basic":
                    superEntityClass = "cn.com.citydo.starter.mybatisPlus.entity.BasicEntity";
                    superEntityColumns = new String[]{"create_time", "update_time", "is_del"};
                    break;
                case "self":
                    if (StringUtils.isBlank(condition.getSelfBaseEntityPath()) || null == condition.getSelfBaseEntityCommon() || condition.getSelfBaseEntityCommon().length == 0) {
                        log.error("请填写自定义父类全路径和公共字段数组");
                        return;
                    }
                    superEntityClass = condition.getSelfBaseEntityPath();
                    superEntityColumns = condition.getSelfBaseEntityCommon();
                    break;
                default:
                    break;
            }
            if (null != superEntityClass && null != superEntityColumns) {
                strategyConfig.setSuperEntityClass(superEntityClass);
                strategyConfig.setSuperEntityColumns(superEntityColumns);
            }
        }
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // .setEntityColumnConstant(true)
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // .setEntityBuilderModel(true)
        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
        strategyConfig.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀处理
        // .setEntityBooleanColumnRemoveIsPrefix(true)
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setLogicDeleteFieldName("is_del");
        autoGenerator.setStrategy(strategyConfig);


        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        if (StringUtils.isBlank(condition.getParentPackage())) {
            log.error("请输入父包名");
            return;
        }
        packageConfig.setParent(condition.getParentPackage());// 自定义包路径
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("dao");
        packageConfig.setEntity("bean");
        autoGenerator.setPackageInfo(packageConfig);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 设置模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        // 如果模板引擎是 freemarker
        String templatePath = "/template/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        if (condition.getIsXml()) {
            // 自定义配置会被优先输出
            //mapper.xml
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
        } else {
            cfg.setFileOutConfigList(null);
        }
        autoGenerator.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig templateConfig = new TemplateConfig();
        //以参考源码 /mybatis-plus/src/main/resources/templates 使用 copy
        // 至您项目 src/main/resources/templates 目录下，模板名称也可自定义如下配置：
        if (condition.getIsController()) {
            templateConfig.setController("/template/controller.java");
        } else {
            templateConfig.setController(null);
        }

        if (condition.getIsEntity()) {
            templateConfig.setEntity("/template/entity.java");
        } else {
            templateConfig.setEntity(null);
        }

        if (condition.getIsMapper()) {
            templateConfig.setMapper("/template/mapper.java");
            templateConfig.setXml(null);
        } else {
            templateConfig.setMapper(null);
            templateConfig.setXml(null);
        }

        if (condition.getIsService()) {
            templateConfig.setService("/template/service.java");
        } else {
            templateConfig.setService(null);
        }
        if (condition.getIsServiceImpl()) {
            templateConfig.setServiceImpl("/template/serviceImpl.java");
        } else {
            templateConfig.setServiceImpl(null);
        }
        autoGenerator.setTemplate(templateConfig);

        //执行生成
        autoGenerator.execute();
    }


    public static void main(String[] args) {
        GeneratorCondition generatorCondition = new GeneratorCondition();
        generatorCondition.setAuthor("XuJing");
        generatorCondition.setUrl("jdbc:mysql://47.103.6.73:3306/standard?useUnicode=true&characterEncoding=utf8&useSSL=false&&serverTimezone=GMT%2B8");
        generatorCondition.setUsername("root");
        generatorCondition.setPassword("123456");
        generatorCondition.setParentPackage("cn.com.citydo.base");
        generatorCondition.setTablePre(new String[]{"tb"});
        generatorCondition.setTableName(new String[]{"tb_role_info"});
        generator(generatorCondition);
    }
}
