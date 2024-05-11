package com.ruoyi.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.mapper.GradeMapper;
import com.ruoyi.core.domain.Grade;
import com.ruoyi.core.service.IGradeService;

/**
 * 班级Service业务层处理
 *
 * @author heye
 * @date 2024-03-15
 */
@Service
public class GradeServiceImpl implements IGradeService
{
    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 查询班级
     *
     * @param classId 班级主键
     * @return 班级
     */
    @Override
    public Grade selectGradeByClassId(Long classId)
    {
        return gradeMapper.selectGradeByClassId(classId);
    }

    /**
     * 查询班级列表
     *
     * @param grade 班级
     * @return 班级
     */
    @Override
    public List<Grade> selectGradeList(Grade grade)
    {
        return gradeMapper.selectGradeList(grade);
    }

    /**
     * 新增班级
     *
     * @param grade 班级
     * @return 结果
     */
    @Transactional
    @Override
    public int insertGrade(Grade grade)
    {
        int rows = gradeMapper.insertGrade(grade);
        insertStudent(grade);
        return rows;
    }

    /**
     * 修改班级
     *
     * @param grade 班级
     * @return 结果
     */
    @Transactional
    @Override
    public int updateGrade(Grade grade)
    {
        gradeMapper.deleteStudentByClassId(grade.getClassId());
        insertStudent(grade);
        return gradeMapper.updateGrade(grade);
    }

    /**
     * 批量删除班级
     *
     * @param classIds 需要删除的班级主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteGradeByClassIds(Long[] classIds)
    {
        gradeMapper.deleteStudentByClassIds(classIds);
        return gradeMapper.deleteGradeByClassIds(classIds);
    }

    /**
     * 删除班级信息
     *
     * @param classId 班级主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteGradeByClassId(Long classId)
    {
        gradeMapper.deleteStudentByClassId(classId);
        return gradeMapper.deleteGradeByClassId(classId);
    }

    /**
     * 新增学生信息
     *
     * @param grade 班级对象
     */
    public void insertStudent(Grade grade)
    {
        List<Student> studentList = grade.getStudentList();
        Long classId = grade.getClassId();
        if (StringUtils.isNotNull(studentList))
        {
            List<Student> list = new ArrayList<Student>();
            for (Student student : studentList)
            {
                student.setClassId(classId);
                list.add(student);
            }
            if (list.size() > 0)
            {
                gradeMapper.batchStudent(list);
            }
        }
    }
}
