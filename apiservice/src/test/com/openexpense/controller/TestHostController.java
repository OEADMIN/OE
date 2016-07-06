package com.openexpense.controller;

import com.openexpense.dto.OeResult;
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
@ContextConfiguration({"classpath*:config/spring.xml",
"classpath*:/config/spring-mvc.xml",
"classpath*:/config/spring-mybatis.xml"})
public class TestHostController {

    @Autowired
    HostController hostController;


    /**用户登录测试</br>
     * xjouyi/                          {fail H001  用户名密码不能为空}
     * /                                {fail H001  用户名密码不能为空}
     * /123                             {fail H001  用户名密码不能为空}
     * xjouyi/123                       {fail H002  用户名格式错误}
     * xjouyi@openexpense.com/123456    {fail H003  密码错误}
     * xjouyi@baidu.com/123             {fail C001  企业不存在}
     * xjouyi@openexpense.com/123       {success  成功}
     */
    @Test
    public void signIn() {
        OeResult oeResultH001 = hostController.signIn("xjouyi","");
        assertEquals("fail",oeResultH001.getType().getName());
        assertEquals("H001",oeResultH001.getCode());

        OeResult oeResultH001_2 = hostController.signIn("","");
        assertEquals("fail",oeResultH001_2.getType().getName());
        assertEquals("H001",oeResultH001_2.getCode());

        OeResult oeResultH001_3 = hostController.signIn("","123");
        assertEquals("fail",oeResultH001_3.getType().getName());
        assertEquals("H001",oeResultH001_3.getCode());

        OeResult oeResultH002 = hostController.signIn("xjouyi","123");
        assertEquals("fail",oeResultH002.getType().getName());
        assertEquals("H002",oeResultH002.getCode());

        OeResult oeResultH003 = hostController.signIn("xjouyi@openexpense.com","123456");
        assertEquals("fail",oeResultH003.getType().getName());
        assertEquals("H003",oeResultH003.getCode());

        OeResult oeResultC001 = hostController.signIn("xjouyi@baidu.com","123");
        assertEquals("fail",oeResultC001.getType().getName());
        assertEquals("C001",oeResultC001.getCode());

        OeResult oeResultSuccess = hostController.signIn("xjouyi@openexpense.com","123");
        assertEquals("success",oeResultSuccess.getType().getName());
    }
    /**用户注销测试
    */
    @Test
    public void signOut() {
        assertEquals("hello",hostController.signOut()) ;
    }
}
