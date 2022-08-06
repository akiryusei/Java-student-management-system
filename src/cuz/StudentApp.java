package cuz;

import cuz.model.Course;
import cuz.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StudentApp {
    private static ArrayList<Student> studentList = new ArrayList();
    private static ArrayList<Course> courseList = new ArrayList();

    private static HashMap<Long, Student> studentMap = new HashMap();
    //取代上面的 studentlist，通过学号可以找到学生，有点像数据库了
    private static HashMap<String, Course> courseMap = new HashMap();
    //课程id， course类型

    public static Course getCourse(String courseId){
        return courseMap.get(courseId);
    }
    public static void readCourses(String filename) {
        try (Scanner scan = new Scanner(new File(filename))) {
            int lineno = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                lineno++;
                if (lineno == 1)
                    continue;
                String[] fileds = line.split(",");
                //csv默认以,分隔   学分可以是double
                Course c = new Course(fileds[0], fileds[1], Double.parseDouble(fileds[2]));
                courseList.add(c);
                //studentMap.put(Long.parseLong(fileds[0]), s);
                courseMap.put(fileds[0],c);//课程id作为主键

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readStudents(String filename) {
        try(Scanner scan = new Scanner(new File(filename))){
            int lineno = 0;
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                lineno++;
                if (lineno == 1)
                    continue;
                String[] fileds = line.split(",");
                Student s = new Student(Long.parseLong(fileds[0]), fileds[1]);
                studentList.add(s);

                studentMap.put(Long.parseLong(fileds[0]), s);
                //存入学号、学生类
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void readScore(String filename) {
        try(Scanner scan = new Scanner(new File(filename))){
            int lineno = 0;
            //第一行
            String[] header  = null;
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                lineno++;
                if (lineno == 1) {
                    header = line.split(",");
                    //System.out.println(header[0]);学号
                    continue;
                }
                String[] fileds = line.split(",");
                for(int i=3 ; i<fileds.length;i++) // 3开始是课程号
                {
                    Student s =studentMap.get(Long.parseLong(fileds[0]));//根据学号查找映射值
                    s.addCourseScore(header[i],Double.parseDouble(fileds[i]));//为学生添加课程成绩
                    //每个学生有一个成绩map
                }

                //studentMap.put(Long.parseLong(fileds[0]), s);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        //readStudents("E:/java_data/student.csv");

        System.out.println("````````");
        readCourses("data/courses.csv");
        courseList.forEach(System.out::println);//使用了course类中的tostring函数

        readStudents("data/student.csv");
        studentList.forEach(System.out::println);

        readScore("data/score.csv");
        for(int i =0;i<studentList.size();i++)//学生个数
        {
            Student s = studentList.get(i);
            //s.printScore();
            System.out.println(s+ " gpa： "+String.format("%.2f",s.getGPA()));
            //直接打印学生对象，返回tostring
            System.out.println("-----------");
        }

        /*
        //比较器 匿名类写法
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                double gpa1 = o1.getGPA();
                double gpa2 = o2.getGPA();
                return Double.compare(gpa1,gpa2);
                //将 o1，o2转换成了gpa比较
            }
        });
        */

        //方法引用
        studentList.sort(Comparator.comparing(Student::getGPA,Comparator.reverseOrder()));
        //工具类
        //Collections.sort(studentList,Comparator.comparing(Student::getGPA));

        //函数式写法
        studentList.sort((s1,s2)-> Double.compare(s1.getGPA(), s2.getGPA()));

        studentList.forEach(System.out::println);//打印tostring

        //lambda表达式进行复杂的打印输出
        studentList.forEach(s -> {System.out.println("Student"+s);
                                    System.out.println("hello");});

    }
}
