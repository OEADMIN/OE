package com.openexpense.common;

import com.openexpense.dto.MenuDto;
import com.openexpense.model.Menu;

import java.util.ArrayList;
import java.util.List;

public final class ConvartUtils {
    private static List<MenuDto> getChildMenu(String menuParent,List<Menu> sourceList){
        if (StringUtils.isEmpty(menuParent)){
            return null;
        }
        List<MenuDto> list = new ArrayList<>();
        for (Menu menu : sourceList){
            if (menu.getMenu_code().equals(menuParent)){
                MenuDto dto = toMenu(menu);
                dto.setChild(getChildMenu(dto.getCode(),sourceList));
                list.add(dto);
            }
        }
        return list.size() > 0 ? list:null;
    }

    public static List<MenuDto> toMenu(List<Menu> menus){
        List<MenuDto> list = new ArrayList<>();
        for (Menu menu : menus) {
            if (StringUtils.isEmpty(menu.getMenu_parent()) ||
                    menu.getMenu_parent().equals(menu.getMenu_code())){
                list.add(toMenu(menu));
            }
        }
        for (MenuDto menuDto : list){
            menuDto.setChild(getChildMenu(menuDto.getCode(),menus));
        }
        return list;
    }

    private static MenuDto toMenu(Menu menu){
        MenuDto dto = new MenuDto();
        dto.setCode(menu.getMenu_code());
        dto.setName(menu.getMenu_name());
        dto.setRoute(menu.getMenu_route());
        dto.setPath(menu.getMenu_path());
        dto.setOrder(menu.getMenu_order());
        return dto;
    }
}
