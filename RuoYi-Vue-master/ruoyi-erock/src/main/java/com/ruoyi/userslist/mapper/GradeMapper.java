package com.ruoyi.userslist.mapper;

import java.util.List;
import com.ruoyi.userslist.domain.Grade;
import com.ruoyi.userslist.domain.Student;

/**
 * 班级Mapper接口
 * 
 * @author eRock
 * @date 2024-03-13
 */
public interface GradeMapper 
{
    /**
     * 查询班级
     * 
     * @param classId 班级主键
     * @return 班级
     */
    public Grade selectGradeByClassId(Long classId);

    /**
     * 查询班级列表
     * 
     * @param grade 班级
     * @return 班级集合
     */
    public List<Grade> selectGradeList(Grade grade);

    /**
     * 新增班级
     * 
     * @param grade 班级
     * @return 结果
     */
    public int insertGrade(Grade grade);

    /**
     * 修改班级
     * 
     * @param grade 班级
     * @return 结果
     */
    public int updateGrade(Grade grade);

    /**
     * 删除班级
     * 
     * @param classId 班级主键
     * @return 结果
     */
    public int deleteGradeByClassId(Long classId);

    /**
     * 批量删除班级
     * 
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGradeByClassIds(Long[] classIds);

    /**
     * 批量删除学生
     * 
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByClassIds(Long[] classIds);
    
    /**
     * 批量新增学生
     * 
     * @param studentList 学生列表
     * @return 结果
     */
    public int batchStudent(List<Student> studentList);
    

    /**
     * 通过班级主键删除学生信息
     * 
     * @param classId 班级ID
     * @return 结果
     */
    public int deleteStudentByClassId(Long classId);
}
