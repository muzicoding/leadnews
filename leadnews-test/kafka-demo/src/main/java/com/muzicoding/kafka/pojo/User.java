package com.muzicodingi.kafka.pojo;

import org.apache.kafka.common.protocol.types.Field;

public class User {

    private String username;

    private Integer age;

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
