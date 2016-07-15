package com.openexpense.datacontrol;

import com.openexpense.common.StringUtils;
import org.apache.ibatis.jdbc.SqlBuilder;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jinyi on 16/7/15.
 */
public class SQLTemplate {
    public SQLTemplate(){

    }

    public String insert(Object obj) throws Exception {
        EntityFieldUtil entityUtil = new EntityFieldUtil(obj);
        SqlBuilder.BEGIN();
        SqlBuilder.INSERT_INTO(entityUtil.tablename());
        SqlBuilder.VALUES(entityUtil.getInsertColumnNames(), entityUtil.getInsertColumnValues());
        return SqlBuilder.SQL();
    }

    public String update(Object obj) throws Exception {
        EntityFieldUtil entityUtil = new EntityFieldUtil(obj);
        Field field = entityUtil.idField();
        Column column = (Column)field.getAnnotation(Column.class);
        SqlBuilder.BEGIN();
        SqlBuilder.UPDATE(entityUtil.tablename());
        SqlBuilder.SET(entityUtil.getUpdateColumnSet());
        SqlBuilder.WHERE(column.name() + "=#{" + field.getName() + "}");
        return SqlBuilder.SQL();
    }

    public String delete(Object obj) throws Exception {
        EntityFieldUtil entityUtil = new EntityFieldUtil(obj);
        Field field = entityUtil.idField();
        Column column = (Column)field.getAnnotation(Column.class);
        SqlBuilder.BEGIN();
        SqlBuilder.DELETE_FROM(entityUtil.tablename());
        SqlBuilder.WHERE(column.name() + "=#{" + field.getName() + "}");
        return SqlBuilder.SQL();
    }

    public String get(Object obj) throws Exception {
        EntityFieldUtil entityUtil = new EntityFieldUtil(obj);
        Field field = entityUtil.idField();
        Column column = (Column)field.getAnnotation(Column.class);
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT(entityUtil.getAllColumnNames());
        SqlBuilder.FROM(entityUtil.tablename());
        SqlBuilder.WHERE(column.name() + "=#{" + field.getName() + "}");
        return SqlBuilder.SQL();
    }

    public String getById(Object obj) throws Exception {
        EntityFieldUtil entityUtil = new EntityFieldUtil(obj);
        String idname = entityUtil.id();
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT(entityUtil.getAllColumnNames());
        SqlBuilder.FROM(entityUtil.tablename());
        SqlBuilder.WHERE(idname + "=#{" + idname + "}");
        return SqlBuilder.SQL();
    }

    public String count(Object obj) throws Exception {
        Map map = (Map)obj;
        Object entity = map.get("entity");
        List objectFieldList = (List)map.get("filterList");
        ArrayList list = new ArrayList();
        Iterator entityUtil = objectFieldList.iterator();

        while(entityUtil.hasNext()) {
            ObjectFilter paramString = (ObjectFilter)entityUtil.next();
            list.addAll(paramString.getFieldList());
        }

        map.remove("filterList");
        map.put("filterList", list);
        EntityFieldUtil entityUtil1 = new EntityFieldUtil(entity);
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT(entityUtil1.getCountRows());
        SqlBuilder.FROM(entityUtil1.tablename());
        String paramString1 = entityUtil1.getFilterSql(objectFieldList, list);
        if(StringUtils.isNotBlank(paramString1)) {
            SqlBuilder.WHERE(paramString1);
        }

        return SqlBuilder.SQL();
    }

    public String findBy(Object obj) throws Exception {
        Map map = (Map)obj;
        Object entity = map.get("entity");
        PageLimit pageLimit = (PageLimit)map.get("pagelimit");
        PageOrder pageOrder = (PageOrder)map.get("pageorder");
        List objectFieldList = (List)map.get("filterList");
        ArrayList list = new ArrayList();
        Iterator entityUtil = objectFieldList.iterator();

        while(entityUtil.hasNext()) {
            ObjectFilter paramString = (ObjectFilter)entityUtil.next();
            list.addAll(paramString.getFieldList());
        }

        map.remove("filterList");
        map.put("filterList", list);
        EntityFieldUtil entityUtil1 = new EntityFieldUtil(entity);
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT(entityUtil1.getAllColumnNames());
        SqlBuilder.FROM(entityUtil1.tablename());
        String paramString1 = entityUtil1.getFilterSql(objectFieldList, list);
        if(StringUtils.isNotBlank(paramString1)) {
            SqlBuilder.WHERE(paramString1);
        }

        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append(SqlBuilder.SQL());
        sbSQL.append(this.getOrder(pageOrder));
        sbSQL.append(this.getLimit(pageLimit));
        return sbSQL.toString();
    }

    public String query(Object obj) throws Exception {
        Map map = (Map)obj;
        Object entity = map.get("entity");
        List objectFieldList = (List)map.get("filterList");
        ArrayList list = new ArrayList();
        Iterator entityUtil = objectFieldList.iterator();

        while(entityUtil.hasNext()) {
            ObjectFilter paramString = (ObjectFilter)entityUtil.next();
            list.addAll(paramString.getFieldList());
        }

        map.remove("filterList");
        map.put("filterList", list);
        EntityFieldUtil entityUtil1 = new EntityFieldUtil(entity);
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT(entityUtil1.getAllColumnNames());
        SqlBuilder.FROM(entityUtil1.tablename());
        String paramString1 = entityUtil1.getFilterSql(objectFieldList, list);
        if(StringUtils.isNotBlank(paramString1)) {
            SqlBuilder.WHERE(paramString1);
        }

        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append(SqlBuilder.SQL());
        return sbSQL.toString();
    }

    public String getLimit(Object start, Object pageSize) {
        StringBuilder sbSQL = new StringBuilder();
        if(start != null && pageSize != null && !start.equals(Integer.valueOf(0)) && !pageSize.equals(Integer.valueOf(0))) {
            int startInt = ((Integer)start).intValue();
            int pageSizeInt = ((Integer)pageSize).intValue();
            sbSQL.append(" limit ").append((startInt - 1) * pageSizeInt).append(",").append(pageSizeInt);
        }

        return sbSQL.toString();
    }

    public String getLimit(PageLimit pageLimit) {
        StringBuilder sbSQL = new StringBuilder();
        if(pageLimit != null && pageLimit.getPageNo() > 0 && pageLimit.getLimit() > 0) {
            int startInt = pageLimit.getPageNo() - 1;
            int pageSizeInt = pageLimit.getLimit();
            sbSQL.append(" limit ").append(startInt * pageSizeInt).append(",").append(pageSizeInt);
        }

        return sbSQL.toString();
    }

    public String getOrder(PageOrder pageOrder) {
        StringBuilder sbSQL = new StringBuilder();
        if(pageOrder != null) {
            sbSQL.append(pageOrder.toString());
        }

        return sbSQL.toString();
    }
}
