package com.openexpense.domain;

import com.openexpense.model.Menu;
import com.openexpense.model.Session;
import com.openexpense.model.User;
import com.openexpense.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuDomain {
    @Autowired
    MenuService menuService;

    public List<Menu> getUserMenu(Session session){
        User user = session.getUser();
        if (user.is_founder()){
            return menuService.getMenuList();
        }else {
            return menuService.getMenuList(user);
        }
    }
}
