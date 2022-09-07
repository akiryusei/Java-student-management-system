package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.ScoresMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.Scores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

@RestController
@CrossOrigin("*")
@SuppressWarnings("ALL")
public class ScoresController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ScoresMapper scoresMapper;
    private Gson gson = new Gson();


    //返回平均分
    @GetMapping("/getAverageScores")
    public String getAverageScores() {
        return gson.toJson(scoresMapper.getAverageScores());
    }

    @GetMapping("/getScores")
    public String getScores() {
        // 定义一个空列表 即成绩信息一行为一个 hashmap
        List<HashMap<String, Object>> scores = new ArrayList<HashMap<String, Object>>();
        // 获取成绩表的信息
        List<Scores> rows = scoresMapper.selectList(null);
        for (Scores row : rows) {
            HashMap<String, Object> score = new HashMap<>();
            score.put("java", row.getJava());
            score.put("english", row.getEnglish());
            score.put("mathematics", row.getMathematics());
            score.put("game", row.getGame());
            score.put("algebra", row.getAlgebra());
            score.put("military", row.getMilitary());
            score.put("modeling", row.getModeling());
            Iterator<Map.Entry<String, Object>> iterator = score.entrySet().iterator();
            float sum = 0.0f;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                String key = entry.getKey();
                int value = (int) (entry.getValue());
                float g = value >= 60 ? (value - 50) / 10 : 0;
                switch (key) {
                    case "java": {
                        g *= 4;
                        break;
                    }
                    case "english": {
                        g *= 2;
                        break;
                    }
                    case "mathematics": {
                        g *= 5;
                        break;
                    }
                    case "game": {
                        g *= 2;
                        break;
                    }
                    case "algebra": {
                        g *= 2;
                        break;
                    }
                    case "military": {
                        g *= 1;
                        break;
                    }
                    case "modeling": {
                        g *= 3;
                        break;
                    }
                }
                sum += g;
            }
            score.put("sid", row.getSid());
            score.put("name", row.getName());
            //保留两位小数
            DecimalFormat df = new DecimalFormat("#0.00");
            //将字符串转换成double
            score.put("grade", Double.parseDouble(df.format(sum / 19)));
            scores.add(score);
        }
        return gson.toJson(scores);
    }

    //可视化-----计算绩点分段人数
    @GetMapping("/getCountGradesNumbers")
    public String getCountGradesNumbers() {
        //获取的 json 数据 带有 grade 的成绩信息，并且转换为 list
        List scores = gson.fromJson(getScores(), List.class);

        int result01 = 0;
        int result12 = 0;
        int result23 = 0;
        int result34 = 0;
        int result45 = 0;
        //每一row迭代
        for (Object score : scores) {
            String s = gson.toJson(score);
            HashMap g = gson.fromJson(s, HashMap.class);
//          System.out.println();
            double value = (double)g.get("grade");
            System.out.println(value);
            if (0 <= value && value < 1) {
                result01++;
            } else if (1 <= value && value < 2) {
                result12++;
            } else if (2 <= value && value < 3) {
                result23++;
            } else if (3 <= value && value < 4) {
                result34++;
            } else if (4 <= value && value < 5) {
                result45++;
            }
        }
        //手写 json
        String res1 = "{\"name\":\"0-1\",\"value\":" + result01 + "}";
        String res2 = "{\"name\":\"1-2\",\"value\":" + result12 + "}";
        String res3 = "{\"name\":\"2-3\",\"value\":" + result23 + "}";
        String res4 = "{\"name\":\"3-4\",\"value\":" + result34 + "}";
        String res5 = "{\"name\":\"4-5\",\"value\":" + result45 + "}";
        return "[" + res1 + "," + res2 + "," + res3 + "," + res4 + "," + res5 + "]";

    }

    @PostMapping("/addScores")
    public void addScores(@RequestBody Scores scores) {
        scoresMapper.insert(scores);
    }

    @PutMapping("/updateScores")
    public void updateScore(@RequestBody Scores scores) {
        QueryWrapper<Scores> gradesQueryWrapper = new QueryWrapper<>();
        System.out.println("=============");
        System.out.println(scores);
        gradesQueryWrapper.eq("sid", scores.getSid());
        scoresMapper.update(scores, gradesQueryWrapper);
    }

    @DeleteMapping("/deleteScores")
    public void deleteScores(@RequestBody Scores scores) {
        QueryWrapper<Scores> gradesQueryWrapper = new QueryWrapper<>();
        //根据 id 进行删除
        gradesQueryWrapper.eq("sid", scores.getSid());
        scoresMapper.delete(gradesQueryWrapper);
    }

    @PostMapping("/searchGrades")
    public String getSearchGrades(@RequestBody HashMap data) {
        QueryWrapper<Scores> gradesQueryWrapper = new QueryWrapper<>();
        gradesQueryWrapper.like("name", data.get("name"));
        List<Scores> gradesList = scoresMapper.selectList(gradesQueryWrapper);
        List<HashMap<String, Object>> scores = new ArrayList<HashMap<String, Object>>();
        List<Scores> rows = scoresMapper.selectList(gradesQueryWrapper);
        for (Scores row : rows) {
            HashMap<String, Object> score = new HashMap<>();
            score.put("java", row.getJava());
            score.put("english", row.getEnglish());
            score.put("mathematics", row.getMathematics());
            score.put("game", row.getGame());
            score.put("algebra", row.getAlgebra());
            score.put("military", row.getMilitary());
            score.put("modeling", row.getModeling());
            Iterator<Map.Entry<String, Object>> iterator = score.entrySet().iterator();
            double sum = 0.0f;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                String key = entry.getKey();
                int value = (int) (entry.getValue());
                float g = value >= 60 ? (value - 50) / 10 : 0;
                switch (key) {
                    case "java": {
                        g *= 4;
                        break;
                    }
                    case "english": {
                        g *= 2;
                        break;
                    }
                    case "mathematics": {
                        g *= 5;
                        break;
                    }
                    case "game": {
                        g *= 2;
                        break;
                    }
                    case "algebra": {
                        g *= 2;
                        break;
                    }
                    case "military": {
                        g *= 1;
                        break;
                    }
                    case "modeling": {
                        g *= 3;
                        break;
                    }
                }
                sum += g;
            }
            score.put("sid", row.getSid());
            score.put("name", row.getName());
            //保留两位小数
            DecimalFormat df = new DecimalFormat("#0.00");
            score.put("grade", Double.parseDouble(df.format(sum / 19)));
            scores.add(score);

        }
        return gson.toJson(scores);
    }

}
