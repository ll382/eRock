package com.ruoyi.userslist.mapper;

import java.util.List;
import com.ruoyi.userslist.domain.Student;

/**
 * 学生Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-13
 */
public interface StudentMapper
{
    /**
     * 查询学生
     *
     * @param stuId 学生主键
     * @return 学生
     */
    public Student selectStudentByStuId(String stuId);

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
     * 删除学生
     *
     * @param stuId 学生主键
     * @return 结果
     */
    public int deleteStudentByStuId(String stuId);

    /**
     * 批量删除学生
     *
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByStuIds(String[] stuIds);
}