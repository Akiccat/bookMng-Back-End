package com.accenture.powerup.bookmng.repository;

import com.accenture.powerup.bookmng.entity.BookRankingEntity;
import com.accenture.powerup.bookmng.entity.UserRankingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 排行榜Mapper映射器。
 */
@Repository
public interface RankingRepository {
    /**
     * 获取书籍排行榜
     */
    List<BookRankingEntity> getBookRanking();
    /**
     * 获取用户排行榜
     */
    List<UserRankingEntity> getUserRanking();
}
