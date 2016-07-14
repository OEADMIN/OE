package com.openexpense.controller;

import com.openexpense.dto.OeResult;
import com.openexpense.model.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**用户登录接口单元测试
 *2016/06/30.
 *@author xjouyi@163.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:config/spring.xml",
"classpath*:/config/spring-mvc.xml",
"classpath*:/config/spring-mybatis.xml"})
public class TestHostController {

    @Autowired
    HostController hostController;

    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

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
        OeResult oeResultSuccess = hostController.signIn("xjouyi@openexpense.com","123");
        assertEquals("success",oeResultSuccess.getType().getName());
        Session session = (Session) oeResultSuccess.getData().get(0);

        System.out.println(session.getSessionid());
        OeResult oeResultOut = hostController.signOut(session.getSessionid());
        assertEquals("success",oeResultOut.getType().getName()) ;
    }

    @Test
    public void signUp() throws Exception {
        this.mockMvc.perform((put("/host/signup")))
                .andDo(print());
    }


}
