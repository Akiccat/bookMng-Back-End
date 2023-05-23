package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotNull;

/**
 * 归还图书信息入力参数
 */
public class GetReturnBookForm {
    private Integer bookId;
    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
