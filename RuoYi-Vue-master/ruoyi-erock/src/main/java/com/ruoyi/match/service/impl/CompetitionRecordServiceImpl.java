package com.ruoyi.match.service.impl;

import java.util.HashMap;
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
 * C 比赛记录Service业务层处理
 *
 * @author houq
 * @date 2024-04-08
 */
@Service
public class CompetitionRecordServiceImpl implements ICompetitionRecordService
{
	@Autowired
	private CompetitionRecordMapper competitionRecordMapper;

	/**
	 * 查询C 比赛记录
	 *
	 * @param ccRId C 比赛记录主键
	 * @return C 比赛记录
	 */
	@Override
	public CompetitionRecord selectCompetitionRecordByCcRId(Long ccRId)
	{
		return competitionRecordMapper.selectCompetitionRecordByCcRId(ccRId);
	}

	/**
	 * 查询C 比赛记录列表
	 *
	 * @param competitionRecord C 比赛记录
	 * @return C 比赛记录
	 */
	@Override
	public List<CompetitionRecord> selectCompetitionRecordList(CompetitionRecord competitionRecord)
	{
		return competitionRecordMapper.selectCompetitionRecordList(competitionRecord);
	}

	/**
	 * 新增C 比赛记录
	 *
	 * @param competitionRecord C 比赛记录
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
	 * 修改C 比赛记录
	 *
	 * @param competitionRecord C 比赛记录
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
	 * 批量删除C 比赛记录
	 *
	 * @param ccRIds 需要删除的C 比赛记录主键
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
	 * 删除C 比赛记录信息
	 *
	 * @param ccRId C 比赛记录主键
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
	 * @param competitionRecord C 比赛记录对象
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
	
	/**
	 * 获取比赛记录记录
	 *
	 * @param speci
	 * @return
	 */
	@Override
	public List<HashMap<String, String>> selectGameRecord(String speci) {
		return competitionRecordMapper.selectGameRecord(speci);
	}

}
