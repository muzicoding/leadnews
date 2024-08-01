package com.muzicodingi.kafka.sample;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 生产者
 */
public class ProducterQuickStart {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1.kafka连接配置信息
        Properties properties = new Properties();
        // kafka连接地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.10:9092");
        //key和value序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // ack消息确认机制
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);

        // 数据压缩 snappy, lz4, gzip
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        // 2.创建kafka生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 3.发送消息
        /**
         * 第一个参数：topic
         * 第二个参数：消息的key
         * 第三个参数：消息的value
         */
        for (int i = 0; i <10; i++) {
            ProducerRecord<String, String> kvProducerRecord = new ProducerRecord<String, String>("itcast-topic-input", "hello kafka");
            producer.send(kvProducerRecord);

        }
        // 同步发送消息
//        RecordMetadata recordMetadata = producer.send(kvProducerRecord).get();
//        System.out.println(recordMetadata.offset());

//        // 异步消息发送
//        producer.send(kvProducerRecord, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                if (e != null) {
//                    System.out.println("记录异常信息到日志表中");
//                }
//                System.out.println(recordMetadata.offset());
//            }
//        });

        // 4.关闭消息通道 必须关闭 否则不成功
        producer.close();

    }
}
