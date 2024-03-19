package com.ruoyi.views.mapper;


import com.ruoyi.views.domain.StudentCourseGrades;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 学生成绩查询Mapper接口
 * 
 * @author ljy
 * @date 2024-03-14
 */
@Mapper
public interface StudentCourseGradesMapper 
{
    /**
     * 查询学生成绩查询
     * 
     * @param stuId 学生成绩查询主键
     * @return 学生成绩查询
     */
    public List<StudentCourseGrades> selectStudentCourseGradesByStuId(Long stuId);

    /**
     * 查询学生成绩查询列表
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 学生成绩查询集合
     */
    public List<StudentCourseGrades> selectStudentCourseGradesList(StudentCourseGrades studentCourseGrades);


    /**
     * 新增学生成绩查询
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 结果
     */
    public int insertStudentCourseGrades(StudentCourseGrades studentCourseGrades);

    /**
     * 修改学生成绩查询
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 结果
     */
    public int updateStudentCourseGrades(StudentCourseGrades studentCourseGrades);

    /**
     * 删除学生成绩查询
     * 
     * @param stuId 学生成绩查询主键
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuId(Long stuId);

    /**
     * 批量删除学生成绩查询
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuIds(Long[] stuIds);
}
