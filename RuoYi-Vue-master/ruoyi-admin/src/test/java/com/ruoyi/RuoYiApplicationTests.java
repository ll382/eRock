package com.ruoyi;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.core.domain.Grade;
import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;


@SpringBootTest
class RuoYiApplicationTests {

    @Autowired
    private StudentMapper studentMapper;


    @Test
    public void s1() {
        List<Map<String, Object>> maps = studentMapper.selectDeduplicationCrDateByStuIdAndEnumIdList(16l, 1);
        for (Map<String, Object> map : maps){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String format = dateTimeFormatter.format((LocalDateTime) map.get("cr_date"));
            System.out.println(format);
            return;
        }
    }

    @Test
    public void test(){
        ArrayList<HashMap<String, List>> list = new ArrayList<>();
        HashMap<String, List> HashMap = new HashMap<>();
//        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<BigDecimal> msShootingList = new ArrayList<>();
//        ArrayList<JSONObject> jsonObjectList = new ArrayList<>();
        List<Map<String, Object>> maps = studentMapper.selectStudentAchievementByStuId(16l);
        System.out.println(maps);
        return;
//        for (Map<String, Object> map : maps){
////            dateList.add(DateUtil.LocalDateTimeConvertString((LocalDateTime) map.get("cr_date")));
//            msShootingList.add((BigDecimal) map.get("ms_shooting"));
////            JSONObject dataJsonObject = new JSONObject();
////            dataJsonObject.put("value",map.get("ms_shooting"));
////            dataJsonObject.put("xAxis",DateUtil.LocalDateTimeConvertString((LocalDateTime) map.get("cr_date")));
////            dataJsonObject.put("yAxis",map.get("ms_shooting"));
////            jsonObjectList.add(dataJsonObject);
//        }
////        HashMap.put("date",dateList);
//        HashMap.put("ms_shooting",msShootingList);
////        HashMap.put("data",jsonObjectList);
//        list.add(HashMap);

    }
}
