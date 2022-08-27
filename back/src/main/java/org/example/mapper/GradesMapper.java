package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Grades;
import org.example.pojo.Score;

import java.util.HashMap;
import java.util.List;

public interface GradesMapper extends BaseMapper<Grades> {
//    @Select("select cid,avg(score) from score group by cid")
//    public List<HashMap<String,Float>> getScores();

    @Select("select c.cname cname,avg(score) average_score from score s left join courses c on s.cid=c.cid group by c.cid;")
    public List<HashMap<String,Float>> getAverageScores();

    @Select("SELECT stu.sid sid,stu.name name,\n" +
            "        sum(case cid when 'A0001' then score else 0 end) as java,\n" +
            "        sum(case cid when 'B0001' then score else 0 end) as English,\n" +
            "        sum(case cid when 'C0001' then score else 0 end) as mathematics,\n" +
            "        sum(case cid when 'A0002' then score else 0 end) as game,\n" +
            "        sum(case cid when 'C0002' then score else 0 end) as algebra,\n" +
            "        sum(case cid when 'D0001' then score else 0 end) as military,\n" +
            "        sum(case cid when 'A0003' then score else 0 end) as modeling\n" +
            "        FROM score sco JOIN student stu on stu.sid=sco.sid\n" +
            "        GROUP BY sco.sid;\n")
    List<HashMap<String,Object>> getScores();


}
