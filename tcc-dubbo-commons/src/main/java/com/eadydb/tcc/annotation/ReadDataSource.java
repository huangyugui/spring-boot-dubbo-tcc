package com.eadydb.tcc.annotation;

import java.lang.annotation.*;

/**
 * @author eadydb
 * @date 17-11-13 下午2:21
 * <p/>
 * @description 读库注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ReadDataSource {
}
