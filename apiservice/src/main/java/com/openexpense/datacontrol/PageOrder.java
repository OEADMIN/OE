package com.openexpense.datacontrol;

import org.springframework.util.StringUtils;

/**
 * Created by jinyi on 16/7/15.
 */
public class PageOrder {
    private String field;
    private OrderType orderType;

    public PageOrder(String field,OrderType orderType){
        this.field = field;
        this.orderType = orderType;
    }

    public PageOrder(){
        this.field = "";
        this.orderType = OrderType.DESC;
    }

    public PageOrder(String field){
        this.field = field;
        this.orderType = OrderType.DESC;
    }

    public String toString(){
        StringBuilder sbSQL = new StringBuilder();
        if (!StringUtils.isEmpty(field)){
            sbSQL.append(" order by ").append(field).append(" ").append(orderType.toString());
        }
        return sbSQL.toString();
    }
}
