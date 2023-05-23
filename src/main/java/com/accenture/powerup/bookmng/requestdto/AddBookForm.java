package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotNull;

/**
 * 添加图书信息入力参数
 */
public class AddBookForm {
    @NotNull(message = "作者名不可以为空")
    String authorName;
    @NotNull(message = "出版社名不可以为空")
    String publisherName;
    @NotNull(message = "书名不可以为空")
    String bookName;
    @NotNull(message = "数量不可以为空")
    Integer quantity;

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
