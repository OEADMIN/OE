package com.openexpense.datacontrol;

/**
 * Created by jinyi on 16/7/15.
 */
public enum OrderType {
    DESC("desc"),ASC("asc");
    private String text;
    OrderType(String text) {
        this.text = text;
    }
    public String toString() {
        return this.text;
    }
}
