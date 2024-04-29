package com.ruoyi;


import com.ruoyi.core.mapper.StudentMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
class RuoYiApplicationTests {

    @Autowired
    private StudentMapper studentMapper;


    @Test
    public void s1() {
        System.out.println(123);
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
