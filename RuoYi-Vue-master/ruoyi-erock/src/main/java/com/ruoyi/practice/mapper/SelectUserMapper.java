package com.ruoyi.practice.mapper;

import com.ruoyi.core.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
public interface SelectUserMapper {

    @Select("Select s.stu_id as stuId, e.avatar as stuImg,s.stu_name as stuName from student s left JOIN sys_user e ON e.user_id = s.user_id where s.stu_id = #{stuId}")
    Student selectUser(Long stuId);
}
