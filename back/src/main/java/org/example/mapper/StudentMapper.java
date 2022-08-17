package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.Student;

public interface StudentMapper extends BaseMapper<Student> {
    //接口继承了basemapper
    //会有基本的方法

}
