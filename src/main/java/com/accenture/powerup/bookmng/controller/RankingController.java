package com.accenture.powerup.bookmng.controller;

import com.accenture.powerup.bookmng.entity.RankingEntity;
import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.accenture.powerup.bookmng.requestdto.ReturnBookForm;
import com.accenture.powerup.bookmng.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/ranking")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    /**
     * 图书以及用户借阅信息排行榜。
     * <p>查询排行榜信息</p>
     *
     */
    @PostMapping("/retrieve")
    public HashMap<String,List<? extends RankingEntity>> getRanking(){
       return rankingService.getRanking();
    }
}
