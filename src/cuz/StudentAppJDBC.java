package cuz;

import cuz.model.Course;
import cuz.model.Student;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class StudentAppJDBC {
    private static ArrayList<Student> studentList = new ArrayList();
    private static ArrayList<Course> courseList = new ArrayList();

    private static HashMap<Long, Student> studentMap = new HashMap();
    //取代上面的 studentlist，通过学号可以找到学生，有点像数据库了
    private static HashMap<String, Course> courseMap = new HashMap();
    //课程id， course类型

    public static Course getCourse(String courseId){
        return courseMap.get(courseId);
    }


    public static void loadStudentFromDatabase() {
        System.out.println("hellp");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java", "root", "yuri2233");
             Statement st = conn.createStatement();
             //com.mysql.cj.jdbc.ConnectionImpl@2de23121 连接成功
        ) {
            String sql = "select * from student";
            ResultSet rs = st.executeQuery(sql);//执行查询
            //生成语句对象
            int rowCount = 0;
            while (rs.next())
            {
                Long sid = rs.getLong("sid");
                String name = rs.getString("name");

                //System.out.println(rowCount+":"+sid+":"+name);
                rowCount++;

                Student s = new Student(sid,name);
                studentList.add(s);
                studentMap.put(sid,s);
            }

            System.out.println(conn);//

        } catch (SQLException e) {

            //查询结果
            e.printStackTrace();
        }
    }

    public static void loadCoursesFromDatabase() {
        System.out.println("hellp");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java", "root", "yuri2233");
             Statement st = conn.createStatement();
             //com.mysql.cj.jdbc.ConnectionImpl@2de23121 连接成功
        ) {
            String sql = "select * from courses";
            ResultSet rs = st.executeQuery(sql);//执行查询
            //生成语句对象
            int rowCount = 0;
            while (rs.next())
            {
                String cid = rs.getString("cid");
                String cname = rs.getString("cname");
                Double credit = rs.getDouble("credit");

                //System.out.println(rowCount+":"+cid+":"+cname+":"+credit);
                rowCount++;
                Course c = new Course(cid,cname,credit);
                courseList.add(c);
                courseMap.put(cid,c);
            }

            System.out.println(conn);//

        } catch (SQLException e) {

            //查询结果
            e.printStackTrace();
        }
    }

    //try catch用完会自动关闭数据库连接
    public static void loadScoreFromDatabase() {
        System.out.println("score");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java", "root", "yuri2233");
             Statement st = conn.createStatement();
             //com.mysql.cj.jdbc.ConnectionImpl@2de23121 连接成功
        ) {
            String sql = "select * from score";
            ResultSet rs = st.executeQuery(sql);//执行查询
            //生成语句对象
            int rowCount = 0;
            while (rs.next())
            {
                Long sid = rs.getLong("sid");
                String cid = rs.getString("cid");
                Double score = rs.getDouble("score");

                //调试
                //System.out.println(rowCount+":"+sid+":"+cid+":"+score);
                rowCount++;

                //找到选课学生，将成绩加入该学生的scoremap
                Student  s =studentMap.get(sid);
                s.addCourseScore(cid,score);
            }

            //System.out.println(conn);//

        } catch (SQLException e) {

            //查询结果
            e.printStackTrace();
        }
    }

    public static void outputToGpaTable() {

        try (
                // Step 1: Construct a database 'Connection' object called 'conn'
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/java", "root", "yuri2233");   // For MySQL only
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
                Statement stmt = conn.createStatement();
        )
        {

            DatabaseMetaData meta = conn.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "gpa", new String[] {"TABLE"});

            System.out.println(resultSet);
            if ( !resultSet.next()) {

                // Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
                //  The query result is returned in a 'ResultSet' object called 'rset'.
                String sql = "CREATE TABLE gpa"  +
                        "(id BIGINT not NULL, " +
                        " gpa REAL, " +
                        " PRIMARY KEY ( id ))";
                System.out.println("The SQL statement is: " + sql + "\n"); // Echo For debugging

                int ret = stmt.executeUpdate(sql);//增删改都是这个 insert、update、delete
                //返回操作行数 个数

                System.out.println(ret);

            }

            // 清空表的数据
            // 删除

            String sqlDelete = "delete from gpa";
            int countDeleted = stmt.executeUpdate(sqlDelete);
            System.out.println(countDeleted + " records deleted.\n");


            // 在表中增加学生数据
            for (Student s : studentList) {

                Long id = s.getId();
                String name = s.getName();
                double gpa = s.getGPA();

                // 查
                String sql_res= "select * from gpa where id = " + id;
                ResultSet rs = stmt.executeQuery(sql_res);

                if (! rs.next()) {
                    // 增
                    System.out.println("Inserting " + id + ": " + name);
                    String sqlInsert = "insert into gpa values (" + id + ","
                            + String.format("%.2f", gpa) + ")";
                    System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
                    int countInserted = stmt.executeUpdate(sqlInsert);
                    System.out.println(countInserted + " records inserted.\n");
                }
                else {
                    // 改
                    System.out.println("Updating " + id + ": " + name);
                    String strUpdate = "update gpa set gpa = " + String.format("%.3f", gpa) +  "where id = " + id;
                    int countUpdated = stmt.executeUpdate(strUpdate);
                    //System.out.println(countUpdated + " records affected.\n");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//从csv文件读取数据
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


        System.out.println("````````");
        //readStudents("data/student.csv");
        loadStudentFromDatabase();
        //studentList.forEach(System.out::println);

        //readCourses("data/courses.csv");
        //从数据库读取数据
        loadCoursesFromDatabase();
        //courseList.forEach(System.out::println);//使用了course类中的tostring函数

        //readScore("data/score.csv");
        loadScoreFromDatabase();
        //studentList.forEach(s -> s.printScore()); //打印每个学生的各科成绩


        for(int i =0;i<studentList.size();i++)//学生个数
        {
            Student s = studentList.get(i);
            //s.printScore();
            System.out.println( s + " gpa： "+String.format("%.2f",s.getGPA()));
            //直接打印学生对象，返回tostring
            System.out.println("-----------");
        }

        outputToGpaTable();
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
        //studentList.sort((s1,s2)-> Double.compare(s1.getGPA(), s2.getGPA()));

        //studentList.forEach(System.out::println);//打印tostring

        //lambda表达式进行复杂的打印输出
       // studentList.forEach(s -> {System.out.println("Student"+s);});



    }
}
