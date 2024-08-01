package com.muzicoding.article.job;

import com.muzicoding.article.service.HotArticleService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ComputerHotArticleJob {

    @Autowired
    private HotArticleService hotArticleService;

    @XxlJob("computeHotArticleJob")
    public void handle() {
        log.info("热文章分值计算调度任务开始执行。。。");
        hotArticleService.computeHotArticle();
        log.info("热文章分值计算调度任务执行结束。。。");

    }
}
