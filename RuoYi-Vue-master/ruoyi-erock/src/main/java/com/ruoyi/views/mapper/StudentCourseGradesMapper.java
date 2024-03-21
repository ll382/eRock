package com.ruoyi.views.mapper;

import java.util.List;
import com.ruoyi.views.domain.StudentCourseGrades;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生成绩视图Mapper接口
 * 
 * @author ljy
 * @date 2024-03-20
 */
@Mapper
public interface StudentCourseGradesMapper 
{
    /**
     * 查询学生成绩视图
     * 
     * @param stuId 学生成绩视图主键
     * @return 学生成绩视图
     */
    public StudentCourseGrades selectStudentCourseGradesByStuId(Long stuId);

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
     * 删除学生成绩视图
     * 
     * @param stuId 学生成绩视图主键
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuId(Long stuId);

    /**
     * 批量删除学生成绩视图
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentCourseGradesByStuIds(Long[] stuIds);
}
