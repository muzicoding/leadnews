package com.muzicoding.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.muzicoding.article.mapper.ApArticleContentMapper;
import com.muzicoding.article.service.ApArticleService;
import com.muzicoding.article.service.ArticleFreemarkerService;
import com.muzicoding.common.constants.ArticleConstants;
import com.muzicoding.file.service.FileStorageService;
import com.muzicoding.model.article.pojos.ApArticle;
import com.muzicoding.model.search.vos.SearchArticleVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class ArticleFreemarkerServiceImpl implements ArticleFreemarkerService {
    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApArticleService apArticleService;

    @Override
    @Async
    public void buildArticleToMinIO(ApArticle apArticle, String content) {
        // 已知文章id
        // 1.获取文章内容
        if (!StringUtils.isBlank(content)) {

            // 2.文章内容通过Freemarker上传html文件
            Template template = null;
            StringWriter out = new StringWriter();

            try {
                template = configuration.getTemplate("article.ftl");

                // 数据模型
                Map<String, Object> contentDataModel = new HashMap<>();
                contentDataModel.put("content", JSONArray.parseArray(content));

                // 合成
                template.process(contentDataModel, out);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }



            // 3.把html文件上传到minio中
            InputStream in = new ByteArrayInputStream(out.toString().getBytes());
            String path = fileStorageService.
                    uploadHtmlFile("", apArticle.getId() + ".html", in);

            // 4.修改ap_article表，保存static_url字段
            apArticleService.update(Wrappers.<ApArticle>lambdaUpdate().
                    eq(ApArticle::getId, apArticle.getId())
                    .set(ApArticle::getStaticUrl, path));

            // 发送消息 创建索引
            creatArticleESIndex(apArticle, content, path);
        }
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息 创建索引
     * @param apArticle
     * @param content
     * @param path
     */
    private void creatArticleESIndex(ApArticle apArticle, String content, String path) {

        SearchArticleVo vo = new SearchArticleVo();
        BeanUtils.copyProperties(apArticle, vo);

        vo.setContent(content);
        vo.setStaticUrl(path);

        kafkaTemplate.send(ArticleConstants.ARTICLE_ES_SYNC_TOPIC, JSON.toJSONString(vo));
    }
}
