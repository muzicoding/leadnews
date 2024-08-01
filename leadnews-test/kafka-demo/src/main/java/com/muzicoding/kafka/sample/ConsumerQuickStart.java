package com.muzicodingi.kafka.sample;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * 消费者
 */
public class ConsumerQuickStart {

    public static void main(String[] args) {

        // 1.kafka的配置信息
        Properties properties = new Properties();
        // 连接地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.10:9092");
        // key和value的反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // 设置消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");

        // 手动提交偏移量
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        // 2.创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 3。订阅主题
        consumer.subscribe(Collections.singletonList("itcast-topic-out"));

        // 同步提交和异步提交偏移量
        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.key());
                    System.out.println(consumerRecord.value());
                    System.out.println(consumerRecord.offset());
                    System.out.println(consumerRecord.partition());
                }
                // 异步提交偏移量
                consumer.commitAsync();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 同步提交
            consumer.commitSync();
        }

        //4。拉取消息
//        while (true) {
//            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
//            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
//                System.out.println(consumerRecord.key());
//                System.out.println(consumerRecord.value());
//                System.out.println(consumerRecord.offset());
//                System.out.println(consumerRecord.partition());

//                try {
//                    // 同步提交偏移量
//                    consumer.commitSync();
//                } catch (CommitFailedException e) {
//                    System.out.println("提交失败");
//                }
//            }
            // 异步方式提交偏移量
//            consumer.commitAsync(new OffsetCommitCallback() {
//                @Override
//                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
//                    if (e != null) {
//                        System.out.println("记录错误提交偏移量"+ map + "异常信息为" + e);
//                    }
//                }
//            });

//        }
    }
}
