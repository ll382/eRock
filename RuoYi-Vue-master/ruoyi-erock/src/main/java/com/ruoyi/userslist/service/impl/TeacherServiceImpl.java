package com.ruoyi.userslist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.userslist.mapper.TeacherMapper;
import com.ruoyi.userslist.domain.Teacher;
import com.ruoyi.userslist.service.ITeacherService;

/**
 * 老师Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-13
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
    public Teacher selectTeacherByTeaId(String teaId)
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
    @Override
    public int insertTeacher(Teacher teacher)
    {
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改老师
     * 
     * @param teacher 老师
     * @return 结果
     */
    @Override
    public int updateTeacher(Teacher teacher)
    {
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除老师
     * 
     * @param teaIds 需要删除的老师主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByTeaIds(String[] teaIds)
    {
        return teacherMapper.deleteTeacherByTeaIds(teaIds);
    }

    /**
     * 删除老师信息
     * 
     * @param teaId 老师主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByTeaId(String teaId)
    {
        return teacherMapper.deleteTeacherByTeaId(teaId);
    }
}
