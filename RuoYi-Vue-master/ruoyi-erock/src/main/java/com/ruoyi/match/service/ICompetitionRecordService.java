package com.ruoyi.match.service;

import java.util.List;
import com.ruoyi.match.domain.CompetitionRecord;

/**
 * 比赛记录Service接口
 * 
 * @author heye
 * @date 2024-03-18
 */
public interface ICompetitionRecordService 
{
    /**
     * 查询比赛记录
     * 
     * @param ccRId 比赛记录主键
     * @return 比赛记录
     */
    public CompetitionRecord selectCompetitionRecordByCcRId(Long ccRId);

    /**
     * 查询比赛记录列表
     * 
     * @param competitionRecord 比赛记录
     * @return 比赛记录集合
     */
    public List<CompetitionRecord> selectCompetitionRecordList(CompetitionRecord competitionRecord);

    /**
     * 新增比赛记录
     * 
     * @param competitionRecord 比赛记录
     * @return 结果
     */
    public int insertCompetitionRecord(CompetitionRecord competitionRecord);

    /**
     * 修改比赛记录
     * 
     * @param competitionRecord 比赛记录
     * @return 结果
     */
    public int updateCompetitionRecord(CompetitionRecord competitionRecord);

    /**
     * 批量删除比赛记录
     * 
     * @param ccRIds 需要删除的比赛记录主键集合
     * @return 结果
     */
    public int deleteCompetitionRecordByCcRIds(Long[] ccRIds);

    /**
     * 删除比赛记录信息
     * 
     * @param ccRId 比赛记录主键
     * @return 结果
     */
    public int deleteCompetitionRecordByCcRId(Long ccRId);
}
