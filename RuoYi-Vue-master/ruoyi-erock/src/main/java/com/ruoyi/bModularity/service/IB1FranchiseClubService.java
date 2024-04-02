package com.ruoyi.bModularity.service;

import com.ruoyi.bModularity.domain.B1FranchiseClub;

import java.util.HashMap;
import java.util.List;

/**
 * 球队、社团训练Service接口
 *
 * @author houq
 * @date 2024-03-27
 */
public interface IB1FranchiseClubService {
	/**
	 * 查询球队、社团训练
	 *
	 * @param tcId 球队、社团训练主键
	 * @return 球队、社团训练
	 */
	public B1FranchiseClub selectB1FranchiseClubByTcId(Long tcId);
	
	/**
	 * 查询球队、社团训练列表
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 球队、社团训练集合
	 */
	public List<B1FranchiseClub> selectB1FranchiseClubList(B1FranchiseClub b1FranchiseClub);
	
	/**
	 * 新增球队、社团训练
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 结果
	 */
	public int insertB1FranchiseClub(B1FranchiseClub b1FranchiseClub);
	
	/**
	 * 修改球队、社团训练
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 结果
	 */
	public int updateB1FranchiseClub(B1FranchiseClub b1FranchiseClub);
	
	/**
	 * 批量删除球队、社团训练
	 *
	 * @param tcIds 需要删除的球队、社团训练主键集合
	 * @return 结果
	 */
	public int deleteB1FranchiseClubByTcIds(Long[] tcIds);
	
	/**
	 * 删除球队、社团训练信息
	 *
	 * @param tcId 球队、社团训练主键
	 * @return 结果
	 */
	public int deleteB1FranchiseClubByTcId(Long tcId);
	
	/**
	 * 获取学生信息第一页
	 *
	 * @return 结果
	 */
	public List<HashMap<String, String>> selectB1Student();
	
	
	/**
	 * 获取学生本周训练记录
	 * @param id
	 * @return
	 */
	public B1FranchiseClub getStudentThisWeekFranchise(Long id);
	
	/**
	 * 获取学生本周训练次数
	 * @param id
	 * @return
	 */
	public Integer getThisWeekFranchiseNum(Long id);
}
