package com.ruoyi.match.service;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.match.domain.CompetitionRecord;

/**
 * C 比赛记录Service接口
 *
 * @author houq
 * @date 2024-04-08
 */
public interface ICompetitionRecordService
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
	 * 批量删除C 比赛记录
	 *
	 * @param ccRIds 需要删除的C 比赛记录主键集合
	 * @return 结果
	 */
	public int deleteCompetitionRecordByCcRIds(Long[] ccRIds);

	/**
	 * 删除C 比赛记录信息
	 *
	 * @param ccRId C 比赛记录主键
	 * @return 结果
	 */
	public int deleteCompetitionRecordByCcRId(Long ccRId);
	
	/**
	 * 获取比赛记录记录
	 *
	 * @param speci
	 * @return
	 */
	public List<HashMap<String, String>> selectGameRecord(String speci);

}
