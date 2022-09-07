package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Scores;

import java.util.HashMap;

public interface ScoresMapper extends BaseMapper<Scores> {

    //查询课程平均分
    @Select("select avg(java) java, avg(english) english,avg(mathematics) math ," +
            "avg(game) game,avg(algebra) algebra,avg( military)  military," +
            "avg(modeling) modeling from scores;")
    HashMap<String, Float> getAverageScores();
}
