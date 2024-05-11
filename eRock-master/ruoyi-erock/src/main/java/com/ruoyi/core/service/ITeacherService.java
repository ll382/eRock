package com.ruoyi.core.service;

import java.util.List;
import com.ruoyi.core.domain.Teacher;

/**
 * 老师Service接口
 * 
 * @author heye
 * @date 2024-03-17
 */
public interface ITeacherService 
{
    /**
     * 查询老师
     * 
     * @param teaId 老师主键
     * @return 老师
     */
    public Teacher selectTeacherByTeaId(Long teaId);

    /**
     * 查询老师列表
     * 
     * @param teacher 老师
     * @return 老师集合
     */
    public List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 新增老师
     * 
     * @param teacher 老师
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 修改老师
     * 
     * @param teacher 老师
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 批量删除老师
     * 
     * @param teaIds 需要删除的老师主键集合
     * @return 结果
     */
    public int deleteTeacherByTeaIds(Long[] teaIds);

    /**
     * 删除老师信息
     * 
     * @param teaId 老师主键
     * @return 结果
     */
    public int deleteTeacherByTeaId(Long teaId);
}
