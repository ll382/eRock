package com.ruoyi.match.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.match.domain.*;
import com.ruoyi.match.mapper.CBallteamMapper;
import com.ruoyi.match.mapper.CPersonnelSheetMapper;
import com.ruoyi.match.mapper.CompetitionRecordMapper;
import com.ruoyi.match.service.ICBallteamService;
import com.ruoyi.match.service.ICPersonnelSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * C 球队内人员Service业务层处理
 *
 * @author heye
 * @date 2024-03-19
 */
@Service
public class CPersonnelSheetServiceImpl implements ICPersonnelSheetService {
	@Autowired
	private CPersonnelSheetMapper cPersonnelSheetMapper;

	@Autowired
	private CompetitionRecordMapper competitionRecordMapper;

	@Autowired
	private ICBallteamService cBallteamService;

	@Autowired
	private CBallteamMapper cBallteamMapper;

	/**
	 * 查询C 球队内人员
	 *
	 * @param psId C 球队内人员主键
	 * @return C 球队内人员
	 */
	@Override
	public CPersonnelSheet selectCPersonnelSheetByPsId(Long psId) {
		return cPersonnelSheetMapper.selectCPersonnelSheetByPsId(psId);
	}

	/**
	 * 查询C 球队内人员列表
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return C 球队内人员
	 */
	@Override
	public List<CPersonnelSheet> selectCPersonnelSheetList(CPersonnelSheet cPersonnelSheet) {
		return cPersonnelSheetMapper.selectCPersonnelSheetList(cPersonnelSheet);
	}

	/**
	 * 新增C 球队内人员
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertCPersonnelSheet(CPersonnelSheet cPersonnelSheet) {
		int rows = cPersonnelSheetMapper.insertCPersonnelSheet(cPersonnelSheet);
		insertCProof(cPersonnelSheet);
		return rows;
	}

	/**
	 * 修改C 球队内人员
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateCPersonnelSheet(CPersonnelSheet cPersonnelSheet) {
		cPersonnelSheetMapper.deleteCProofByPsId(cPersonnelSheet.getPsId());
		insertCProof(cPersonnelSheet);
		return cPersonnelSheetMapper.updateCPersonnelSheet(cPersonnelSheet);
	}

	/**
	 * 批量删除C 球队内人员
	 *
	 * @param psIds 需要删除的C 球队内人员主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCPersonnelSheetByPsIds(Long[] psIds) {
		cPersonnelSheetMapper.deleteCProofByPsIds(psIds);
		return cPersonnelSheetMapper.deleteCPersonnelSheetByPsIds(psIds);
	}

	/**
	 * 删除C 球队内人员信息
	 *
	 * @param psId C 球队内人员主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCPersonnelSheetByPsId(Long psId) {
		cPersonnelSheetMapper.deleteCProofByPsId(psId);
		return cPersonnelSheetMapper.deleteCPersonnelSheetByPsId(psId);
	}

	/**
	 * 新增C 佐证信息
	 *
	 * @param cPersonnelSheet C 球队内人员对象
	 */
	public void insertCProof(CPersonnelSheet cPersonnelSheet) {
		List<CProof> cProofList = cPersonnelSheet.getCProofList();
		Long psId = cPersonnelSheet.getPsId();
		if (StringUtils.isNotNull(cProofList)) {
			List<CProof> list = new ArrayList<CProof>();
			for (CProof cProof : cProofList) {
				cProof.setPsId(psId);
				list.add(cProof);
			}
			if (list.size() > 0) {
				cPersonnelSheetMapper.batchCProof(list);
			}
		}
	}


	/**
	 * 根据球队id查询C 球队内人员列表
	 *
	 * @param balId
	 * @return
	 */
	@Override
	public List<CPersonnelSheet> findPersonnelShellByBalId(Long balId) {
		return cPersonnelSheetMapper.findPersonnelShellByBalId(balId);
	}

	/**
	 * 新增C 球队内人员信息
	 *
	 * @param cBallteam 球队参赛对象
	 */
	@Override
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

	@Override
	public CPersonnelSheet cpsUploadResource(KwUploadResource kwUploadResource) {
		String[] speci = {"友谊赛", "班赛", "校赛", "校际赛"};
		kwUploadResource.setCcRSpeci(speci[kwUploadResource.getCcRSpeciId()]);
		CPersonnelSheet result;
		CPersonnelSheet cPersonnelSheet = cPersonnelSheetMapper.judgeNull(kwUploadResource);
		if (cPersonnelSheet == null || !cPersonnelSheet.getStuId().equals(kwUploadResource.getStuId())) {
			result = insertCbUrl(kwUploadResource);
		} else {
			cPersonnelSheet.setPsUrl(kwUploadResource.getPsUrl());
			cPersonnelSheetMapper.updateCPersonnelSheet(cPersonnelSheet);
			result = cPersonnelSheet;
		}
		return result;
	}

	public CPersonnelSheet insertCbUrl(KwUploadResource kwUploadResource) {
		// 小组成员信息
		StuGroup stuGroup = cBallteamMapper.getStuGroupByStuId(kwUploadResource.getStuId());

		// 新增课外赛比赛记录
		CompetitionRecord competitionRecord = new CompetitionRecord();
		competitionRecord.setEnumId(7L);
		competitionRecord.setCcRTime(new Date());
		competitionRecord.setCcRSpeci(kwUploadResource.getCcRSpeci());
		competitionRecord.setCcRAudit(0L);
		competitionRecordMapper.insertCompetitionRecord(competitionRecord);

		// 新增参赛小组和成员
		CBallteam cBallteam = new CBallteam();
		cBallteam.setCcRId(competitionRecord.getCcRId());
		cBallteam.setGgId(stuGroup.getGgId());

		List<CPersonnelSheet> cPersonnelSheetList = new ArrayList<>();
		stuGroup.getStudentList().forEach(student -> {
			if (Objects.equals(student.getStuId(), kwUploadResource.getStuId())) {
				cPersonnelSheetList.add(new CPersonnelSheet(1L, null, student.getStuId(), student.getStuName(), kwUploadResource.getPsUrl(), 0));
			} else {
				cPersonnelSheetList.add(new CPersonnelSheet(1L, null, student.getStuId(), student.getStuName(), null, 0));
			}
		});
		cBallteam.setcPersonnelSheetList(cPersonnelSheetList);
		cBallteamService.insertCBallteam(cBallteam);

		Optional<CPersonnelSheet> firstMatch = cPersonnelSheetList.stream()
				.filter(sheet -> Objects.equals(sheet.getStuId(), kwUploadResource.getStuId()))
				.findFirst();
		return firstMatch.orElse(null);
	}
}
