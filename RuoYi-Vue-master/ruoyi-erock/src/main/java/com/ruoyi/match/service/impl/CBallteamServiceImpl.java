package com.ruoyi.match.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.mapper.CBallteamMapper;
import com.ruoyi.match.service.ICBallteamService;
import com.ruoyi.match.service.MatchScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 球队参赛Service业务层处理
 *
 * @author heye
 * @date 2024-03-19
 */
@Service
public class CBallteamServiceImpl implements ICBallteamService {
	@Autowired
	private CBallteamMapper cBallteamMapper;

	@Autowired
	private MatchScoreService matchScoreService;

	/**
	 * 查询球队参赛
	 *
	 * @param balId 球队参赛主键
	 * @return 球队参赛
	 */
	@Override
	public CBallteam selectCBallteamByBalId(Long balId) {
		return cBallteamMapper.selectCBallteamByBalId(balId);
	}

	/**
	 * 查询球队参赛列表
	 *
	 * @param cBallteam 球队参赛
	 * @return 球队参赛
	 */
	@Override
	public List<CBallteam> selectCBallteamList(CBallteam cBallteam) {
		return cBallteamMapper.selectCBallteamList(cBallteam);
	}

	/**
	 * 新增球队参赛
	 *
	 * @param cBallteam 球队参赛
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertCBallteam(CBallteam cBallteam) {
		int rows = cBallteamMapper.insertCBallteam(cBallteam);
		insertCPersonnelSheet(cBallteam);
		return rows;
	}

	/**
	 * 修改球队参赛
	 *
	 * @param cBallteam 球队参赛
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateCBallteam(CBallteam cBallteam) {
		cBallteamMapper.deleteCPersonnelSheetByBalId(cBallteam.getBalId());
		insertCPersonnelSheet(cBallteam);
		return cBallteamMapper.updateCBallteam(cBallteam);
	}

	/**
	 * 批量删除球队参赛
	 *
	 * @param balIds 需要删除的球队参赛主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCBallteamByBalIds(Long[] balIds) {
		cBallteamMapper.deleteStuGroupByGgId(balIds[0]);
		cBallteamMapper.deleteCPersonnelSheetByBalIds(balIds);
		return cBallteamMapper.deleteCBallteamByBalIds(balIds);
	}

	/**
	 * 删除球队参赛信息
	 *
	 * @param balId 球队参赛主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCBallteamByBalId(Long balId) {
		cBallteamMapper.deleteCPersonnelSheetByBalId(balId);
		return cBallteamMapper.deleteCBallteamByBalId(balId);
	}

	/**
	 * 新增C 球队内人员信息
	 *
	 * @param cBallteam 球队参赛对象
	 */
	public void insertCPersonnelSheet(CBallteam cBallteam) {
		List<CPersonnelSheet> cPersonnelSheetList = getCPersonnelSheets(cBallteam);
		Long balId = cBallteam.getBalId();
		if (StringUtils.isNotNull(cPersonnelSheetList)) {
			List<CPersonnelSheet> list = new ArrayList<CPersonnelSheet>();
			for (CPersonnelSheet cPersonnelSheet : cPersonnelSheetList) {
				cPersonnelSheet.setBalId(balId);
				list.add(cPersonnelSheet);
			}
			if (!list.isEmpty()) {
				cBallteamMapper.batchCPersonnelSheet(list);
			}
		}
	}

	/**
	 * 设置成员分数
	 * 1-2名为1等计3分，3-4名为2等计2分，5-6名为3等计1分
	 */
	private static List<CPersonnelSheet> getCPersonnelSheets(CBallteam cBallteam) {
		List<CPersonnelSheet> cPersonnelSheetList = cBallteam.getCPersonnelSheetList();
		int score = 3; // 初始分数
		for (int i = 0; i < cPersonnelSheetList.size(); i++) {
			CPersonnelSheet cPersonnelSheet = cPersonnelSheetList.get(i);

			// 每两个元素减少分数
			if (i % 2 == 0 && i > 0) {
				score--;
			}

			// 设置分数，不低于1
			cPersonnelSheet.setPsNum(BigDecimal.valueOf(Math.max(score, 1)));
		}
		return cPersonnelSheetList;
	}

	/**
	 * 获取比赛小组信息
	 *
	 * @param ccRId
	 * @return
	 */
	@Override
	public List<CBallteam> selectCompetitionRecordByCcRName(Long ccRId) {
		return cBallteamMapper.selectCompetitionRecordByCcRName(ccRId);
	}

	/**
	 * 新增小组
	 *
	 * @param map 小组
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertStuGroup(HashMap<String, Object> map) {
		StuGroup stuGroup = new StuGroup();
		stuGroup.setGgName(String.valueOf(map.get("ggName")));
		CBallteam cBallteam = JSON.parseObject(JSON.toJSONString(map), CBallteam.class);
		// 新增小组
		stuGroup.setStuGroupLeader(cBallteam.getCPersonnelSheetList().get(0).getStuId());   // 设置组长
		cBallteamMapper.insertStuGroup(stuGroup);
		// 获取最新插入的小组id
		cBallteam.setGgId(cBallteamMapper.getNewSTuGroup());
		// 新增球队参赛
		int rows = cBallteamMapper.insertCBallteam(cBallteam);
		// 新增球队内人员
		insertCPersonnelSheet(cBallteam);

		// C1模块分数新增或修改
		matchScoreService.modifiedModuleScoreMain(cBallteam.getcPersonnelSheetList());
		return rows;
	}

	/**
	 * 批量修改球队参赛
	 *
	 * @param cBallteamList
	 * @return
	 */
	@Override
	public int updateCBallteams(List<CBallteam> cBallteamList) {
		cBallteamList.forEach(this::updateCBallteam);
		return 1;
	}
}
