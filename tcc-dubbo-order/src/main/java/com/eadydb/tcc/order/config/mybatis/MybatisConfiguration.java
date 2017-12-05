package com.eadydb.tcc.order.config.mybatis;

import com.eadydb.tcc.order.util.SpringContextUtil;
import com.eadydb.tcc.enums.DataSourceType;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@AutoConfigureAfter({DataSourceConfig.class})
@MapperScan(basePackages = "com.eadydb.tcc.order.mapper")
@Slf4j
public class MybatisConfiguration {

    @Value("${mysql.datasource.readSize}")
    private String readDataSourceSize;

    // 映射文件
    @Value("${mysql.datasource.mapperLocations}")
    private String mapperLocations;

    // mybatis配置文件
    @Value("${mysql.datasource.configLocation}")
    private String configLocation;

    @Value("${mysql.datasource.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Autowired
    @Qualifier("writeDataSource")
    private DataSource writeDataSource;
    @Autowired
    @Qualifier("readDataSource01")
    private DataSource readDataSource01;
    @Autowired
    @Qualifier("readDataSource02")
    private DataSource readDataSource02;


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(roundRobinDataSourceProxy());

            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            // 设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);

            // 设置mybatis-config.xml配置文件
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

            // 添加分页插件
            Interceptor[] plugins = new Interceptor[]{pageHelper(), new SqlPrintInterceptor()};
            sessionFactoryBean.setPlugins(plugins);

            return sessionFactoryBean.getObject();
        } catch (IOException ex) {
            log.error("mybatis resolver mapper.xml is error", ex);
            return null;
        } catch (Exception e) {
            log.error("mybatis sqlSessionFactoryBean create error", e);
            return null;
        }
    }

    // 分页插件
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    /**
     * 把所有数据库都放进路由中
     *
     * @return
     */
    @Bean(name = "roundRobinDataSourceProxy")
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        //把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，
        //否则切换数据源时找不到正确的数据源
        targetDataSource.put(DataSourceType.WRITE.getType(), writeDataSource);
        targetDataSource.put(DataSourceType.READ.getType() + "1", readDataSource01);
        targetDataSource.put(DataSourceType.READ.getType() + "2", readDataSource02);

        final int size = Integer.parseInt(readDataSourceSize);

        // 路由,寻找对应的数据源
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource() {
            private AtomicInteger count = new AtomicInteger(0);

            /**
             * 这是AbstractRoutingDataSource类中的一个抽象方法，
             * 而它的返回值是你所要用的数据源dataSource的key值，有了这个key值，
             * targetDataSources就从中取出对应的DataSource，如果找不到，就用配置默认的数据源。
             */
            @Override
            protected Object determineCurrentLookupKey() {
                String typeKey = DataSourceContextHolder.getReadOrWrite();
                if (typeKey == null) {
                    //	System.err.println("使用数据库write.............");
                    //    return DataSourceType.write.getType();
                    throw new NullPointerException("数据库路由时，决定使用哪个数据库源类型不能为空...");
                }
                if (typeKey.equals(DataSourceType.WRITE.getType())) {
                    return DataSourceType.WRITE.getType();
                }
                //读库， 简单负载均衡
                int number = count.getAndAdd(1);
                int lookupKey = number % size;
                return DataSourceType.READ.getType() + (lookupKey + 1);
            }
        };
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSource);
        return proxy;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //事务管理
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager((DataSource) SpringContextUtil.getBean("roundRobinDataSourceProxy"));
    }

}
