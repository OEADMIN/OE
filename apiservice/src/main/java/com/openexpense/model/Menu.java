package com.openexpense.model;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "oe_menu")
public class Menu {
    private String menu_code;
    private String menu_name;
    private String menu_parent;
    private String menu_route;
    private String menu_path;
    private int menu_order;
    private String menu_state;
    private Date create_date;

    public String getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(String menu_code) {
        this.menu_code = menu_code;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_parent() {
        return menu_parent;
    }

    public void setMenu_parent(String menu_parent) {
        this.menu_parent = menu_parent;
    }

    public String getMenu_route() {
        return menu_route;
    }

    public void setMenu_route(String menu_route) {
        this.menu_route = menu_route;
    }

    public String getMenu_path() {
        return menu_path;
    }

    public void setMenu_path(String menu_path) {
        this.menu_path = menu_path;
    }

    public int getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(int menu_order) {
        this.menu_order = menu_order;
    }

    public String getMenu_state() {
        return menu_state;
    }

    public void setMenu_state(String menu_state) {
        this.menu_state = menu_state;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
