package com.accenture.powerup.bookmng.controller;

import com.accenture.powerup.bookmng.entity.BookEntity;
import com.accenture.powerup.bookmng.entity.BorrowEntity;
import com.accenture.powerup.bookmng.entity.ReturnBookEntity;
import com.accenture.powerup.bookmng.entity.UserEntity;
import com.accenture.powerup.bookmng.service.BookService;
import com.accenture.powerup.bookmng.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTest {
    private static final Logger log = LoggerFactory.getLogger(LoginControllerTest.class);
    private MockMvc mvc;
    // 使用mockito提供的@Mock注解装饰外部业务层，模拟外部业务层依赖
    @Mock
    private BookService bookService;
    // 创建一个实例，其余用@Mock注解创建的mock将被注入到用该实例中。
    @InjectMocks
    private BookController bookController;
    // 在被@Test注解装饰的测试方法之前执行被@BeforeEach装饰的非静态方法，@BeforeEach为Junit5提供的注解
    @BeforeEach
    public void setUp() {
        // 执行以后，service自动注入到controller中。
        MockitoAnnotations.openMocks(this);
        // 构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    /**
     * 测试方法
     * <p>用于测试BookController中的searchBooks方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception
     */
    @Test
    public void testSearchBooks() throws Exception {
        List<BookEntity> list = new ArrayList<>();
        BookEntity book = new BookEntity(5,"茨威格","武汉人民出版社","人类群星闪耀时");
        list.add(book);
        // 模拟外部业务层依赖返回结果
        when(bookService.searchBooks(Mockito.any(int.class), Mockito.any(String.class), Mockito.any(String.class),Mockito.any(String.class))).thenReturn(list);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\": 5, \"bookName\": \"人类群星闪耀时\",\"authorName\": \"茨威格\",\"publisherName\": \"武汉人民出版社\"}");

        // 发送请求，获取请求结果
        ResultActions perform = mvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        log.info("response:" + response.getContentAsString());
        // 断言
        assertEquals(getBookEntityFormJson(response.getContentAsString()).get(0).toString(), book.toString());
    }

    @Test
    public void testSearchBooksException() throws Exception {
        List<BookEntity> list = new ArrayList<>();
        BookEntity book = new BookEntity(5,"茨威格","武汉人民出版社","人类群星闪耀时");
        list.add(book);
        // 模拟外部业务层依赖返回结果
        when(bookService.searchBooks(Mockito.any(int.class), Mockito.any(String.class), Mockito.any(String.class),Mockito.any(String.class))).thenReturn(list);

        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/search")
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
     * <p>用于测试BookController中的borrowBook方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception 异常
     */
    @Test
    public void testBorrowBook() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.borrowBook(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(LocalDate.class),Mockito.any(LocalDate.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": 10,\"bookId\": 201,\"borrowDate\":\"2023-05-08\",\"returnDate\":\"2023-05-08\"}");

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
    public void testBorrowBookException() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.borrowBook(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(LocalDate.class),Mockito.any(LocalDate.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\": 201,\"borrowDate\":\"2023-05-08\",\"returnDate\":\"2023-05-08\"}");


        Throwable exception = assertThrows(NestedServletException.class, () -> {
            mvc.perform(request);
        });
        assertEquals(
                exception.getMessage(),"Request processing failed; nested exception is com.accenture.powerup.bookmng.exception.BusinessFailureException"
        );
    }

    /**
     * 测试方法
     * <p>用于测试BookController中的bookInformationInsert方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception 异常
     */
    @Test
    public void testBookInformationInsert() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.addBook(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),Mockito.any(Integer.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"authorName\": \"吃饭\",\"publisherName\": \"多吃点\",\"bookName\":\"吃饭的学问\",\"quantity\":50}");

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
    public void testBookInformationInsertException() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.addBook(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),Mockito.any(Integer.class))).thenReturn(i);        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/add")
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
     * <p>用于测试BookController中的bookInformationInsert方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception 异常
     */
    @Test
    public void testBookInformationUpdate() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.editBook(
                Mockito.any(Integer.class),
                Mockito.any(String.class),
                Mockito.any(String.class),
                Mockito.any(String.class),
                Mockito.any(Integer.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\": \"5\",\"authorName\": \"吃饭\",\"publisherName\": \"多吃点\",\"bookName\":\"吃饭的学问\",\"quantity\":50}");

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
    public void testBookInformationUpdateException() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.editBook(Mockito.any(Integer.class), Mockito.any(String.class), Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class))).thenReturn(i);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/edit")
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
     * <p>用于测试BookController中的bookInformationDelete方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception 异常
     */
    @Test
    public void testBookInformationDelete() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.deleteBook(Mockito.any(Integer.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\": \"5\"}");

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
    public void testBookInformationDeleteException() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.deleteBook(Mockito.any(Integer.class))).thenReturn(i);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/delete")
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
     * <p>用于测试BookController中的return方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception 异常
     */
    @Test
    public void testBookReturn() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.returnBook(Mockito.any(Integer.class))).thenReturn(i);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/return")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"historyId\": \"5\"}");

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
    public void testBookReturnException() throws Exception {
        Integer i = 1;
        // 模拟外部业务层依赖返回结果
        when(bookService.returnBook(Mockito.any(Integer.class))).thenReturn(i);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/return")
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
     * <p>用于测试BookController中的getBookReturn方法。</p>
     * <p>注意，测试方法必须为public void 方法， 即公开且无返回值方法。</p>
     * <p>
     * 由于该示例仅展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @throws Exception
     */
    @Test
    public void testGetBookReturn() throws Exception {
        List<ReturnBookEntity> list = new ArrayList<>();
        LocalDate borrowDate = LocalDate.parse("2023-05-08");
        ReturnBookEntity book = new ReturnBookEntity(5,10,"吃饭",borrowDate);
        list.add(book);
        // 模拟外部业务层依赖返回结果
        when(bookService.getReturnBooks(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(list);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/getReturn")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\": 10, \"userId\": 5}");

        // 发送请求，获取请求结果
        ResultActions perform = mvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        log.info("response:" + response.getContentAsString());
        // 断言
        assertEquals(getReturnBookEntityFormJson(response.getContentAsString()).get(0).toString(), book.toString());
    }

    @Test
    public void testGetBookReturnException() throws Exception {
        List<ReturnBookEntity> list = new ArrayList<>();
        LocalDate borrowDate = LocalDate.parse("2023-05-08");
        ReturnBookEntity book = new ReturnBookEntity(5,10,"吃饭",borrowDate);
        list.add(book);
        // 模拟外部业务层依赖返回结果
        when(bookService.getReturnBooks(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(list);
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/book/getReturn")
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
     * 将字符串型Json数据转为图书实体类。
     * <p>
     * 由于该示例仅为配合展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @param json 字符串型Json数据
     * @return 用户实体类
     * @throws JsonMappingException 异常
     * @throws JsonProcessingException 异常
     */
    private List<BookEntity> getBookEntityFormJson(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, new TypeReference<List<BookEntity>>(){});
    }

    /**
     * 将字符串型Json数据转为归还图书实体类。
     * <p>
     * 由于该示例仅为配合展示Junit使用，所以方法直接throw异常。<br>
     * 实际开发过程中应使用try-catch捕获异常并加以处理。
     * </p>
     *
     * @param json 字符串型Json数据
     * @return 用户实体类
     * @throws JsonMappingException 异常
     * @throws JsonProcessingException 异常
     */
    private List<ReturnBookEntity> getReturnBookEntityFormJson(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, new TypeReference<List<ReturnBookEntity>>(){});
    }

}
