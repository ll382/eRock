package com.ruoyi.views.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ruoyi.views.domain.StudentCourseGrades;

/**
 * 学生成绩视图Service接口
 * 
 * @author ljy
 * @date 2024-03-20
 */
public interface IStudentCourseGradesService 
{
    /**
     * 查询学生成绩视图
     * 
     * @param stuId 学生成绩视图主键
     * @return 学生成绩视图
     */
    public ArrayList<HashMap<String, List>> selectStudentCourseGradesByStuId(Long stuId);

    /**
     * 查询学生成绩视图列表
     * 
     * @param studentCourseGrades 学生成绩视图
     * @return 学生成绩视图集合
     */
    public List<StudentCourseGrades> selectStudentCourseGradesList(StudentCourseGrades studentCourseGrades);

    /**
     * 新增学生成绩视图
     * 
     * @param studentCourseGrades 学生成绩视图
     * @return 结果
     */
    public int insertStudentCourseGrades(StudentCourseGrades studentCourseGrades);

    /**
     * 修改学生成绩视图
     * 
     * @param studentCourseGrades 学生成绩视图
     * @return 结果
     */
    public int updateStudentCourseGrades(StudentCourseGrades studentCourseGrades);

    /**
     * 批量删除学生成绩视图
     * 
     * @param stuIds 需要删除的学生成绩视图主键集合
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuIds(Long[] stuIds);

    /**
     * 删除学生成绩视图信息
     * 
     * @param stuId 学生成绩视图主键
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuId(Long stuId);
}
