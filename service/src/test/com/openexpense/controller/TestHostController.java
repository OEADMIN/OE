package com.openexpense.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by jinyi on 16/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:config/spring.xml","classpath*:/config/spring-mvc.xml"})
public class TestHostController {

    @Autowired
    HostController hostController;

    @Test
    public void signOut() {
        assertEquals("hello",hostController.signOut()) ;
    }
}
