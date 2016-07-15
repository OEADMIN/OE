package com.openexpense.datacontrol;

import com.openexpense.exception.OeException;
import sun.jvm.hotspot.oops.ObjectHeap;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinyi on 16/7/15.
 */
public class EntityFieldUtil {
    private static Map<Class, Field[]> fieldMap = new HashMap();
    private Object obj = null;
    private Field[] fields = null;
    private String id = null;

    public EntityFieldUtil(Object obj) {
        if(!fieldMap.containsKey(obj.getClass())) {
            if(fieldMap.containsKey(obj.getClass())) {
                return;
            }

            fieldMap.put(obj.getClass(), obj.getClass().getDeclaredFields());
        }

        this.obj = obj;
        this.fields = (Field[])fieldMap.get(obj.getClass());
    }

    public String id() {
        String idName = "";
        Field[] arr$ = this.fields;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];
            if(field.isAnnotationPresent(Id.class)) {
                idName = this.getColumnName(field);
                if(null == idName) {
                    idName = field.getName();
                }
                break;
            }
        }

        return idName;
    }

    public Field idField() throws OeException {
        String idName = "";
        Field[] arr$ = this.fields;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];
            if(field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }

        throw new OeException("");
    }

    public String tablename() throws Exception {
        Table table = (Table)this.obj.getClass().getAnnotation(Table.class);
        if(table != null) {
            return table.name();
        } else {
            throw new Exception("undefine POJO @Table, need Tablename(@Table(name))");
        }
    }

    public String getCountRows() {
        StringBuilder sb = new StringBuilder();
        sb.append(" COUNT(1) ");
        return sb.toString();
    }

    public String getAllColumnNames() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Field[] arr$ = this.fields;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];
            if(i++ != 0) {
                sb.append(',');
            }

            sb.append(this.getColumnName(field));
        }

        return sb.toString();
    }

    public String getInsertColumnNames() throws OeException {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Field[] arr$ = this.fields;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];
            if(field.isAnnotationPresent(Column.class)) {
                Column column = (Column)field.getAnnotation(Column.class);
                if(column.insertable()) {
                    if(i++ != 0) {
                        sb.append(',');
                    }

                    sb.append(column.name());
                }
            }
        }

        return sb.toString();
    }

    public String getInsertColumnValues() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Field[] arr$ = this.fields;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];
            if(field.isAnnotationPresent(Column.class)) {
                Column column = (Column)field.getAnnotation(Column.class);
                if(column.insertable()) {
                    if(i++ != 0) {
                        sb.append(',');
                    }

                    sb.append("#{").append(field.getName()).append('}');
                }
            }
        }

        return sb.toString();
    }

    public String getUpdateColumnSet() {
        StringBuilder sbColumnSet = new StringBuilder();
        int i = 0;
        Field[] arr$ = this.fields;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];
            if(field.isAnnotationPresent(Column.class)) {
                Column column = (Column)field.getAnnotation(Column.class);
                if(column.updatable() && !isNull(field)) {
                    if(i++ != 0) {
                        sbColumnSet.append(',');
                    }

                    sbColumnSet.append(column.name());
                    sbColumnSet.append("=#{").append(field.getName()).append('}');
                }
            }
        }

        return sbColumnSet.toString();
    }

    private boolean isNull(Field field) {
        field.setAccessible(true);

        try {
            return field.get(this.obj) == null;
        } catch (IllegalAccessException var3) {
            return true;
        }
    }

    public String getFilterSql(List<ObjectHeap.ObjectFilter> filterList, List<ObjectField> listField) throws Exception {
        StringBuilder sbWhere = new StringBuilder();

        for(int iFilter = 0; iFilter < filterList.size(); ++iFilter) {
            boolean addRelation = true;
            ObjectFilter filter = (ObjectFilter)filterList.get(iFilter);
            if(iFilter == 0) {
                addRelation = false;
            }

            sbWhere.append(filter.toString(this, listField, Boolean.valueOf(addRelation)));
        }

        return sbWhere.toString();
    }

    public String getColumnName(Field field) {
        String methodName = null;
        if(field.isAnnotationPresent(Column.class)) {
            Column column = (Column)field.getAnnotation(Column.class);
            methodName = column.name();
        }

        return methodName;
    }

    public Field[] getFields() {
        return this.fields;
    }
}
