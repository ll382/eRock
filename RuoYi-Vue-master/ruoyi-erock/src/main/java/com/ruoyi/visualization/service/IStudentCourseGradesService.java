package com.ruoyi.visualization.service;

import java.util.List;
import com.ruoyi.visualization.domain.StudentCourseGrades;

/**
 * PC段首页Service接口
 * 
 * @author ljy
 * @date 2024-03-22
 */
public interface IStudentCourseGradesService 
{
    /**
     * 查询PC段首页
     * 
     * @param stuName PC段首页主键
     * @return PC段首页
     */
    public StudentCourseGrades selectStudentCourseGradesByStuName(String stuName);

    /**
     * 查询PC段首页列表
     * 
     * @param studentCourseGrades PC段首页
     * @return PC段首页集合
     */
    public List<StudentCourseGrades> selectStudentCourseGradesList();

    /**
     * 新增PC段首页
     * 
     * @param studentCourseGrades PC段首页
     * @return 结果
     */
    public int insertStudentCourseGrades(StudentCourseGrades studentCourseGrades);

    /**
     * 修改PC段首页
     * 
     * @param studentCourseGrades PC段首页
     * @return 结果
     */
    public int updateStudentCourseGrades(StudentCourseGrades studentCourseGrades);

    /**
     * 批量删除PC段首页
     * 
     * @param stuNames 需要删除的PC段首页主键集合
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuNames(String[] stuNames);

    /**
     * 删除PC段首页信息
     * 
     * @param stuName PC段首页主键
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuName(String stuName);
}
