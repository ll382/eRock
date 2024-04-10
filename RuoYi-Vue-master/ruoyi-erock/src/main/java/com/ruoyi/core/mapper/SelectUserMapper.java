package com.ruoyi.core.mapper;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.domain.vo.SelectUserVo;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
public interface SelectUserMapper {

    List<AMarkSheet> selectStudent(List<AMarkSheet> stuList);
    @Select("Select e.avatar as img,s.stu_name as name from student s left JOIN sys_user e ON e.user_id = s.user_id where s.stu_id = #{stuId}")
    SelectUserVo selectStudentbyOne(Long stuId);
    @Select("Select e.avatar as img,t.tea_name as name from teacher t left JOIN sys_user e ON e.user_id = t.user_id where t.tea_id = #{teaId}")
    SelectUserVo selectTeacherbyOne(Long teaId);
    List<AExerciseTask> selectTeacher(List<AExerciseTask> stuId);
}
