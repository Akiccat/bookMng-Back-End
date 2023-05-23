package com.accenture.powerup.bookmng.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.accenture.powerup.bookmng.entity.BookEntity;
import com.accenture.powerup.bookmng.entity.GetLoginLogEntity;
import com.accenture.powerup.bookmng.entity.LoginLogEntity;
import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.accenture.powerup.bookmng.entity.UserEntity;
import com.accenture.powerup.bookmng.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * LoginController测试类。
 * <p>使用junit5 + mockito完成</p>
 *
 * @author mingze.xia
 */
@SpringBootTest
public class LoginControllerTest {
    private static final Logger log = LoggerFactory.getLogger(LoginControllerTest.class);
    private MockMvc mvc;
    // 使用mockito提供的@Mock注解装饰外部业务层，模拟外部业务层依赖
    @Mock
    private LoginService loginService;
    // 创建一个实例，其余用@Mock注解创建的mock将被注入到用该实例中。
    @InjectMocks
    private LoginController loginController;
    // 在被@Test注解装饰的测试方法之前执行被@BeforeEach装饰的非静态方法，@BeforeEach为Junit5提供的注解
    @BeforeEach
    public void setUp() {
        // 执行以后，service自动注入到controller中。
        MockitoAnnotations.openMocks(this);
        // 构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }
    /**
     * 测试方法
     * <p>用于测试LoginController中的Login方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        UserEntity user = new UserEntity(1, "admin");
        // 模拟外部业务层依赖返回结果
        when(loginService.login(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(user);

        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\": \"admin\", \"password\": \"admin\"}");

        // 发送请求，获取请求结果
        ResultActions perform = mvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        log.info("response:" + response.getContentAsString());
        // 断言
        assertEquals(getUserEntityFormJson(response.getContentAsString()).getUserId(), user.getUserId());
        assertEquals(getUserEntityFormJson(response.getContentAsString()).getUserName(), user.getUserName());

    }

    @Test
	public void testLoginException() throws Exception {
		UserEntity user = new UserEntity(1, "admin");
		// 模拟外部业务层依赖返回结果
		when(loginService.login(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(user);

		// 构建请求
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_JSON).content("{\"userName\": \"admin\", \"password\": \" \"}");

		Throwable exception = assertThrows(NestedServletException.class, () -> {
			mvc.perform(request);
		});
		assertEquals(
				exception.getMessage(),"Request processing failed; nested exception is com.accenture.powerup.bookmng.exception.BusinessFailureException"
				);
	}

    /**
     * 测试方法
     * <p>用于getLog。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     */
    @Test
    public void testGetLog() throws Exception {
        List<GetLoginLogEntity> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.parse("2023-04-11T16:49:29");
        GetLoginLogEntity loginLog = new GetLoginLogEntity(1,5, time,"admin");
        list.add(loginLog);
        // 模拟外部业务层依赖返回结果
        when(loginService.getLogs()).thenReturn(list);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/getLogs")
                .contentType(MediaType.APPLICATION_JSON);
                /*.content("{}");*/

        // 发送请求，获取请求结果
        ResultActions perform = mvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        log.info("response:" + response.getContentAsString());
        assertEquals(getLogListFormJson(response.getContentAsString()).get(0).toString(), loginLog.toString());
    }


    /**
     * 将字符串型Json数据转为用户实体类。
     * <p>
     * 由于该示例仅为配合展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @param json 字符串型Json数据
     * @return 用户实体类
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private UserEntity getUserEntityFormJson(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, UserEntity.class);
    }

    /**
     * 将字符串型Json数据转为log实体类。
     * <p>
     * 由于该示例仅为配合展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @param json 字符串型Json数据
     * @return 用户实体类
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private List<GetLoginLogEntity> getLogListFormJson(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, new TypeReference<List<GetLoginLogEntity>>(){});
    }
}
