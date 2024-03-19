package com.ruoyi.match.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.mapper.CompetitionRecordMapper;
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.service.ICompetitionRecordService;

/**
 * 比赛记录Service业务层处理
 * 
 * @author heye
 * @date 2024-03-18
 */
@Service
public class CompetitionRecordServiceImpl implements ICompetitionRecordService 
{
    @Autowired
    private CompetitionRecordMapper competitionRecordMapper;

    /**
     * 查询比赛记录
     * 
     * @param ccRId 比赛记录主键
     * @return 比赛记录
     */
    @Override
    public CompetitionRecord selectCompetitionRecordByCcRId(Long ccRId)
    {
        return competitionRecordMapper.selectCompetitionRecordByCcRId(ccRId);
    }

    /**
     * 查询比赛记录列表
     * 
     * @param competitionRecord 比赛记录
     * @return 比赛记录
     */
    @Override
    public List<CompetitionRecord> selectCompetitionRecordList(CompetitionRecord competitionRecord)
    {
        return competitionRecordMapper.selectCompetitionRecordList(competitionRecord);
    }

    /**
     * 新增比赛记录
     * 
     * @param competitionRecord 比赛记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCompetitionRecord(CompetitionRecord competitionRecord)
    {
        int rows = competitionRecordMapper.insertCompetitionRecord(competitionRecord);
        insertCBallteam(competitionRecord);
        return rows;
    }

    /**
     * 修改比赛记录
     * 
     * @param competitionRecord 比赛记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCompetitionRecord(CompetitionRecord competitionRecord)
    {
        competitionRecordMapper.deleteCBallteamByCcRId(competitionRecord.getCcRId());
        insertCBallteam(competitionRecord);
        return competitionRecordMapper.updateCompetitionRecord(competitionRecord);
    }

    /**
     * 批量删除比赛记录
     * 
     * @param ccRIds 需要删除的比赛记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCompetitionRecordByCcRIds(Long[] ccRIds)
    {
        competitionRecordMapper.deleteCBallteamByCcRIds(ccRIds);
        return competitionRecordMapper.deleteCompetitionRecordByCcRIds(ccRIds);
    }

    /**
     * 删除比赛记录信息
     * 
     * @param ccRId 比赛记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCompetitionRecordByCcRId(Long ccRId)
    {
        competitionRecordMapper.deleteCBallteamByCcRId(ccRId);
        return competitionRecordMapper.deleteCompetitionRecordByCcRId(ccRId);
    }

    /**
     * 新增球队参赛信息
     * 
     * @param competitionRecord 比赛记录对象
     */
    public void insertCBallteam(CompetitionRecord competitionRecord)
    {
        List<CBallteam> cBallteamList = competitionRecord.getCBallteamList();
        Long ccRId = competitionRecord.getCcRId();
        if (StringUtils.isNotNull(cBallteamList))
        {
            List<CBallteam> list = new ArrayList<CBallteam>();
            for (CBallteam cBallteam : cBallteamList)
            {
                cBallteam.setCcRId(ccRId);
                list.add(cBallteam);
            }
            if (list.size() > 0)
            {
                competitionRecordMapper.batchCBallteam(list);
            }
        }
    }
}
