package com.accenture.powerup.bookmng.entity;

import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 书籍信息实体类。
 */
@Alias("Book")
public class BookEntity extends BaseEntity {
    private Integer bookId;
    private String authorName;
    private String publisherName;
    private String bookName;
    private Integer quantity;

    public BookEntity() {
        super();
    }

    public BookEntity(Integer bookId, String authorName, String publisherName, String bookName) {
        this.bookId = bookId;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.bookName = bookName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "bookId=" + bookId +
                ", authorName='" + authorName + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}