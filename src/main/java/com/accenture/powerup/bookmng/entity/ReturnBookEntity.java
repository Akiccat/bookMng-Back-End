package com.accenture.powerup.bookmng.entity;

import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
/**
 * 归还书籍信息实体类。
 */
@Alias("ReturnBook")
public class ReturnBookEntity {
    Integer historyId;
    Integer bookId;
    String bookName;
    LocalDate borrowDate;

    public ReturnBookEntity() {

    }

    public ReturnBookEntity(Integer historyId, Integer bookId, String bookName, LocalDate borrowDate) {
        this.historyId = historyId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Override
    public String toString() {
        return "ReturnBookEntity{" +
                "historyId=" + historyId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
