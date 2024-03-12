package com.ruoyi.userslist.service;

import java.util.List;
import com.ruoyi.userslist.domain.Teacher;

/**
 * 老师Service接口
 *
 * @author ruoyi
 * @date 2024-03-12
 */
public interface ITeacherService
{
    /**
     * 查询老师
     *
     * @param teaId 老师主键
     * @return 老师
     */
    public Teacher selectTeacherByTeaId(String teaId);

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
    public int deleteTeacherByTeaIds(String[] teaIds);

    /**
     * 删除老师信息
     *
     * @param teaId 老师主键
     * @return 结果
     */
    public int deleteTeacherByTeaId(String teaId);
}
