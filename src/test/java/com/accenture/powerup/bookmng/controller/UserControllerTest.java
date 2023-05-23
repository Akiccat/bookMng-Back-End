package com.accenture.powerup.bookmng.controller;

import com.accenture.powerup.bookmng.requestdto.EditUserForm;
import com.accenture.powerup.bookmng.requestdto.RegistrationForm;
import com.accenture.powerup.bookmng.service.RankingService;
import com.accenture.powerup.bookmng.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private static final Logger log = LoggerFactory.getLogger(LoginControllerTest.class);
    private MockMvc mvc;
    // 使用mockito提供的@Mock注解装饰外部业务层，模拟外部业务层依赖
    @Mock
    private UserService userService;
    // 创建一个实例，其余用@Mock注解创建的mock将被注入到用该实例中。
    @InjectMocks
    private UserController userController;
    // 在被@Test注解装饰的测试方法之前执行被@BeforeEach装饰的非静态方法，@BeforeEach为Junit5提供的注解
    @BeforeEach
    public void setUp() {
        // 执行以后，service自动注入到controller中。
        MockitoAnnotations.openMocks(this);
        // 构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * 测试方法
     * <p>用于测试UserController中的registerUser方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception 异常
     */
    @Test
    public void testRegisterUser() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(userService.userRegister(
                Mockito.any(RegistrationForm.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\":\"5\",\n" +
                        "    \"userName\":\"William\",\n" +
                        "    \"password\":\"william\",\n" +
                        "    \"confirmPassword\":\"william\",\n" +
                        "    \"email\":\"william@qq.com\",\n" +
                        "    \"birthday\":\"2023-12-12\",\n" +
                        "    \"gender\":0,\n" +
                        "    \"grade\":1,\n" +
                        "    \"interest\":\"吃饭\",\n" +
                        "    \"introduction\":\"我是章鱼小丸子\"\n" +
                        "}");

        // 发送请求，获取请求结果
        ResultActions perform = mvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        log.info("response:" + response.getContentAsString());
        // 断言
        /*System.out.println(response.getContentAsString());*/
        assertEquals(response.getContentAsString(), i.toString());
    }

    @Test
    public void testRegisterUserException() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(userService.userRegister(Mockito.any(RegistrationForm.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}");


        Throwable exception = assertThrows(NestedServletException.class, () -> {
            mvc.perform(request);
        });
        assertEquals(
                exception.getMessage(),"Request processing failed; nested exception is com.accenture.powerup.bookmng.exception.BusinessFailureException"
        );
    }

    /**
     * 测试方法
     * <p>用于测试UserController中的editUser方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception 异常
     */
    @Test
    public void testEditUser() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(userService.editUser(Mockito.any(EditUserForm.class))).thenReturn(i);
        EditUserForm editUserForm = new EditUserForm();
        editUserForm.setUserId(5);
        ObjectMapper mapper = new ObjectMapper();
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(editUserForm));
        // 发送请求，获取请求结果
        ResultActions perform = mvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        log.info("response:" + response.getContentAsString());
        // 断言
        /*System.out.println(response.getContentAsString());*/
        assertEquals(response.getContentAsString(), i.toString());
    }

    @Test
    public void testEditUserException() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(userService.editUser(Mockito.any(EditUserForm.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}");


        Throwable exception = assertThrows(NestedServletException.class, () -> {
            mvc.perform(request);
        });
        assertEquals(
                exception.getMessage(),"Request processing failed; nested exception is com.accenture.powerup.bookmng.exception.BusinessFailureException"
        );
    }
}
