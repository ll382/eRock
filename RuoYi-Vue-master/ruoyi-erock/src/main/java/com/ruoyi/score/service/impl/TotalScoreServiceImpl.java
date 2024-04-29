package com.ruoyi.score.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.score.mapper.TotalScoreMapper;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.service.ITotalScoreService;

/**
 * 总分Service业务层处理
 *
 * @author houq
 * @date 2024-04-22
 */
@Service
public class TotalScoreServiceImpl implements ITotalScoreService {
	@Autowired
	private TotalScoreMapper totalScoreMapper;

	@Autowired
	private ModuleScoreMapper moduleScoreMapper;

	/**
	 * 查询总分
	 *
	 * @param tsId 总分主键
	 * @return 总分
	 */
	@Override
	public TotalScore selectTotalScoreByTsId(Long tsId) {
		return totalScoreMapper.selectTotalScoreByTsId(tsId);
	}

	/**
	 * 查询总分列表
	 *
	 * @param totalScore 总分
	 * @return 总分
	 */
	@Override
	public List<TotalScore> selectTotalScoreList(TotalScore totalScore) {
		return totalScoreMapper.selectTotalScoreList(totalScore);
	}

	/**
	 * 新增总分
	 *
	 * @param totalScore 总分
	 * @return 结果
	 */
	@Override
	public int insertTotalScore(TotalScore totalScore) {
		return totalScoreMapper.insertTotalScore(totalScore);
	}

	/**
	 * 修改总分
	 *
	 * @param totalScore 总分
	 * @return 结果
	 */
	@Override
	public int updateTotalScore(TotalScore totalScore) {
		return totalScoreMapper.updateTotalScore(totalScore);
	}

	/**
	 * 批量删除总分
	 *
	 * @param tsIds 需要删除的总分主键
	 * @return 结果
	 */
	@Override
	public int deleteTotalScoreByTsIds(Long[] tsIds) {
		return totalScoreMapper.deleteTotalScoreByTsIds(tsIds);
	}

	/**
	 * 删除总分信息
	 *
	 * @param tsId 总分主键
	 * @return 结果
	 */
	@Override
	public int deleteTotalScoreByTsId(Long tsId) {
		return totalScoreMapper.deleteTotalScoreByTsId(tsId);
	}

	/**
	 * 总分相加
	 */
	@Override
	public int addingTotalScore(TotalScore totalScore) {
		// 如果模块成绩少于4条就终止
		List<ModuleScore> moduleScoreList = moduleScoreMapper.selectModuleScoreList(new ModuleScore(totalScore.getTsId()));
		if (moduleScoreList.size() < 4) {
			return -1;
		}

		BigDecimal score = moduleScoreList.stream()
				.map(ModuleScore::getAvsScore)
				.filter(Objects::nonNull)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		totalScore.setEpScore(score);
		return updateTotalScore(totalScore);
	}
}
