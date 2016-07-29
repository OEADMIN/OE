package com.openexpense.service;

import com.openexpense.dto.MenuDto;
import com.openexpense.model.User;

import java.util.List;

/**
 * Created by jinyi on 16/7/26.
 */
public interface MenuService {
    enum Type {
        /**正常 00*/
        NORMAL ("00");
        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    List<MenuDto> getMenuList();
    List<MenuDto> getMenuList(User user);
}
