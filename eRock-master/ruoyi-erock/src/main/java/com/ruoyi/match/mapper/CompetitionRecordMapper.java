package com.ruoyi.match.mapper;

import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.domain.CompetitionRecord;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * C 比赛记录Mapper接口
 *
 * @author houq
 * @date 2024-04-08
 */
public interface CompetitionRecordMapper {
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

	/**
	 * 获取课外赛学生信息
	 *
	 * @return
	 */
	public List<HashMap<String, String>> findCBallteamKw();

	/**
	 * 获取课外赛信息
	 *
	 * @return
	 */
	public List<HashMap<String, Object>> findRecordKwByStuId(Long stuId);

	/**
	 * 根据学号和比赛id获取学生信息
	 *
	 * @return
	 */
	public HashMap<String, Object> findRecordKwByStuIdAndCcrId(HashMap<String, String> map);

	/**
	 * 审核学生上传的资料
	 *
	 * @return
	 */
	public Integer updateAudit(HashMap<String, String> map);

	@Select("SELECT ccr.cc_r_speci AS speci, cps.job_id AS jobId FROM c_competition_record AS ccr INNER JOIN c_ballteam AS cb ON cb.cc_r_id = ccr.cc_r_id INNER JOIN c_personnel_sheet AS cps ON cps.bal_id = cb.bal_id WHERE ccr.cc_r_id = #{ccrId} AND cps.stu_id = #{stuId}")
	public HashMap<String, Object> selectSpeci(HashMap<String, String> map);

	/**
	 * 获取学生课外赛加分
	 */
	public List<CPersonnelSheet> selectScoreByCcrId(HashMap<String, String> map);
}
