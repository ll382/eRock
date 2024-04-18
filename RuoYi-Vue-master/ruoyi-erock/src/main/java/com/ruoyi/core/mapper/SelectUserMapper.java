package com.ruoyi.core.mapper;

import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SelectUserVo;
import com.ruoyi.common.core.domain.entity.Group;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
public interface SelectUserMapper {

//    通过userId查询学生Id
    @Select("Select s.stu_id as id from student s where s.user_id = #{userId}")
    Long selectStudentStuId(Long userId);

//    通过userId查询老师Id
    @Select("Select s.tea_id as id from teacher s where s.user_id = #{userId}")
    Long selectTeacherTeaId(Long userId);

//    单查头像
    @Select("Select s.stu_id as id , e.avatar as img,s.stu_name as name from student s left JOIN sys_user e ON e.user_id = s.user_id where s.stu_id = #{stuId}")
    SelectUserVo selectStudentbyOne(Long stuId);
    @Select("Select t.tea_id as id , e.avatar as img,t.tea_name as name from teacher t left JOIN sys_user e ON e.user_id = t.user_id where t.tea_id = #{teaId}")
    SelectUserVo selectTeacherbyOne(Long teaId);

//    废弃
    List<BaseEntity> selectTeachers(List<Long> teaId);
//    查小组内所有学生
    List<SelectUserVo> selectInGroupStudent(Long ggId);
//    返回未在list中的学生
    List<SelectUserVo> selectStudents(List<Long> stuList);
    List<SelectUserVo> selectUndoneStudents(List<Long> stuList);
    List<SelectUserVo> selectUndoneTeacher(List<SelectUserVo> teaList);
//    小组查询
    @Select("select gg_id as ggId , gg_name as ggName, stu_group_leader as stuGroupLeader  from stu_group where gg_id = #{ggId}")
    Group selectGroupbyOne(Long ggId);

    List<SelectUserVo> selectUserFrequency(@Param("list") List<SelectUserVo> list);
    List<SelectUserVo> selectUserFrequencyByRtId(@Param("list") List<SelectUserVo> list ,@Param("phtrId") Long phtrId);

    @Select("select phtr_id as phtrId, tea_id as teaId, phtr_start_time as phtrStartTime, phtr_end_time as phtrEndTime , phtr_name as phtrName , phtr_describe as phtrDescribe from a3_physical_training where phtr_id = #{phtrId}")
    A3PhysicalTraining selectStudentFrequency(Long phtrId);

//    返回未在list中的所有小组
    List<Group> selectGroup(@Param("ggId") List<Long> ggId);
}
