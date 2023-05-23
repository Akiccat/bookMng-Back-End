package com.accenture.powerup.bookmng.entity;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * 书籍借阅实体类。
 */
public class BorrowEntity extends BaseEntity{
    private Integer userId;
    private Integer bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowEntity() {
        super();
    }

    public BorrowEntity(Integer userId, Integer bookId, LocalDate borrowDate, LocalDate returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
