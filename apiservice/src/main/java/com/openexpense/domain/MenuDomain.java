package com.openexpense.domain;

import com.openexpense.dto.MenuDto;
import com.openexpense.model.User;
import com.openexpense.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**菜单业务领域
 *2016/07/06.
 *@author xjouyi@163.com
 */
@Service
public class MenuDomain {
    @Autowired
    MenuService menuService;
    /**根据用户获得菜单
     *@param user 用户对象
     *@return 菜单列表
     */
    public List<MenuDto> getUserMenu(User user){
        if (user.is_founder()){
            return menuService.getMenuList();
        }else {
            return menuService.getMenuList(user);
        }
    }
}
