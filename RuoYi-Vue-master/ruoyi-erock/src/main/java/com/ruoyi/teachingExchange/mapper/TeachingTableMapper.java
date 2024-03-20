package com.ruoyi.teachingExchange.mapper;

import com.ruoyi.teachingExchange.domain.A1Communication;
import com.ruoyi.teachingExchange.domain.TeachingTable;

import java.util.List;

/**
 * A1 线上学习 教学
 Mapper接口
 *
 * @author heye
 * @date 2024-03-20
 */
public interface TeachingTableMapper
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
     * 删除A1 线上学习 教学
 
     *
     * @param teachingId A1 线上学习 教学
 主键
     * @return 结果
     */
    public int deleteTeachingTableByTeachingId(Long teachingId);

    /**
     * 批量删除A1 线上学习 教学
 
     *
     * @param teachingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachingTableByTeachingIds(Long[] teachingIds);

    /**
     * 批量删除A1交流
     *
     * @param teachingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA1CommunicationByTeachingIds(Long[] teachingIds);

    /**
     * 批量新增A1交流
     *
     * @param a1CommunicationList A1交流列表
     * @return 结果
     */
    public int batchA1Communication(List<A1Communication> a1CommunicationList);


    /**
     * 通过A1 线上学习 教学
 主键删除A1交流信息
     *
     * @param teachingId A1 线上学习 教学
 ID
     * @return 结果
     */
    public int deleteA1CommunicationByTeachingId(Long teachingId);
}
