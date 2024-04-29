package com.ruoyi.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.core.domain.vo.StudentModuleScore;


/**
 * 学生Service接口
 * 
 * @author heye
 * @date 2024-03-17
 */
public interface IStudentService 
{
    /**
     * 查询学生
     * 
     * @param stuId 学生主键
     * @return 学生
     */
    public Student selectStudentByStuId(Long stuId);

    /**
     * 根据班级和日期查询所有学生成绩信息等
     * @return 所有学生成绩信息等
     */
    public List<StudentCourseGrades> selectStudentCourseGradesList(String score);

    /**
     *  根据学生ID查询当天的所有成绩次数
     * @param stuId 学生ID
     * @param enumId 枚举ID
     * @return
     */
    public List<Map<String,List>> selectDeduplicationCrDateByStuNameAndEnumIdList(Long stuId, Integer enumId, String hour);

    /**
     * 根据学生ID查询成绩
     * @param stuId 学生ID
     * @return 学生的所有成绩
     */
    public List<List> selectStudentAchievementByStuId(Long stuId, String crDate,String score);

    /**
     * 查询classId班所有学生进步分
     * @param classId 班级ID
     * @return
     */
    public List<StudentModuleScore> selectStudentProgressScoreList(Integer classId);

    /**
     * 查询学生列表
     * 
     * @param student 学生
     * @return 学生集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生
     * 
     * @param student 学生
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生
     * 
     * @param student 学生
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学生
     * 
     * @param stuIds 需要删除的学生主键集合
     * @return 结果
     */
    public int deleteStudentByStuIds(Long[] stuIds);

    /**
     * 删除学生信息
     * 
     * @param stuId 学生主键
     * @return 结果
     */
    public int deleteStudentByStuId(Long stuId);
}
