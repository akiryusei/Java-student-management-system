package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("courses")
public class Course {
    private String cid;
    private String cname;
    private int credit;
}
