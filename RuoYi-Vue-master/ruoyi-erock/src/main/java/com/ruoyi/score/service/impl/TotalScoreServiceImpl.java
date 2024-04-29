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
 * �ܷ�Serviceҵ��㴦��
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
	 * ��ѯ�ܷ�
	 *
	 * @param tsId �ܷ�����
	 * @return �ܷ�
	 */
	@Override
	public TotalScore selectTotalScoreByTsId(Long tsId) {
		return totalScoreMapper.selectTotalScoreByTsId(tsId);
	}

	/**
	 * ��ѯ�ܷ��б�
	 *
	 * @param totalScore �ܷ�
	 * @return �ܷ�
	 */
	@Override
	public List<TotalScore> selectTotalScoreList(TotalScore totalScore) {
		return totalScoreMapper.selectTotalScoreList(totalScore);
	}

	/**
	 * �����ܷ�
	 *
	 * @param totalScore �ܷ�
	 * @return ���
	 */
	@Override
	public int insertTotalScore(TotalScore totalScore) {
		return totalScoreMapper.insertTotalScore(totalScore);
	}

	/**
	 * �޸��ܷ�
	 *
	 * @param totalScore �ܷ�
	 * @return ���
	 */
	@Override
	public int updateTotalScore(TotalScore totalScore) {
		return totalScoreMapper.updateTotalScore(totalScore);
	}

	/**
	 * ����ɾ���ܷ�
	 *
	 * @param tsIds ��Ҫɾ�����ܷ�����
	 * @return ���
	 */
	@Override
	public int deleteTotalScoreByTsIds(Long[] tsIds) {
		return totalScoreMapper.deleteTotalScoreByTsIds(tsIds);
	}

	/**
	 * ɾ���ܷ���Ϣ
	 *
	 * @param tsId �ܷ�����
	 * @return ���
	 */
	@Override
	public int deleteTotalScoreByTsId(Long tsId) {
		return totalScoreMapper.deleteTotalScoreByTsId(tsId);
	}

	/**
	 * �ܷ����
	 */
	@Override
	public int addingTotalScore(TotalScore totalScore) {
		// ���ģ��ɼ�����4������ֹ
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
