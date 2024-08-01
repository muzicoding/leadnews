package com.muzicoding.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzicoding.model.article.dtos.ArticleDto;
import com.muzicoding.model.article.dtos.ArticleHomeDto;
import com.muzicoding.model.article.dtos.ArticleInfoDto;
import com.muzicoding.model.article.pojos.ApArticle;
import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.mess.ArticleVisitStreamMess;

public interface ApArticleService extends IService<ApArticle> {

    /**
     * 加载文章列表
     * @param dto
     * @param type 1 加载更多 2 加载最新
     * @return
     */
    public ResponseResult load(ArticleHomeDto dto, Short type);

    /**
     * 加载文章列表
     * @param dto
     * @param type 1 加载更多 2 加载最新
     * @param firstPage true 首页
     * @return
     */
    public ResponseResult load2(ArticleHomeDto dto, Short type, boolean firstPage);

    /**
     * 保存app端相关文章
     * @param dto
     * @return
     */
    public ResponseResult saveArticle(ArticleDto dto);

    /**
     * 加载文章详情 数据回显
     * @param dto
     * @return
     */
    public ResponseResult loadArticleBehavior(ArticleInfoDto dto);

    /**
     * 更新文章的分值 同时跟新缓存中的热点文章数据
     * @param mess
     */
    public void updateScore(ArticleVisitStreamMess mess);
}
