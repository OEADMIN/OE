package com.openexpense.datacontrol;

/**
 * Created by jinyi on 16/7/15.
 */
public class ObjectField {
    private String fieldName;
    private Object value;
    private Operator operator;

    public ObjectField(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = Operator.EQ;
    }

    public ObjectField(String fieldName, Operator operator, Object value){
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Operator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value){
        this.value = value;
    }
}
