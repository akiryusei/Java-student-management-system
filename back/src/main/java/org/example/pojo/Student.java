package org.example.pojo;
import lombok.Data;

@Data
public class Student {
    //数据表结构
    private Long id;
    private String number;
    private String name;
    private int age;
    private int chi;
    private int math;
    private int eng;
}