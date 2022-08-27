package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.CoursesMapper;
import org.example.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("all")
@CrossOrigin("*")
@RestController
public class CoursesController {
    @Autowired
    private CoursesMapper coursesMapper;

    private Gson gson=new Gson();
    @GetMapping("/courses")
    public String getCourses(){
        return gson.toJson(coursesMapper.selectList(null));
    }

    @PostMapping("addCourse")
    public void addCourse(@RequestBody Course courses) {
        coursesMapper.insert(courses);
    }
    @PutMapping("/updateCourse")
    public void updateCourse(@RequestBody Course course){
        QueryWrapper<Course> coursesQueryWrapper = new QueryWrapper<>();
        coursesQueryWrapper.eq("cid",course.getCid());
        coursesMapper.update(course,coursesQueryWrapper);
    }
    @DeleteMapping("/deleteCourse")
    public void deleteCourse(@RequestBody Course course){
        QueryWrapper<Course> coursesQueryWrapper = new QueryWrapper<>();
        coursesQueryWrapper.eq("cid",course.getCid());
        coursesMapper.delete(coursesQueryWrapper);
    }
}
