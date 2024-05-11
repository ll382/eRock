package com.ruoyi.core.mapper;

import java.util.List;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.domain.Student;

/**
 * 小组Mapper接口
 * 
 * @author heye
 * @date 2024-03-15
 */
public interface StuGroupMapper 
{
    /**
     * 查询小组
     * 
     * @param ggId 小组主键
     * @return 小组
     */
    public StuGroup selectStuGroupByGgId(Long ggId);

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
     * @param ggId 小组主键
     * @return 结果
     */
    public int deleteStuGroupByGgId(Long ggId);

    /**
     * 批量删除小组
     * 
     * @param ggIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStuGroupByGgIds(Long[] ggIds);

    /**
     * 批量删除学生
     * 
     * @param ggIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByStuIds(Long[] ggIds);
    
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
     * @param ggId 小组ID
     * @return 结果
     */
    public int deleteStudentByStuId(Long ggId);
}
