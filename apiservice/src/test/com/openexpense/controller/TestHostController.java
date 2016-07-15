package com.openexpense.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openexpense.dto.OeResult;
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

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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

    private OeResult siginIn(String logincode,String pass) throws Exception {
        String result = this.mockMvc.perform((get("/host/signin"))
                .param("logincode",logincode)
                .param("pass",pass)
        )
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
        return new ObjectMapper().readValue(result, OeResult.class);
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
    public void testSignIn() throws Exception {
        OeResult oeResult;

        oeResult = siginIn("xjouyi","");
        assertEquals("fail",oeResult.getType().getName());

        oeResult =  siginIn("xjouyi@openexpense.com","1234");
        assertEquals("fail",oeResult.getType().getName());
    }
    /**用户注销测试
    */
    @Test
    public void testSignOut() throws Exception {
        OeResult oeResult = siginIn("xjouyi@openexpense.com","123");
        assertEquals("success",oeResult.getType().getName());
        LinkedHashMap map = (LinkedHashMap) oeResult.getData().get(0);

        OeResult oeResultOut = hostController.signOut((String) map.get("sessionid"));
        assertEquals("success",oeResultOut.getType().getName()) ;
    }

    @Test
    public void signUp() throws Exception {
        String result = this.mockMvc.perform((put("/host/signup")))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


}
