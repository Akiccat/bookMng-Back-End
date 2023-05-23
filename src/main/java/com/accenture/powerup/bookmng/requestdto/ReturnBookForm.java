package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotNull;

/**
 * 归还图书信息入力参数
 */
public class ReturnBookForm {
    @NotNull(message = "历史ID不能为空")
    private Integer historyId;
    private Integer bookId;
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

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }
}
