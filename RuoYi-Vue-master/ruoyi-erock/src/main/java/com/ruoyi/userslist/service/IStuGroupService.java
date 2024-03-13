package com.ruoyi.userslist.service;

import java.util.List;
import com.ruoyi.userslist.domain.StuGroup;

/**
 * 小组Service接口
 * 
 * @author ruoyi
 * @date 2024-03-13
 */
public interface IStuGroupService 
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
     * 批量删除小组
     * 
     * @param gIds 需要删除的小组主键集合
     * @return 结果
     */
    public int deleteStuGroupByGIds(Long[] gIds);

    /**
     * 删除小组信息
     * 
     * @param gId 小组主键
     * @return 结果
     */
    public int deleteStuGroupByGId(Long gId);
}
