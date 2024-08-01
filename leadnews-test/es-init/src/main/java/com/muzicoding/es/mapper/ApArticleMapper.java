package com.muzicoding.es.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muzicoding.es.pojo.SearchArticleVo;
import com.muzicoding.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    public List<SearchArticleVo> loadArticleList();

}
