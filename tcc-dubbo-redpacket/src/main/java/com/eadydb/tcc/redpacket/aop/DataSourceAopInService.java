package com.eadydb.tcc.redpacket.aop;

import com.eadydb.tcc.redpacket.config.mybatis.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @author eadydb
 * @date 11/16/17 2:44 PM
 * <p/>
 * @description
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Component
@Slf4j
public class DataSourceAopInService implements PriorityOrdered {

    @Override
    public int getOrder() {
        return 1;
    }

    @Before("execution(* com.eadydb.tcc.*.service..*.*(..)) && @annotation(com.eadydb.tcc.annotation.ReadDataSource)")
    public void setReadDataSource() {
        DataSourceContextHolder.setRead();
    }

    @Before("execution(* com.eadydb.tcc.*.service..*.*(..)) && @annotation(com.eadydb.tcc.annotation.WriteDataSource)")
    public void setWriteDataSource() {
        DataSourceContextHolder.setWrite();
    }
}
