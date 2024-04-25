package com.ruoyi;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.core.domain.Grade;
import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.util.DateUtil;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.practice.domain.ABallExam;
import com.ruoyi.practice.mapper.ABallExamMapper;
import com.ruoyi.practice.mapper.AMarkSheetMapper;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.service.ITotalScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@SpringBootTest
class RuoYiApplicationTests {

    @Autowired
    private StudentMapper studentMapper;


    @Test
    public void s1() {


    }

    @Test
    public void test(){
//        Function<String,Date> supplier = DateUtil::StringConvertDate;
//        ArrayList<LocalDateTime> list = new ArrayList<>();
//        List<Map<String, Object>> students = studentMapper.selectStudentAchievementList(16l,supplier.apply("2024-03-15 08:00:00") ,"ms_shooting");
//        for (Map<String, Object> student : students){
//            list.add((LocalDateTime) student.get("cr_date"));
//        }
//        list.stream().distinct().forEach(System.out::println);

    }
}
