package com.accenture.powerup.bookmng.service;

import com.accenture.powerup.bookmng.entity.BookEntity;
import com.accenture.powerup.bookmng.entity.ReturnBookEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * 图书业务接口。
 */
public interface BookService {

    /**
     * 书籍查找
     * <p>根据用户输入的信息查找图书</p>
     *
     * @param bookId 书籍ID
     * @param authorName 作者名称
     * @param publisherName 出版社名称
     * @param bookName 书名
     * @return List<BookEntity>
     */
    public List<BookEntity> searchBooks(int bookId, String authorName, String publisherName, String bookName);

    /**
     * 书籍查找
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookId 书籍ID
     * @param userId 用户id
     * @param borrowDate 借阅日期
     * @param returnDate 归还日期
     */
    public int borrowBook(int userId, int bookId, LocalDate borrowDate, LocalDate returnDate);

    /**
     * 书籍添加
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookName 书籍名称
     * @param publisherName 出版社名
     * @param authorName 作者名
     * @param quantity 数量
     */
    public int addBook(String bookName, String publisherName, String authorName, Integer quantity);

    /**
     * 书籍更改
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookId 书籍ID
     * @param bookName 书籍名称
     * @param publisherName 出版社名
     * @param authorName 作者名
     * @param quantity 数量
     */
    public int editBook(Integer bookId, String bookName, String publisherName, String authorName, Integer quantity);

    /**
     * 书籍删除
     * <p>根据用户输入的信息删除图书</p>
     *
     * @param bookId 书籍ID
     */
    public int deleteBook(Integer bookId);

    /**
     * 书籍归还
     * <p>根据用户输入的信息归还图书</p>
     *
     * @param historyId 借阅历史ID
     */
    public int returnBook(Integer historyId);

    /**
     * 获取归还列表
     * <p>根据用户输入的信息归还图书</p>
     *
     * @param bookId 书籍ID
     * @param userId 用户ID
     */
    public List<ReturnBookEntity> getReturnBooks(Integer bookId, Integer userId);
}
