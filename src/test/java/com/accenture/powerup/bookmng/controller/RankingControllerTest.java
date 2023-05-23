package com.accenture.powerup.bookmng.controller;

import com.accenture.powerup.bookmng.entity.BookRankingEntity;
import com.accenture.powerup.bookmng.entity.RankingEntity;
import com.accenture.powerup.bookmng.entity.UserRankingEntity;
import com.accenture.powerup.bookmng.service.RankingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RankingControllerTest {
    private static final Logger log = LoggerFactory.getLogger(LoginControllerTest.class);
    private MockMvc mvc;
    // 使用mockito提供的@Mock注解装饰外部业务层，模拟外部业务层依赖
    @Mock
    private RankingService rankingService;
    // 创建一个实例，其余用@Mock注解创建的mock将被注入到用该实例中。
    @InjectMocks
    private RankingController rankingController;
    // 在被@Test注解装饰的测试方法之前执行被@BeforeEach装饰的非静态方法，@BeforeEach为Junit5提供的注解
    @BeforeEach
    public void setUp() {
        // 执行以后，service自动注入到controller中。
        MockitoAnnotations.openMocks(this);
        // 构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(rankingController).build();
    }

    /**
     * 测试方法
     * <p>用于测试RankingController中的getRanking方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     */
    @Test
    public void testGetRanking() throws Exception {
        HashMap<String,List<? extends RankingEntity>> map = new HashMap<>();
        List<BookRankingEntity> booklist = new ArrayList<>();
        List<UserRankingEntity> userlist = new ArrayList<>();
        UserRankingEntity user = new UserRankingEntity("我",10,20);
        BookRankingEntity book = new BookRankingEntity("吃饭","吃好饭",10,100);
        booklist.add(book);
        userlist.add(user);
        map.put("booklist",booklist);
        map.put("userlist",userlist);
        // 模拟外部业务层依赖返回结果
        when(rankingService.getRanking()).thenReturn(map);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/ranking/retrieve")
                .contentType(MediaType.APPLICATION_JSON);
        /*.content("{}");*/

        // 发送请求，获取请求结果
        ResultActions perform = mvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        log.info("response:" + response.getContentAsString());

        //关于带不带引号的探索
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        //json 带引号
        System.out.println(response.getContentAsString());
        //json 带引号
        System.out.println(json);
        //HashMap<String,List<Object>>实体 不带引号
        System.out.println(getRankMapFormJson(response.getContentAsString()));
        //HashMap<String,List<Object>>实体 不带引号
        System.out.println(getRankMapFormJson(json));
        //实体转json 带引号
        System.out.println(mapper.writeValueAsString(getRankMapFormJson(response.getContentAsString())));
        //实体转json 带引号
        System.out.println(mapper.writeValueAsString(getRankMapFormJson(json).toString()));

        //断言
        assertEquals(getRankMapFormJson(response.getContentAsString()), getRankMapFormJson(json));
    }

    /**
     * 将字符串型Json数据转为Rank实体类。
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
    private HashMap<String,List<Object>> getRankMapFormJson(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, new TypeReference<HashMap<String,List<Object>>>(){});
    }
}
