package com.ruoyi.teachingExchange.service;

import com.ruoyi.teachingExchange.domain.TeachingTable;

import java.util.List;

/**
 * A1 线上学习 教学
 Service接口
 *
 * @author heye
 * @date 2024-03-20
 */
public interface ITeachingTableService
{
    /**
     * 查询A1 线上学习 教学
 
     *
     * @param teachingId A1 线上学习 教学
 主键
     * @return A1 线上学习 教学
 
     */
    public TeachingTable selectTeachingTableByTeachingId(Long teachingId);

    /**
     * 查询A1 线上学习 教学
 列表
     *
     * @param teachingTable A1 线上学习 教学
 
     * @return A1 线上学习 教学
 集合
     */
    public List<TeachingTable> selectTeachingTableList(TeachingTable teachingTable);

    /**
     * 新增A1 线上学习 教学
 
     *
     * @param teachingTable A1 线上学习 教学
 
     * @return 结果
     */
    public int insertTeachingTable(TeachingTable teachingTable);

    /**
     * 修改A1 线上学习 教学
 
     *
     * @param teachingTable A1 线上学习 教学
 
     * @return 结果
     */
    public int updateTeachingTable(TeachingTable teachingTable);

    /**
     * 批量删除A1 线上学习 教学
 
     *
     * @param teachingIds 需要删除的A1 线上学习 教学
 主键集合
     * @return 结果
     */
    public int deleteTeachingTableByTeachingIds(Long[] teachingIds);

    /**
     * 删除A1 线上学习 教学
 信息
     *
     * @param teachingId A1 线上学习 教学
 主键
     * @return 结果
     */
    public int deleteTeachingTableByTeachingId(Long teachingId);
}
