package com.eadydb.tcc.capital.config.mybatis;

import com.eadydb.tcc.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    public static void setRead() {
        local.set(DataSourceType.READ.getType());
    }

    public static void setWrite() {
        local.set(DataSourceType.WRITE.getType());
    }

    public static String getReadOrWrite() {
        return local.get();
    }

    public static void clear() {
        local.remove();
    }
}
