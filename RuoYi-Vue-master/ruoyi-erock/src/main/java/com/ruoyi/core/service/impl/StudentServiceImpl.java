package com.ruoyi.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.core.domain.StuToTea;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author heye
 * @date 2024-03-17
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
    public Student selectStudentByStuId(Long stuId)
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
    @Transactional
    @Override
    public int insertStudent(Student student)
    {
        int rows = studentMapper.insertStudent(student);
        insertStuToTea(student);
        return rows;
    }

    /**
     * 修改学生
     * 
     * @param student 学生
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStudent(Student student)
    {
        studentMapper.deleteStuToTeaByStuId(student.getStuId());
        insertStuToTea(student);
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生
     * 
     * @param stuIds 需要删除的学生主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStudentByStuIds(Long[] stuIds)
    {
        studentMapper.deleteStuToTeaByStuIds(stuIds);
        return studentMapper.deleteStudentByStuIds(stuIds);
    }

    /**
     * 删除学生信息
     * 
     * @param stuId 学生主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStudentByStuId(Long stuId)
    {
        studentMapper.deleteStuToTeaByStuId(stuId);
        return studentMapper.deleteStudentByStuId(stuId);
    }

    /**
     * 新增多个学生对多个老师信息
     * 
     * @param student 学生对象
     */
    public void insertStuToTea(Student student)
    {
        List<StuToTea> stuToTeaList = student.getStuToTeaList();
        Long stuId = student.getStuId();
        if (StringUtils.isNotNull(stuToTeaList))
        {
            List<StuToTea> list = new ArrayList<StuToTea>();
            for (StuToTea stuToTea : stuToTeaList)
            {
                stuToTea.setStuId(stuId);
                list.add(stuToTea);
            }
            if (list.size() > 0)
            {
                studentMapper.batchStuToTea(list);
            }
        }
    }
}
