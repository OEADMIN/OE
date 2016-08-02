package com.openexpense.controller;

import com.openexpense.domain.MenuDomain;
import com.openexpense.dto.OeResult;
import com.openexpense.exception.OeException;
import com.openexpense.model.Session;
import com.openexpense.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**菜单接口
 *2016/07/06.
 *@author xjouyi@163.com
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    SessionService sessionService;

    @Autowired
    MenuDomain menuDomain;

    /**根据企业域获取企业对象
     *@param id sessionid
     *@return 菜单数据
     */
    @RequestMapping(value = "/usermenu",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public OeResult getUserMenu(@RequestParam(name = "id")String id){
        try {
            Session session = sessionService.getSession(id);
            return OeResult.getSuccessResult(menuDomain.getUserMenu(session.getUser()));
        } catch (OeException e) {
            return e.getResult();
        }
    }
}
