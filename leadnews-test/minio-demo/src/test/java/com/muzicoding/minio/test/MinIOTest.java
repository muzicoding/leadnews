package com.muzicoding.minio.test;

import com.muzicoding.file.service.FileStorageService;
import com.muzicoding.minio.MinIOApplication;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest(classes = MinIOApplication.class)
@RunWith(SpringRunner.class)
public class MinIOTest {

//    @Autowired
//    private FileStorageService fileStorageService;
//
////    把list。html文件上传到minio中，并且可以再浏览器中访问
//    @Test
//    public void test() throws FileNotFoundException {
//
//        FileInputStream fileInputStream = new FileInputStream("E:\\list.html");
//
//        String path = fileStorageService.uploadHtmlFile("", "list.html", fileInputStream);
//
//        System.out.println(path);
//    }

    /**
     * 把list。html文件上传到minio中，并且可以再浏览器中访问
     * @param args
     */
    public static void main(String[] args) {

        try {
            FileInputStream fileInputStream = new FileInputStream("E:\\BaiduNetdiskDownload\\模板文件\\plugins\\js\\axios.min.js");

            // 1.获取minio的连接信息，创建一个minio的客户端
            MinioClient minioClient = MinioClient.builder().
                    credentials("minio", "minio123")
                    .endpoint("http://192.168.1.10:9000").build();

            // 2.上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("plugins/js/axios.min.js")  // 文件名称
                    .contentType("text/javascript")   // 文件类型
                    .bucket("leadnews")   // 桶名称，与minio创建桶一致
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);

            // 访问路径
//            System.out.println("http://192.168.1.10:9000/leadnews/list.html");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
