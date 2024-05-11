package com.ruoyi.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.core.domain.StuToTea;
import com.ruoyi.core.mapper.TeacherMapper;
import com.ruoyi.core.domain.Teacher;
import com.ruoyi.core.service.ITeacherService;

/**
 * 老师Service业务层处理
 * 
 * @author heye
 * @date 2024-03-17
 */
@Service
public class TeacherServiceImpl implements ITeacherService 
{
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询老师
     * 
     * @param teaId 老师主键
     * @return 老师
     */
    @Override
    public Teacher selectTeacherByTeaId(Long teaId)
    {
        return teacherMapper.selectTeacherByTeaId(teaId);
    }

    /**
     * 查询老师列表
     * 
     * @param teacher 老师
     * @return 老师
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        return teacherMapper.selectTeacherList(teacher);
    }

    /**
     * 新增老师
     * 
     * @param teacher 老师
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTeacher(Teacher teacher)
    {
        int rows = teacherMapper.insertTeacher(teacher);
        insertStuToTea(teacher);
        return rows;
    }

    /**
     * 修改老师
     * 
     * @param teacher 老师
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTeacher(Teacher teacher)
    {
        teacherMapper.deleteStuToTeaByTeaId(teacher.getTeaId());
        insertStuToTea(teacher);
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除老师
     * 
     * @param teaIds 需要删除的老师主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeacherByTeaIds(Long[] teaIds)
    {
        teacherMapper.deleteStuToTeaByTeaIds(teaIds);
        return teacherMapper.deleteTeacherByTeaIds(teaIds);
    }

    /**
     * 删除老师信息
     * 
     * @param teaId 老师主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeacherByTeaId(Long teaId)
    {
        teacherMapper.deleteStuToTeaByTeaId(teaId);
        return teacherMapper.deleteTeacherByTeaId(teaId);
    }

    /**
     * 新增多个学生对多个老师信息
     * 
     * @param teacher 老师对象
     */
    public void insertStuToTea(Teacher teacher)
    {
        List<StuToTea> stuToTeaList = teacher.getStuToTeaList();
        Long teaId = teacher.getTeaId();
        if (StringUtils.isNotNull(stuToTeaList))
        {
            List<StuToTea> list = new ArrayList<StuToTea>();
            for (StuToTea stuToTea : stuToTeaList)
            {
                stuToTea.setTeaId(teaId);
                list.add(stuToTea);
            }
            if (list.size() > 0)
            {
                teacherMapper.batchStuToTea(list);
            }
        }
    }
}
