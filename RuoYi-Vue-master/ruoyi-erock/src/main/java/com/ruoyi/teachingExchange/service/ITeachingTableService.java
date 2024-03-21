package com.ruoyi.teachingExchange.service;

import java.util.List;

import com.ruoyi.teachingExchange.domain.A1Communication;
import com.ruoyi.teachingExchange.domain.TeachingTable;

/**
 * A1 线上学习学生线上观看记录表Service接口
 *
 * @author heye
 * @date 2024-03-20
 */
public interface ITeachingTableService
{
    /**
     * 查询A1 线上学习学生线上观看记录表
     *
     * @param teachingId A1 线上学习学生线上观看记录表主键
     * @return A1 线上学习学生线上观看记录表
     */
    public TeachingTable selectTeachingTableByTeachingId(Long teachingId);

    /**
     * 查询A1 线上学习学生线上观看记录表列表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
     * @return A1 线上学习学生线上观看记录表集合
     */
    public List<TeachingTable> selectTeachingTableList(TeachingTable teachingTable);

    /**
     * 查询A1 线上学习学生线上观看记录表列表
     *
     * @return A1 线上学习学生线上观看记录表集合
     */
    public TeachingTable selectCommunicationList(Long commId);

    /**
     * 新增A1 线上学习学生线上观看记录表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
     * @return 结果
     */
    public int insertTeachingTable(TeachingTable teachingTable);

    /**
     * 修改A1 线上学习学生线上观看记录表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
     * @return 结果
     */
    public int updateTeachingTable(TeachingTable teachingTable);

    /**
     * 批量删除A1 线上学习学生线上观看记录表
     *
     * @param teachingIds 需要删除的A1 线上学习学生线上观看记录表主键集合
     * @return 结果
     */
    public int deleteTeachingTableByTeachingIds(Long[] teachingIds);

    /**
     * 删除A1 线上学习学生线上观看记录表信息
     *
     * @param teachingId A1 线上学习学生线上观看记录表主键
     * @return 结果
     */
    public int deleteTeachingTableByTeachingId(Long teachingId);
}
