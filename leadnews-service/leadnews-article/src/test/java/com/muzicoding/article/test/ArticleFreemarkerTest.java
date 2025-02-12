package com.muzicoding.article.test;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.muzicoding.article.ArticleApplication;
import com.muzicoding.article.mapper.ApArticleContentMapper;
import com.muzicoding.article.service.ApArticleService;
import com.muzicoding.file.service.FileStorageService;
import com.muzicoding.model.article.pojos.ApArticle;
import com.muzicoding.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleFreemarkerTest {

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApArticleService apArticleService;

    @Test
    public void test() throws Exception {

        // 已知文章id
        // 1.获取文章内容
        ApArticleContent apArticleContent = apArticleContentMapper.
                selectOne(Wrappers.<ApArticleContent>lambdaQuery().
                        eq(ApArticleContent::getArticleId, "1383828014629179393"));
        if (apArticleContent != null && !StringUtils.isBlank(apArticleContent.getContent())) {

            // 2.文章内容通过Freemarker上传html文件
            Template template = configuration.getTemplate("article.ftl");

            // 数据模型
            Map<String, Object> content = new HashMap<>();
            content.put("content", JSONArray.parseArray(apArticleContent.getContent()));
            StringWriter out = new StringWriter();

            // 合成
            template.process(content, out);

            // 3.把html文件上传到minio中
            InputStream in = new ByteArrayInputStream(out.toString().getBytes());
            String path = fileStorageService.
                    uploadHtmlFile("", apArticleContent.getArticleId() + ".html", in);

            // 4.修改ap_article表，保存static_url字段
            apArticleService.update(Wrappers.<ApArticle>lambdaUpdate().
                    eq(ApArticle::getId, apArticleContent.getArticleId())
                    .set(ApArticle::getStaticUrl, path));
        }



    }
}
