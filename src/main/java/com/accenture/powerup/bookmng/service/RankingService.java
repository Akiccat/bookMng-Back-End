package com.accenture.powerup.bookmng.service;

import com.accenture.powerup.bookmng.entity.RankingEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 排行业务接口。
 */
public interface RankingService {
    /**
     * 获取书籍以及用户排行榜
     *
     * @return 排行数组
     */
    HashMap<String,List<? extends RankingEntity>> getRanking();
}
