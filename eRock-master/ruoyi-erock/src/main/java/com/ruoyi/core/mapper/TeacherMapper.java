package com.ruoyi.core.mapper;

import java.util.List;
import com.ruoyi.core.domain.Teacher;
import com.ruoyi.core.domain.StuToTea;

/**
 * 老师Mapper接口
 * 
 * @author heye
 * @date 2024-03-17
 */
public interface TeacherMapper 
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
     * 删除老师
     * 
     * @param teaId 老师主键
     * @return 结果
     */
    public int deleteTeacherByTeaId(Long teaId);

    /**
     * 批量删除老师
     * 
     * @param teaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeacherByTeaIds(Long[] teaIds);

    /**
     * 批量删除多个学生对多个老师
     * 
     * @param teaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStuToTeaByTeaIds(Long[] teaIds);
    
    /**
     * 批量新增多个学生对多个老师
     * 
     * @param stuToTeaList 多个学生对多个老师列表
     * @return 结果
     */
    public int batchStuToTea(List<StuToTea> stuToTeaList);
    

    /**
     * 通过老师主键删除多个学生对多个老师信息
     * 
     * @param teaId 老师ID
     * @return 结果
     */
    public int deleteStuToTeaByTeaId(Long teaId);
}
