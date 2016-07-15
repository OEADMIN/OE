package com.openexpense.datacontrol;

/**
 * Created by jinyi on 16/7/15.
 */
public enum Operator {
    EQ("="), LIKE("like"), GT(">"), LT("<"), GTE(">="), LTE("<="),NOTEQ("!=");

    private String text;
    Operator(String text) {
        this.text = text;
    }
    public String toString() {
        return this.text;
    }
}
