package com.ruoyi.visualization.mapper;

import java.util.List;
import com.ruoyi.visualization.domain.StudentCourseGrades;
import org.apache.ibatis.annotations.Mapper;

/**
 * PC段首页Mapper接口
 * 
 * @author ljy
 * @date 2024-03-22
 */
@Mapper
public interface StudentCourseGradesMapper 
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
    public List<StudentCourseGrades> selectStudentCourseGradesList(StudentCourseGrades studentCourseGrades);

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
     * 删除PC段首页
     * 
     * @param stuName PC段首页主键
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuName(String stuName);

    /**
     * 批量删除PC段首页
     * 
     * @param stuNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuNames(String[] stuNames);
}
