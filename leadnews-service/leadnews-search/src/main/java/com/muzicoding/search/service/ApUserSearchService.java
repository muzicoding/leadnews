package com.muzicoding.search.service;

import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.search.dtos.HistorySearchDto;

public interface ApUserSearchService {

    /**
     * 保存用户搜索历史
     * @param keyword
     * @param userId
     */
    public void insert(String keyword, Integer userId);

    /**
     * 查询搜索历史
     * @return
     */
    public ResponseResult findUserSearch();

    /**
     删除搜索历史
     @param historySearchDto
     @return
     */
    ResponseResult delUserSearch(HistorySearchDto historySearchDto);
}
