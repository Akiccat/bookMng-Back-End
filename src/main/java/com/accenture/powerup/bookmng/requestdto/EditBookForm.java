package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotNull;

/**
 * 修改图书信息入力参数
 */
public class EditBookForm {
    @NotNull(message = "图书ID不能为NULL")
    Integer bookId;
    @NotNull(message = "作者不能为NULL")
    String authorName;
    @NotNull(message = "出版社不能为NULL")
    String publisherName;
    @NotNull(message = "书名不能为NULL")
    String bookName;
    @NotNull(message = "数量不能为NULL")
    Integer quantity;

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
}
