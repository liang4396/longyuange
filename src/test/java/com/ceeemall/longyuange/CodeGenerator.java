package com.ceeemall.longyuange;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author llp
 * @date 2021/7/20 9:50
 */
public class CodeGenerator {

    // 要生成代码的表名配置
    String[] tables = {
            "sys_user"//xxx表
/*            "t_aaa",//aaa表
            "t_bbbb"//bbbb表*/
    };

    @Test
    public void genCode(){
        //创建代码生成器
        AutoGenerator generator = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("llp");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setSwagger2(true);//开启Swagger2模式
        generator.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL);
        dsConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dsConfig.setUrl("jdbc:mysql://115.29.140.84:3306/longyuange?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dsConfig.setUsername("root");
        dsConfig.setPassword("xiaopihai");
        generator.setDataSource(dsConfig);

        // 4、包配置
        PackageConfig pkConfig  = new PackageConfig();
        pkConfig.setParent("com.ceeemall.longyuange.user");//
        pkConfig.setEntity("pojo.entity");//与数据库结构一致
        generator.setPackageInfo(pkConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategyConfig.setEntityLombokModel(true);
        //strategyConfig.setLogicDeleteFieldName("is_deleted");
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀（确保tinyint(1)）
        strategyConfig.setRestControllerStyle(true); //restful api风格控制器
        strategyConfig.setInclude(tables);//生成的表
        generator.setStrategy(strategyConfig);

        generator.execute();

    }

    @Test
    public void password(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new  BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}
