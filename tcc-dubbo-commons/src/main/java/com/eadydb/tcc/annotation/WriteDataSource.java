package com.eadydb.tcc.annotation;

import java.lang.annotation.*;

/**
 * @author eadydb
 * @date 17-11-13 下午2:19
 * <p/>
 * @description 写库注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WriteDataSource {
}


