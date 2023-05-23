package com.accenture.powerup.bookmng.service.impl;

import com.accenture.powerup.bookmng.entity.BookEntity;
import com.accenture.powerup.bookmng.entity.ReturnBookEntity;
import com.accenture.powerup.bookmng.repository.BookRepository;
import com.accenture.powerup.bookmng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 图书业务实现层。
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

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
    @Override
    public List<BookEntity> searchBooks(int bookId, String authorName, String publisherName, String bookName) {
        List<BookEntity> list = null;
        try {
            list = bookRepository.searchBooks(bookId,authorName,publisherName,bookName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 书籍查找
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookId 书籍ID
     * @param userId 用户id
     * @param borrowDate 借阅日期
     * @param returnDate 归还日期
     */
    @Override
    public int borrowBook(int userId, int bookId, LocalDate borrowDate, LocalDate returnDate) {
        try {
            borrowDate = LocalDate.now();
            return bookRepository.borrowBook(userId, bookId, borrowDate, returnDate);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 书籍添加
     * <p>根据用户输入的信息借阅图书</p>
     *
     * @param bookName 书籍名称
     * @param publisherName 出版社名
     * @param authorName 作者名
     * @param quantity 数量
     */
    @Override
    public int addBook(String bookName, String publisherName, String authorName, Integer quantity) {
        try {
            return bookRepository.addBook(bookName,publisherName,authorName,quantity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


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
    @Override
    public int editBook(Integer bookId, String bookName, String publisherName, String authorName, Integer quantity) {

        try {
            BookEntity book = bookRepository.searchBooks(bookId);
            if (bookName != null){
                book.setBookName(bookName);
            }
            else if(publisherName != null){
                book.setPublisherName(publisherName);
            }
            else if(authorName != null){
                book.setAuthorName(authorName);
            }
            else if(quantity != null){
                book.setQuantity(quantity);
            }
            return bookRepository.editBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 书籍删除
     * <p>根据用户输入的信息删除图书</p>
     *
     * @param bookId 书籍ID
     */
    @Override
    public int deleteBook(Integer bookId) {
        try {
            return bookRepository.deleteBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 书籍归还
     * <p>根据用户输入的信息归还图书</p>
     *
     * @param historyId 借阅历史ID
     */
    @Override
    public int returnBook(Integer historyId) {
        try {
            LocalDate returnDate = LocalDate.now();
            return bookRepository.returnBook(historyId,returnDate);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取归还列表
     * <p>根据用户输入的信息归还图书</p>
     *
     * @param bookId 书籍ID
     * @param userId 用户ID
     */
    @Override
    public List<ReturnBookEntity> getReturnBooks(Integer bookId, Integer userId) {
        try {
            List<ReturnBookEntity> list = bookRepository.getReturnBooks(bookId,userId);
            for (ReturnBookEntity returnBookEntity : list) {
                returnBookEntity.setBookName(bookRepository.searchBooks(returnBookEntity.getBookId()).getBookName());
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
