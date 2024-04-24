package com.ruoyi.match.service;

import com.ruoyi.match.domain.CBallteam;

import java.util.HashMap;
import java.util.List;

/**
 * 球队参赛Service接口
 *
 * @author heye
 * @date 2024-03-19
 */
public interface ICBallteamService {
	/**
	 * 查询球队参赛
	 *
	 * @param balId 球队参赛主键
	 * @return 球队参赛
	 */
	public CBallteam selectCBallteamByBalId(Long balId);

	/**
	 * 查询球队参赛列表
	 *
	 * @param cBallteam 球队参赛
	 * @return 球队参赛集合
	 */
	public List<CBallteam> selectCBallteamList(CBallteam cBallteam);

	/**
	 * 新增球队参赛
	 *
	 * @param cBallteam 球队参赛
	 * @return 结果
	 */
	public int insertCBallteam(CBallteam cBallteam);

	/**
	 * 修改球队参赛
	 *
	 * @param cBallteam 球队参赛
	 * @return 结果
	 */
	public int updateCBallteam(CBallteam cBallteam);

	/**
	 * 批量删除球队参赛
	 *
	 * @param balIds 需要删除的球队参赛主键集合
	 * @return 结果
	 */
	public int deleteCBallteamByBalIds(Long[] balIds);

	/**
	 * 删除球队参赛信息
	 *
	 * @param balId 球队参赛主键
	 * @return 结果
	 */
	public int deleteCBallteamByBalId(Long balId);


	/**
	 * 获取比赛小组信息
	 *
	 * @param ccRId
	 * @return
	 */
	public List<CBallteam> selectCompetitionRecordByCcRName(Long ccRId);

	/**
	 * 新增小组
	 *
	 * @param map 小组
	 * @return 结果
	 */
	public int insertStuGroup(HashMap<String, Object> map);

	/**
	 * 批量修改球队参赛
	 *
	 * @param cBallteamList
	 * @return
	 */
	public int updateCBallteams(List<CBallteam> cBallteamList);
}
