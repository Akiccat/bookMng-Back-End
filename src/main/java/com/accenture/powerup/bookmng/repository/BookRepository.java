package com.accenture.powerup.bookmng.repository;

import com.accenture.powerup.bookmng.entity.BookEntity;
import com.accenture.powerup.bookmng.entity.ReturnBookEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 书籍业务Mapper映射器。
 */
@Repository
public interface BookRepository {
    /**
     * 书籍查找
     * <p>根据用户输入的信息查找图书</p>
     *
     * @param bookId 书籍ID
     * @param authorName 作者名称
     * @param publisherName 出版社名称
     * @param bookName 书名
     * @return null
     */
    public List<BookEntity> searchBooks(int bookId, String authorName, String publisherName, String bookName);

    /**
     * 书籍查找
     * <p>根据用户输入的信息查找图书</p>
     *
     * @param bookId 书籍ID
     * @return BookEntity
     */
    public BookEntity searchBooks(Integer bookId);

    /**
     * 书籍查找
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookId     书籍ID
     * @param userId     用户id
     * @param borrowDate 借阅日期
     * @param returnDate 归还日期
     * @return
     */
    public int borrowBook(int userId, int bookId, LocalDate borrowDate, LocalDate returnDate);

    /**
     * 书籍添加
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookName      书籍ID
     * @param publisherName 出版社名
     * @param authorName    作者名
     * @param quantity      数量
     * @return
     */
    public int addBook(String bookName, String publisherName, String authorName, Integer quantity);

    /**
     * 书籍更改
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param book 书籍ID
     * @return
     */
    public int editBook(BookEntity book);

    /**
     * 书籍删除
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookId 书籍ID
     * @return
     */
    public int deleteBook(Integer bookId);

    /**
     * 书籍归还
     * <p>根据用户输入的信息归还图书</p>
     *
     * @param historyId 借阅ID
     * @return
     */
    public int returnBook(Integer historyId, LocalDate returnDate);

    /**
     * 获取归还列表
     * <p>根据用户输入的信息归还图书</p>
     *
     * @param bookId 书籍ID
     * @param userId 用户ID
     */
    public List<ReturnBookEntity> getReturnBooks(Integer bookId, Integer userId);
}
