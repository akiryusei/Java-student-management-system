package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.StudentMapper;
import org.example.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@SuppressWarnings("ALL")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    private Gson gson=new Gson();

    @GetMapping("/students")
    public String getStudents() {
        return gson.toJson(studentMapper.selectList(null));
    }
    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student){
        studentMapper.insert(student);
    }
    @PutMapping("/updateStudent")
    public void updateStudent(@RequestBody Student student){
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("sid",student.getSid());
        System.out.println(student);
        studentMapper.update(student,studentQueryWrapper);
    }
    @DeleteMapping("/deleteStudent")
    public void deleteStudent(@RequestBody Student student){
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("sid",student.getSid());
        System.out.println(student);
        studentMapper.delete(studentQueryWrapper);
    }

}
