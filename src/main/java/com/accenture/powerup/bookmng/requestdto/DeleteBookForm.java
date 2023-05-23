package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotNull;

/**
 * 删除图书信息入力参数
 */
public class DeleteBookForm {
    @NotNull(message = "BookId不可以为NULL")
    private Integer bookId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
