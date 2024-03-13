package com.ruoyi.userslist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.userslist.mapper.StudentMapper;
import com.ruoyi.userslist.domain.Student;
import com.ruoyi.userslist.service.IStudentService;

/**
 * 学生Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-13
 */
@Service
public class StudentServiceImpl implements IStudentService
{
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生
     *
     * @param stuId 学生主键
     * @return 学生
     */
    @Override
    public Student selectStudentByStuId(String stuId)
    {
        return studentMapper.selectStudentByStuId(stuId);
    }

    /**
     * 查询学生列表
     *
     * @param student 学生
     * @return 学生
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学生
     *
     * @param student 学生
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生
     *
     * @param student 学生
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生
     *
     * @param stuIds 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStuIds(String[] stuIds)
    {
        return studentMapper.deleteStudentByStuIds(stuIds);
    }

    /**
     * 删除学生信息
     *
     * @param stuId 学生主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStuId(String stuId)
    {
        return studentMapper.deleteStudentByStuId(stuId);
    }
}
