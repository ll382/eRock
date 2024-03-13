package com.ruoyi.userslist.mapper;

import java.util.List;
import com.ruoyi.userslist.domain.StuGroup;
import com.ruoyi.userslist.domain.Student;

/**
 * 小组Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-13
 */
public interface StuGroupMapper
{
    /**
     * 查询小组
     *
     * @param gId 小组主键
     * @return 小组
     */
    public StuGroup selectStuGroupByGId(Long gId);

    /**
     * 查询小组列表
     *
     * @param stuGroup 小组
     * @return 小组集合
     */
    public List<StuGroup> selectStuGroupList(StuGroup stuGroup);

    /**
     * 新增小组
     *
     * @param stuGroup 小组
     * @return 结果
     */
    public int insertStuGroup(StuGroup stuGroup);

    /**
     * 修改小组
     *
     * @param stuGroup 小组
     * @return 结果
     */
    public int updateStuGroup(StuGroup stuGroup);

    /**
     * 删除小组
     *
     * @param gId 小组主键
     * @return 结果
     */
    public int deleteStuGroupByGId(Long gId);

    /**
     * 批量删除小组
     *
     * @param gIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStuGroupByGIds(Long[] gIds);

    /**
     * 批量删除学生
     *
     * @param gIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByGIds(Long[] gIds);

    /**
     * 批量新增学生
     *
     * @param studentList 学生列表
     * @return 结果
     */
    public int batchStudent(List<Student> studentList);


    /**
     * 通过小组主键删除学生信息
     *
     * @param gId 小组ID
     * @return 结果
     */
    public int deleteStudentByGId(Long gId);
}
