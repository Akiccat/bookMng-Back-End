package com.accenture.powerup.bookmng.service.impl;

import com.accenture.powerup.bookmng.entity.BookRankingEntity;
import com.accenture.powerup.bookmng.entity.RankingEntity;
import com.accenture.powerup.bookmng.entity.UserRankingEntity;
import com.accenture.powerup.bookmng.repository.BookRepository;
import com.accenture.powerup.bookmng.repository.RankingRepository;
import com.accenture.powerup.bookmng.repository.UserRepository;
import com.accenture.powerup.bookmng.service.RankingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 排行榜业务实现层
 */
@Service
public class RankingServiceImpl implements RankingService {
    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取书籍以及用户排行榜
     *
     * @return 排行数组
     */
    @Override
    public HashMap<String,List<? extends RankingEntity>> getRanking() {
        HashMap<String,List<? extends RankingEntity>> rankingListMap = new HashMap<>();
        try {
            List<BookRankingEntity> booklist = rankingRepository.getBookRanking();
            List<UserRankingEntity> userlist = rankingRepository.getUserRanking();

            for (BookRankingEntity entity : booklist) {
                entity.setBookName(bookRepository.searchBooks(entity.getBookId()).getBookName());
                entity.setAuthorName(bookRepository.searchBooks(entity.getBookId()).getAuthorName());
            }

            for (UserRankingEntity entity : userlist){
                entity.setUserName(userRepository.getUser(entity.getUserId()).getUserName());
            }
            rankingListMap.put("booklist",booklist);
            rankingListMap.put("userlist",userlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rankingListMap;
    }
}
