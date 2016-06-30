package com.openexpense.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**用户登录接口单元测试
 *2016/06/30.
 *@author xjouyi@163.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:config/spring.xml","classpath*:/config/spring-mvc.xml"})
public class TestHostController {

    @Autowired
    HostController hostController;

    /**用户注销测试
    */
    @Test
    public void signOut() {
        assertEquals("hello",hostController.signOut()) ;
    }
}
