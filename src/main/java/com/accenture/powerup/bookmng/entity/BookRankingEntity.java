package com.accenture.powerup.bookmng.entity;

import org.apache.ibatis.type.Alias;
/**
 * 书籍排行实体类。
 */
@Alias("BookRanking")
public class BookRankingEntity extends RankingEntity {
    String bookName;
    String authorName;
    Integer bookId;
    Integer borrowCount;

    public BookRankingEntity() {
    }

    public BookRankingEntity(String bookName, String authorName, Integer bookId, Integer borrowCount) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookId = bookId;
        this.borrowCount = borrowCount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    @Override
    public String toString() {
        return "BookRankingEntity{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", bookId=" + bookId +
                ", borrowCount=" + borrowCount +
                '}';
    }
}
