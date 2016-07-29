package com.openexpense.controller;

import com.openexpense.domain.MenuDomain;
import com.openexpense.dto.OeResult;
import com.openexpense.exception.OeException;
import com.openexpense.model.Session;
import com.openexpense.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jinyi on 16/7/26.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    SessionService sessionService;

    @Autowired
    MenuDomain menuDomain;

    @RequestMapping(value = "/usermenu",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public OeResult getUserMenu(@RequestParam(name = "id")String id){
        try {
            Session session = sessionService.getSession(id);
            return OeResult.getSuccessResult(menuDomain.getUserMenu(session));
        } catch (OeException e) {
            return e.getResult();
        }
    }
}
