package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.GradesMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.Grades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@SuppressWarnings("ALL")
public class GradesController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GradesMapper gradesMapper;
    private Gson gson = new Gson();

    // 获取各个学生各科分数
//    @GetMapping("/getGrades")
//    public String getGrades() {
//
//        List<HashMap<String, Object>> scores = gradesMapper.getScores();
//        for (HashMap<String, Object> score : scores) {
//            double sum = 0.0;
//            Iterator<Map.Entry<String, Object>> iterator = score.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, Object> entry = iterator.next();
//                if (
//                        entry.getKey().equals("name") || entry.getKey().equals("sid")
//                ) {
//                    continue;
//                }
//                System.out.println(entry.getKey() + ":" + entry.getValue());
//                double s = (double) (entry.getValue());
//                double grade = s >= 60 ? (s - 50) / 10 : 0;
//                sum += grade;
//            }
//            String format = String.format("%.2f", sum);
//            float grades = Float.parseFloat(format);
//            score.put("grades", grades);
//            System.out.println("======" + grades + "========");
////            float java_grade=(float)score.get("java")>=60?()
////            case score.get("")
////            score.put("grades",(float)score.get("English")>=60?)
////            System.out.println(score);
//        }
//        return gson.toJson(scores);
//    }

    @GetMapping("/getAverageScores")
    public String getAverageScores() {
        return gson.toJson(gradesMapper.getAverageScores());
    }

    @GetMapping("/getCountGradesNumbers")
    public String getCountGradesNumbers() {
        List<HashMap<String, Object>> scores = gradesMapper.getScores();
        HashMap<String, Integer> level_conut = new HashMap<>();
        int between0and5 = 0;
        int between5and10 = 0;
        int between10and15 = 0;
        int between15and20 = 0;
        int between20and25 = 0;
        int between25and30 = 0;
        int between30and35 = 0;
        for (HashMap<String, Object> score : scores) {
            double grades = 0.0;
            Iterator<Map.Entry<String, Object>> iterator = score.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (
                        entry.getKey().equals("name") || entry.getKey().equals("sid")
                ) {
                    continue;
                }
                System.out.println(entry.getKey() + ":" + entry.getValue());
                double s = (double) (entry.getValue());
                double grade = s >= 60 ? (s - 50) / 10 : 0;
                grades += grade;
            }
            if (grades >= 0 && grades <= 5) {
                between0and5++;
            } else if (grades > 5 && grades <= 10) {
                between5and10++;
            } else if (grades > 10 && grades <= 15) {
                between10and15++;
            } else if (grades > 15 && grades <= 20) {
                between15and20++;
            } else if (grades > 20 && grades <= 25) {
                between20and25++;
            }else if (grades > 25 && grades <=30){
                between25and30++;
            }else if (grades > 30 && grades <=35){
                between30and35++;
            }
        }
        level_conut.put("between0and5",between0and5);
        level_conut.put("between5and10",between5and10);
        level_conut.put("between10and15",between10and15);
        level_conut.put("between15and20",between15and20);
        level_conut.put("between20and25",between20and25);
        level_conut.put("between25and30",between25and30);
        level_conut.put("between30and35",between30and35);
        return gson.toJson(level_conut);
//            String format = String.format("%.2f", sum);
//            float grades = Float.parseFloat(format);
//            score.put("grades",grades);
//            System.out.println("======"+grades+"========");
    }

    @GetMapping("/getGrades")
    public String getGrades(){
        // 定义一个空列表
        List<HashMap<String,Object>> grades = new ArrayList<HashMap<String,Object>>();
        // 获取成绩表的信息
        List<Grades> rows = gradesMapper.selectList(null);
        for(Grades row : rows){
            HashMap<String, Object> grade = new HashMap<>();
            grade.put("java",row.getJava());
            grade.put("english",row.getEnglish());
            grade.put("mathematics",row.getMathematics());
            grade.put("game",row.getGame());
            grade.put("algebra",row.getAlgebra());
            grade.put("military",row.getMilitary());
            grade.put("modeling",row.getModeling());
            Iterator<Map.Entry<String, Object>>iterator=grade.entrySet().iterator();
            float sum=0.0f;
            while(iterator.hasNext()){
                Map.Entry<String, Object> entry = iterator.next();
                String key = entry.getKey();
                int value = (int) (entry.getValue());
                float g=value>=60?(value-50)/10:0;
                switch(key){
                    case "java"->g*=4;
                    case "English"->g*=2;
                    case "mathematics"->g*=5;
                    case "game"->g*=2;
                    case "algebra"->g*=2;
                    case "military"->g*=1;
                    case "modeling"->g*=3;
                }
                sum+=g;
            }
            grade.put("sid",row.getSid());
            grade.put("name",row.getName());
            grade.put("gender",row.getGender());
            grade.put("grade",sum/19);
            grades.add(grade);

        }
        return gson.toJson(grades);
    }
    @PostMapping("/addGrades")
    public void addGrades(@RequestBody Grades grades){
        gradesMapper.insert(grades);
    }
    @PutMapping("/updateGrades")
    public void updateGrade(@RequestBody Grades grades){
        QueryWrapper<Grades> gradesQueryWrapper = new QueryWrapper<>();
        System.out.println("=============");
        System.out.println(grades);
        gradesQueryWrapper.eq("sid",grades.getSid());
        gradesMapper.update(grades,gradesQueryWrapper);
    }
    @DeleteMapping("/deleteGrades")
    public void deleteGrades(@RequestBody Grades grades){
        QueryWrapper<Grades> gradesQueryWrapper = new QueryWrapper<>();
        gradesQueryWrapper.eq("sid",grades.getSid());
        gradesMapper.delete(gradesQueryWrapper);
    }


}
