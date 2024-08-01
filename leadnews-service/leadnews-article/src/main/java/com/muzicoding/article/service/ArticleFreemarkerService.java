package com.muzicoding.article.service;

import com.muzicoding.model.article.pojos.ApArticle;

/**
 * 生成静态模板上传到minIO中
 */
public interface ArticleFreemarkerService {
    public void buildArticleToMinIO(ApArticle apArticle, String content);
}
