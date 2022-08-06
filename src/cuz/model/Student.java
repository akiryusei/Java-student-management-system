package cuz.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import cuz.StudentApp;
import cuz.StudentAppJDBC;

public class Student {
    private long id;
    private String name;
    private HashMap<String, Double> scoreMap;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
        this.scoreMap = new HashMap();
    }

    public void addCourseScore(String courseId, double score) {
        this.scoreMap.put(courseId, score);
        //增加映射
    }

    public double getCourseScore(String courseId) {
        if(scoreMap.containsKey(courseId))//存在这个key值
        {
            return scoreMap.get(courseId);
        }else
            return  -1;
    }
    public void printScore() {
        //打印学号姓名
        System.out.println(toString());


        Set<String> courseIdList = this.scoreMap.keySet();//返回key的set 课程id
        Iterator<String> it = courseIdList.iterator();

        while (it.hasNext()) {
            String courseId = (String) it.next();
            double score = (Double) this.scoreMap.get(courseId);
            Course c = StudentAppJDBC.getCourse(courseId);
            System.out.println(courseId + ": "+c.getName()+": " + score);
        }
        //超简单的一行函数式打印方法
        //scoreMap.forEach( (k, v)-> System.out.println(k+": "+v));
    }

    public double getGPA() {
        //绩点=分数/10 -5
        //平均绩点：(课程学分1*绩点+课程学分2*绩点)/总学分
        //找到学生修的所有课程号
        Set<String> courseIdList = this.scoreMap.keySet();
        Iterator<String> it = courseIdList.iterator();

        double sumPoint = 0.0; //分子：
        double sumCredit = 0.0;//分母 学分的和

        //迭代器迭代课程
        while(it.hasNext()) {
            String courseId = (String)it.next();
            Course c = StudentAppJDBC.getCourse(courseId);
            //简单的防止错误处理
            if (c != null) {
                double credit = c.getCredit();
                sumCredit += credit;
                double score = (Double)this.scoreMap.get(courseId);//获得每门课成绩
                double point = score / 10.0 - 5.0;//计算绩点
                //绩点为0
                if(score<60.0)
                    point = 0;
                sumPoint += point * credit;
            }
        }

        double gpa = sumPoint / sumCredit;
        //System.out.println("GPA : " + gpa);
        return gpa;
    }
/*
    public void addCourseScore(String courseId, Double score) {
        this.scoreMap.put(courseId, score);
    }

    public void printScore() {
        System.out.println(this.toString());
        Set<String> courseIdList = this.scoreMap.keySet();
        Iterator<String> it = courseIdList.iterator();

        while(it.hasNext()) {
            String courseId = (String)it.next();
            double score = (Double)this.scoreMap.get(courseId);
            //Course c = StudentApp.getCourse(courseId);
            System.out.println(courseId + ": " + c.getName() + ": " + score);
        }

    }

*/
    @Override
    public String toString() {

        return this.id + " : " + this.name +": "+String.format("%.2f",getGPA());
    }

    public Long getId()
    {
        return id;
    }
    public String getName()
    {return  name;}
}
