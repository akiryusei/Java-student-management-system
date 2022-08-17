package org.example.controller;

import com.google.gson.Gson;
import lombok.val;
import org.example.mapper.StudentMapper;
import org.example.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//跨域问题
//三个地方只要有一个不一样，就会导致跨域，
//任何地方的发过来都能响应
@CrossOrigin(origins = {"*","null"})
//消除警告
@SuppressWarnings("all")
@RestController
public class Controller {
    //自动接线，自动去找mapper，拿
    @Autowired
    StudentMapper studentMapper;

    private Gson gson = new Gson();

    //get请求
    @GetMapping("/students")//路由的名字
    public String getStudents()
    {
        //查所有的学生
        List<Student> students = studentMapper.selectList(null);
        //将list students转换为字符串
        return gson.toJson(students);
    }

    //增删改查使用post
    @PostMapping("/add")
    public void addStudent(@RequestBody Student student)
    {
        studentMapper.insert(student);
    }

    @PostMapping("/delete")
    public void removeStudent(@RequestBody Student student)
    {
        studentMapper.deleteById(student);
    }

    @PostMapping("/update")
    //requestbody 请求体内容转成 student
    public void updateStudent(@RequestBody Student student)
    {
        //前端拿到内容后 post请求 更新后端
        studentMapper.updateById(student);
    }
}
