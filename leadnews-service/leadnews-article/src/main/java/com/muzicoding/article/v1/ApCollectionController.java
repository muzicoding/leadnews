package com.muzicoding.article.v1;

import com.muzicoding.article.service.ApCollectionService;
import com.muzicoding.model.article.dtos.CollectionBehaviorDto;
import com.muzicoding.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/collection_behavior")
public class ApCollectionController {

    @Autowired
    private ApCollectionService apCollectionService;


    @PostMapping
    public ResponseResult collection(@RequestBody CollectionBehaviorDto dto) {
        return apCollectionService.collection(dto);
    }
}