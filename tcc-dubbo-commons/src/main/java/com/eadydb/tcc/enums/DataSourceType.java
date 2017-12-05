package com.eadydb.tcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author eadydb
 * @date 17-11-13 下午2:18
 * <p/>
 * @description 数据源读写类型枚举
 */
@Getter
@AllArgsConstructor
public enum DataSourceType {

    WRITE("write", "写库"),
    READ("read", "读库");

    private String type;

    private String name;
}
