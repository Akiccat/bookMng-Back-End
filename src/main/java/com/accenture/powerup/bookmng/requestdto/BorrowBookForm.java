package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 借阅图书参数
 */
public class BorrowBookForm {
    @NotNull(message = "用户ID不可以为NULL")
    private Integer userId;
    @NotNull(message = "图书ID不可以为NULL")
    private Integer bookId;
    @NotNull(message = "借阅日期")
    private LocalDate borrowDate;
    private LocalDate returnDate;

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
