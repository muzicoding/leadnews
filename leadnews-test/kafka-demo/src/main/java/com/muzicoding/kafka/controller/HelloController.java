package com.muzicodingi.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.muzicodingi.kafka.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/hello")
    public String hello() {
//        kafkaTemplate.send("itcast-topic", "hello");

        User user = new User();
        user.setUsername("小王");
        user.setAge(18);

        kafkaTemplate.send("user-topic", JSON.toJSONString(user));

        return "ok";

    }

}
