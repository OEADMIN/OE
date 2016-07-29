package com.openexpense.service.impl;

import com.openexpense.common.ConvartUtils;
import com.openexpense.dao.MenuDao;
import com.openexpense.dto.MenuDto;
import com.openexpense.model.Menu;
import com.openexpense.model.User;
import com.openexpense.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<MenuDto> getMenuList() {
        List<Menu> menus = menuDao.getMenu(Type.NORMAL.getName());
        return ConvartUtils.toMenu(menus);
    }

    @Override
    public List<MenuDto> getMenuList(User user) {
        List<Menu> menus = menuDao.getMenuByUser(user.getUser_id());
        return ConvartUtils.toMenu(menus);
    }
}
