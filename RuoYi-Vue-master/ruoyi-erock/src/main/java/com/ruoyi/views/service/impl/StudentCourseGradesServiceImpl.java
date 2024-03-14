package com.ruoyi.views.service.impl;

import java.util.List;

import com.ruoyi.views.domain.StudentCourseGrades;
import com.ruoyi.views.mapper.StudentCourseGradesMapper;
import com.ruoyi.views.service.IStudentCourseGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生成绩查询Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-14
 */
@Service
public class StudentCourseGradesServiceImpl implements IStudentCourseGradesService
{
    @Autowired
    private StudentCourseGradesMapper studentCourseGradesMapper;

    /**
     * 查询学生成绩查询
     * 
     * @param stuId 学生成绩查询主键
     * @return 学生成绩查询
     */
    @Override
    public StudentCourseGrades selectStudentCourseGradesByStuId(Long stuId)
    {
        return studentCourseGradesMapper.selectStudentCourseGradesByStuId(stuId);
    }

    /**
     * 查询学生成绩查询列表
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 学生成绩查询
     */
    @Override
    public List<StudentCourseGrades> selectStudentCourseGradesList(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.selectStudentCourseGradesList(studentCourseGrades);
    }

    /**
     * 新增学生成绩查询
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 结果
     */
    @Override
    public int insertStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.insertStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 修改学生成绩查询
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 结果
     */
    @Override
    public int updateStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.updateStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 批量删除学生成绩查询
     * 
     * @param stuIds 需要删除的学生成绩查询主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuIds(Long[] stuIds)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuIds(stuIds);
    }

    /**
     * 删除学生成绩查询信息
     * 
     * @param stuId 学生成绩查询主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuId(Long stuId)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuId(stuId);
    }
}
