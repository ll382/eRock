package com.ruoyi.match.mapper;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.domain.CBallteam;

/**
 * C 比赛记录Mapper接口
 *
 * @author houq
 * @date 2024-04-08
 */
public interface CompetitionRecordMapper
{
	/**
	 * 查询C 比赛记录
	 *
	 * @param ccRId C 比赛记录主键
	 * @return C 比赛记录
	 */
	public CompetitionRecord selectCompetitionRecordByCcRId(Long ccRId);

	/**
	 * 查询C 比赛记录列表
	 *
	 * @param competitionRecord C 比赛记录
	 * @return C 比赛记录集合
	 */
	public List<CompetitionRecord> selectCompetitionRecordList(CompetitionRecord competitionRecord);

	/**
	 * 新增C 比赛记录
	 *
	 * @param competitionRecord C 比赛记录
	 * @return 结果
	 */
	public int insertCompetitionRecord(CompetitionRecord competitionRecord);

	/**
	 * 修改C 比赛记录
	 *
	 * @param competitionRecord C 比赛记录
	 * @return 结果
	 */
	public int updateCompetitionRecord(CompetitionRecord competitionRecord);

	/**
	 * 删除C 比赛记录
	 *
	 * @param ccRId C 比赛记录主键
	 * @return 结果
	 */
	public int deleteCompetitionRecordByCcRId(Long ccRId);

	/**
	 * 批量删除C 比赛记录
	 *
	 * @param ccRIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteCompetitionRecordByCcRIds(Long[] ccRIds);

	/**
	 * 批量删除球队参赛
	 *
	 * @param ccRIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteCBallteamByCcRIds(Long[] ccRIds);

	/**
	 * 批量新增球队参赛
	 *
	 * @param cBallteamList 球队参赛列表
	 * @return 结果
	 */
	public int batchCBallteam(List<CBallteam> cBallteamList);


	/**
	 * 通过C 比赛记录主键删除球队参赛信息
	 *
	 * @param ccRId C 比赛记录ID
	 * @return 结果
	 */
	public int deleteCBallteamByCcRId(Long ccRId);
	
	/**
	 * 获取比赛记录记录
	 *
	 * @param speci
	 * @return
	 */
	public List<HashMap<String, String>> selectGameRecord(String speci);
}
