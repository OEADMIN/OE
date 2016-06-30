package com.openexpense.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jinyi on 16/6/13.
 */
@Controller
@RequestMapping("/host")
public class HostController {

    @ResponseBody
    @RequestMapping(value = "/signin/{id}/{pass}",method = RequestMethod.GET)
    public String signIn(@PathVariable("id")String id,@PathVariable("pass")String pass){
        return id+","+pass;
    }

    @ResponseBody
    @RequestMapping(value = "/signout",method = RequestMethod.GET)
    public String signOut(){
        return "hello";
    }
}
