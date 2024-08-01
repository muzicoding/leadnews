package com.muzicoding.apis.article.fallback;

import com.muzicoding.apis.article.IArticleClient;
import com.muzicoding.model.article.dtos.ArticleDto;
import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }
}
