package com.ruoyi.match.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.domain.Student;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.mapper.CBallteamMapper;
import com.ruoyi.match.service.ICBallteamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
		List<CPersonnelSheet> cPersonnelSheetList = cBallteam.getCPersonnelSheetList();
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
		cBallteamMapper.insertStuGroup(stuGroup);
		cBallteam.setGgId(cBallteamMapper.getNewSTuGroup());
		int rows = cBallteamMapper.insertCBallteam(cBallteam);
		insertCPersonnelSheet(cBallteam);
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
