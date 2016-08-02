package com.openexpense;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openexpense.dto.OeResult;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by jinyi on 16/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:config/spring.xml",
        "classpath*:/config/spring-mvc.xml",
        "classpath*:/config/spring-mybatis.xml"})
@Transactional
@Rollback(value = true)


public class TestMenuController {
    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    private OeResult siginIn(String logincode, String pass) throws Exception {
        String result = this.mockMvc.perform((post("/host/signin"))
                .param("logincode",logincode)
                .param("pass",pass)
        )
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
        return new ObjectMapper().readValue(result, OeResult.class);
    }
}
