package com.muzicodingi.kafka.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@Slf4j
public class KafkaStreamHelloListener {

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        // 创建stream对象，同时指定从哪个topic中接受消息
        KStream<String, String> stream = streamsBuilder.stream("itcast-topic-input");

        // 处理消息的value
        stream.flatMapValues(new ValueMapper<String, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(String value) {
                        String[] valArry = value.split(" ");
                        return Arrays.asList(valArry);
                    }
                })
                // 按照value进行聚合
                .groupBy((key, value)->value)
                // 时间窗口
                .windowedBy(TimeWindows.of(Duration.ofSeconds(10)))
                // 统计单词的个数
                .count()
                // 转换为kstream
                .toStream()
                .map((key, value)->{
                    System.out.println("key:"+key+",vlaue:"+value);
                    return new KeyValue<>(key.key().toString(), value.toString());
                })
                // 发送消息
                .to("itcast-topic-out");
        return stream;
    }
}
