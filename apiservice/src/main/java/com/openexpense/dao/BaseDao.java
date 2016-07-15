package com.openexpense.dao;

import com.openexpense.datacontrol.ObjectFilter;
import com.openexpense.datacontrol.SQLTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface BaseDao<T> {
    @InsertProvider(type = SQLTemplate.class,method = "insert")
    int insert(T t);

    @DeleteProvider(type = SQLTemplate.class,method = "delete")
    int delete(T t);

    @UpdateProvider(type = SQLTemplate.class,method = "update")
    int update(T t);

    @SelectProvider(type = SQLTemplate.class,method = "count")
    int count(@Param("entity") T t, @Param("filterList")List<ObjectFilter> objectFieldList);

}
