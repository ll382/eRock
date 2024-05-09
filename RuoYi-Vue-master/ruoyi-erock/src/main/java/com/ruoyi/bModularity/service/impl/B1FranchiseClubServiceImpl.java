package com.ruoyi.bModularity.service.impl;

import com.ruoyi.bModularity.domain.B1FranchiseClub;
import com.ruoyi.bModularity.domain.B1MassSource;
import com.ruoyi.bModularity.domain.B1Student;
import com.ruoyi.bModularity.mapper.B1FranchiseClubMapper;
import com.ruoyi.bModularity.service.IB1FranchiseClubService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.Semester;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ruoyi.core.util.DateUtil.DateConvertString;

/**
 * 球队、社团训练Service业务层处理
 *
 * @author houq
 * @date 2024-03-27
 */
@Service
public class B1FranchiseClubServiceImpl implements IB1FranchiseClubService {
	@Autowired
	private B1FranchiseClubMapper b1FranchiseClubMapper;

	@Autowired
	private SelectUser selectUser;

	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	/**
	 * 查询球队、社团训练
	 *
	 * @param tcId 球队、社团训练主键
	 * @return 球队、社团训练
	 */
	@Override
	public B1FranchiseClub selectB1FranchiseClubByTcId(Long tcId) {
		return b1FranchiseClubMapper.selectB1FranchiseClubByTcId(tcId);
	}

	/**
	 * 查询球队、社团训练列表
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 球队、社团训练
	 */
	@Override
	public List<B1FranchiseClub> selectB1FranchiseClubList(B1FranchiseClub b1FranchiseClub) {
		return b1FranchiseClubMapper.selectB1FranchiseClubList(b1FranchiseClub);
	}

	/**
	 * 新增球队、社团训练
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertB1FranchiseClub(B1FranchiseClub b1FranchiseClub) {
		b1FranchiseClub.setTcTime(new Date());

		int rows = b1FranchiseClubMapper.insertB1FranchiseClub(b1FranchiseClub);
		insertB1MassSource(b1FranchiseClub);
		return rows;
	}

	/**
	 * 修改球队、社团训练
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateB1FranchiseClub(B1FranchiseClub b1FranchiseClub) {
		b1FranchiseClubMapper.deleteB1MassSourceByTcId(b1FranchiseClub.getTcId());
		insertB1MassSource(b1FranchiseClub);
		return b1FranchiseClubMapper.updateB1FranchiseClub(b1FranchiseClub);
	}

	/**
	 * 批量删除球队、社团训练
	 *
	 * @param tcIds 需要删除的球队、社团训练主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteB1FranchiseClubByTcIds(Long[] tcIds) {
		b1FranchiseClubMapper.deleteB1MassSourceByTcIds(tcIds);
		return b1FranchiseClubMapper.deleteB1FranchiseClubByTcIds(tcIds);
	}

	/**
	 * 删除球队、社团训练信息
	 *
	 * @param tcId 球队、社团训练主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteB1FranchiseClubByTcId(Long tcId) {
		b1FranchiseClubMapper.deleteB1MassSourceByTcId(tcId);
		return b1FranchiseClubMapper.deleteB1FranchiseClubByTcId(tcId);
	}

	/**
	 * 新增B1 资源信息
	 *
	 * @param b1FranchiseClub 球队、社团训练对象
	 */
	public void insertB1MassSource(B1FranchiseClub b1FranchiseClub) {
		List<B1MassSource> b1MassSourceList = b1FranchiseClub.getB1MassSourceList();
		Long tcId = b1FranchiseClub.getTcId();
		if (StringUtils.isNotNull(b1MassSourceList)) {
			List<B1MassSource> list = new ArrayList<B1MassSource>();
			for (B1MassSource b1MassSource : b1MassSourceList) {
				b1MassSource.setTcId(tcId);
				list.add(b1MassSource);
			}
			if (!list.isEmpty()) {
				b1FranchiseClubMapper.batchB1MassSource(list);
			}
		}
	}

	/**
	 * 获取学生信息第一页
	 *
	 * @return 结果
	 */
	@Override
	public List<B1Student> selectB1Student() {
		return selectUser.selectStudent(b1FranchiseClubMapper.selectB1Student());
	}

	/**
	 * 获取学生本周训练记录
	 *
	 * @param id
	 * @return
	 */
	@Override
	public B1FranchiseClub getStudentThisWeekFranchise(Long id) {
		return b1FranchiseClubMapper.getStudentThisWeekFranchise(id);
	}

	/**
	 * 获取学生本周的训练分数
	 *
	 * @return 结果
	 */
	@Override
	public Integer getThisWeekFranchiseNum(Long id) {
		b1ModuleScore(id);
		return b1FranchiseClubMapper.getThisWeekFranchiseNum(id);
	}

	public void b1ModuleScore(Long id) {
		Semester semester = b1FranchiseClubMapper.selectSemester();
		long startDate = semester.getStartDate().getTime();
		long finishDate = semester.getFinishDate().getTime();
		int requiresTrainNum = (int) ((finishDate - startDate) / 1000 / 60 / 60 / 24 / 7 * 2);
		System.out.println(DateConvertString(semester.getStartDate()) + DateConvertString(semester.getFinishDate()));

		int stuTrainNum = b1FranchiseClubMapper.countTrainScore(DateConvertString(semester.getStartDate()), DateConvertString(semester.getFinishDate()), id);

		TotalScore totalScore = new TotalScore();
		totalScore.setStuId(id);
		totalScore.setSemesterId(d2CertificateMapper.selectDate(new Date()).getSemesterId());
		Long tsId = selectUser.judgeInformation(totalScore);

		ModuleScore moduleScore = new ModuleScore();
		moduleScore.setTsId(tsId);
		moduleScore.setEnumId(4L);
		moduleScore.setAvsScore(scoreCount(requiresTrainNum, stuTrainNum));
		selectUser.judgeModuleScore(moduleScore);
	}

	public BigDecimal scoreCount(int requiresTrainNum, int stuTrainNum) {
		BigDecimal defaultScore = new BigDecimal("20");
		int difference = Math.max(requiresTrainNum - stuTrainNum, 0);
		BigDecimal deduction = new BigDecimal(String.valueOf(difference));
		BigDecimal finalScore = defaultScore.subtract(deduction);
		return finalScore.max(BigDecimal.ZERO);
	}
}
