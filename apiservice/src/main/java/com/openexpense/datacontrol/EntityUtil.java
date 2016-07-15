package com.openexpense.datacontrol;


import com.openexpense.common.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityUtil {
    private static Map<Class, List<PropertyDescriptor>> columnMap = new HashMap<Class, List<PropertyDescriptor>>();

    private Object obj = null;
    private List<PropertyDescriptor> columnList = null;

    public EntityUtil(Object obj){
        if(!columnMap.containsKey(obj.getClass())){
            if(columnMap.containsKey(obj.getClass()))
                return;

            BeanInfo intro = null;
            try {
                intro = Introspector.getBeanInfo(obj.getClass());
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            PropertyDescriptor[] propertyDescriptors = intro.getPropertyDescriptors();
            List<PropertyDescriptor> columnList = new ArrayList<PropertyDescriptor>(propertyDescriptors.length);
            for (PropertyDescriptor p : propertyDescriptors) {
                Method method = p.getReadMethod();
                if (method.isAnnotationPresent(Column.class)) {
                    columnList.add(p);
                }
            }
            columnMap.put(obj.getClass(), columnList);
        }

        this.obj = obj;
        this.columnList = columnMap.get(obj.getClass());
    }

    public String id() {
        String idName = "";
        for (PropertyDescriptor p : columnList) {
            Method method = p.getReadMethod();
            if (method.isAnnotationPresent(Id.class)) {
                idName = getPropertyColumnName(p);
                if (null == idName) {
                    idName = p.getName();
                }
                break;
            }
        }
        return idName;
    }

    public String tablename() throws Exception {
        Table table = obj.getClass().getAnnotation(Table.class);
        if(table != null)
            return table.name();
        else
            throw new Exception("undefine POJO @Table, need Tablename(@Table(name))");
    }

    public String getCountRows(){
        StringBuilder sb = new StringBuilder();
        sb.append(" COUNT(1) ");
        return sb.toString();
    }

    public String getAllColumnNames(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(PropertyDescriptor p : this.columnList) {
            if(i++ != 0) {
                sb.append(',');
            }
            sb.append(getPropertyColumnName(p));
        }
        return sb.toString();
    }

    public String getInsertColumnNames(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String idName = id();
        for(PropertyDescriptor p : this.columnList) {
            if (!idName.equals(p.getName()) && isNull(p)) {
                continue;
            }
            if (!getCanInsertColumn(p)){
                continue;
            }
            if(i++ != 0) {
                sb.append(',');
            }
            sb.append(getPropertyColumnName(p));
        }
        return sb.toString();
    }

    public String getInsertColumnValues(){
        StringBuilder sb = new StringBuilder();
        String idName = id();
        int i = 0;
        for(PropertyDescriptor p : this.columnList) {
            String column = p.getName();
            if (!idName.equals(column) && isNull( p)) {
                continue;
            }
            if (!getCanInsertColumn(p)){
                continue;
            }
            if(i++ != 0)
                sb.append(',');
            sb.append("#{").append(column).append('}');
        }
        return sb.toString();
    }

    public String getUpdateColumnSet(){
        StringBuilder sbColumnSet = new StringBuilder();
        String idName = id();
        int i = 0;

        for(PropertyDescriptor p : this.columnList) {
            String column = p.getName();
            if (!idName.equals(column) && isNull(p)) {
                continue;
            }
            if (!getCanUpdateColumn(p)){
                continue;
            }
            if (!isFailValue(p)){
                continue;
            }
            if(i++ != 0)
                sbColumnSet.append(',');
            sbColumnSet.append(getPropertyColumnName(p));
            sbColumnSet.append("=#{").append(column).append('}');
        }

        return sbColumnSet.toString();
    }

    public String getFilterSql(List<ObjectFilter> filterList,List<ObjectField> listField) throws Exception {
        StringBuilder sbWhere = new StringBuilder();

        for (int iFilter = 0;iFilter < filterList.size();iFilter ++){
            boolean addRelation = true;
            ObjectFilter filter = filterList.get(iFilter);
            if (iFilter == 0){
                addRelation = false;
            }
            sbWhere.append(filter.toString(this,listField,addRelation));
        }
        return sbWhere.toString();
    }

    public String getSearchFilterSql(List<ObjectField> objectFieldList) throws Exception {
        StringBuilder sbWhere = new StringBuilder();
        for (int iFilter = 0;iFilter < objectFieldList.size();iFilter++){
            ObjectField objectField = objectFieldList.get(iFilter);

            if(iFilter != objectFieldList.size() - 1){
//                sbWhere.append(" ").append(searchFilter.relation.toString()).append(" ");
                // TODO
            }

            for (int iProperty = 0;iProperty < this.columnList.size();iProperty ++){
                PropertyDescriptor peroperty = this.columnList.get(iProperty);
                String column = peroperty.getName();
                if (column.equals(objectField.getFieldName())){
                    sbWhere.append(tablename()+"."+getPropertyColumnName(peroperty));

                    switch (objectField.getOperator()){
                        case EQ:
                            sbWhere.append(" = ");
                            break;
                        case LIKE:
                            sbWhere.append(" like ");
                            objectField.setValue("%"+ objectField.getValue()+"%");
                            break;
                        case GT:
                            sbWhere.append(" > ");
                            break;
                        case LT:
                            sbWhere.append(" < ");
                            break;
                        case GTE:
                            sbWhere.append(" >= ");
                            break;
                        case LTE:
                            sbWhere.append(" <= ");
                            break;
                    }
                    sbWhere.append("#{filterList["+iFilter+"].value}");

                    break;
                }
            }

        }
        return sbWhere.toString();
    }

    private boolean isNull(PropertyDescriptor propertyDescriptor) {
        try {
            return propertyDescriptor.getReadMethod().invoke(obj) == null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isFailValue(PropertyDescriptor p){
        String methodName = null;
        Method method = p.getReadMethod();
        if (method.isAnnotationPresent(ValueFailOption.class)){
            Annotation columnAnnotation = method.getAnnotation(ValueFailOption.class);
            if (null != columnAnnotation) {
                ValueFailOption column = (ValueFailOption)columnAnnotation;
                try {
                    if ((Integer)(p.getReadMethod().invoke(obj)) == column.fieldIntValue()){
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return true;

    }

    public boolean getCanInsertColumn(PropertyDescriptor p){
        String methodName = null;
        Method method = p.getReadMethod();
        if (method.isAnnotationPresent(ColumnOption.class)){
            Annotation columnAnnotation = method.getAnnotation(ColumnOption.class);
            if (null != columnAnnotation) {
                ColumnOption column = (ColumnOption)columnAnnotation;
                return column.insert();
            }
        }
        return true;
    }

    public boolean getCanUpdateColumn(PropertyDescriptor p){
        String methodName = null;
        Method method = p.getReadMethod();
        if (method.isAnnotationPresent(ColumnOption.class)){
            Annotation columnAnnotation = method.getAnnotation(ColumnOption.class);
            if (null != columnAnnotation) {
                ColumnOption column = (ColumnOption)columnAnnotation;
                return column.update();
            }
        }
        return true;

    }

    public String getPropertyColumnName(PropertyDescriptor p) {
        String methodName = null;
        Method method = p.getReadMethod();
        if (method.isAnnotationPresent(Column.class)) {
            Annotation columnAnnotation = method.getAnnotation(Column.class);
            if (null != columnAnnotation) {
                Column column = (Column)columnAnnotation;
                String columnName = column.name();
                if (StringUtils.isNotBlank(columnName)) {
                    methodName = columnName;
                }
            }
            if (StringUtils.isBlank(methodName)) {
                methodName = p.getName();
            }
        }
        return methodName;
    }

    public List<PropertyDescriptor> getColumnList(){
        return this.columnList;
    }
}
