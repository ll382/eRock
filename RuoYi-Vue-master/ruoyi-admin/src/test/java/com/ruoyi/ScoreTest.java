//package com.ruoyi;
//
//import com.ruoyi.dModularity.domain.D2Certificate;
//import com.ruoyi.dModularity.mapper.D2CertificateMapper;
//import com.ruoyi.score.domain.ModuleAndTotal;
//import com.ruoyi.score.domain.ModuleScore;
//import com.ruoyi.score.domain.TotalScore;
//import com.ruoyi.score.mapper.ModuleScoreMapper;
//import com.ruoyi.score.mapper.TotalScoreMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ScoreTest {
//	@Autowired
//	private ModuleScoreMapper moduleScoreMapper;
//
//	@Autowired
//	private TotalScoreMapper totalScoreMapper;
//
//	@Autowired
//	private D2CertificateMapper d2CertificateMapper;
//
//	@Test
//	public void totalScoreTest() {
//		TotalScore totalScore = new TotalScore();
//		totalScore.setSemesterId(3L);
//		totalScore.setStuId(100L);
//
//		// �ж�ѧ��Ϊ100��ѧ����û�е�����ѧ�ڵ��ּܷ�¼
//		List<TotalScore> totalScoreList = totalScoreMapper.selectTotalScoreList(totalScore);
//		if (totalScoreList.isEmpty()) { // ���û��������¼
//			totalScore.setEpScore(BigDecimal.valueOf(0.0));
//			totalScoreMapper.insertTotalScore(totalScore);
//		}
//	}
//
//	@Test
//	public void test() {
//		D2Certificate d2Certificate = new D2Certificate();
//		d2Certificate.setCertificateId(1L);
//		d2Certificate.setAudit(0L);
//
//		// ��ȡѧ���Լ���Ҫ�ӵķ���
//		D2Certificate d2Certificate1 = d2CertificateMapper.selectD2CertificateList(d2Certificate).get(0);
//		ModuleAndTotal moduleAndTotal = new ModuleAndTotal();
//		moduleAndTotal.setStuId(d2Certificate1.getStuId());
//		moduleAndTotal.setAvsScore(getScoreByCertificateName(d2Certificate1.getCertificateName()));
//		System.out.println("d2Certificate1=" + d2Certificate1);
//		System.out.println("moduleAndTotal=" + moduleAndTotal);
//	}
//
//	/**
//	 * ����֤�����ƻ�ȡ����
//	 */
//	private BigDecimal getScoreByCertificateName(String certificateName) {
//		certificateName = certificateName.trim();
//		HashMap<String, BigDecimal> scoreMap = new HashMap<>();
//		scoreMap.put("���������˶�Ա", BigDecimal.valueOf(3));
//		scoreMap.put("У�������˶�Ա", BigDecimal.valueOf(4));
//		scoreMap.put("У�����ϱ��������˶�Ա", BigDecimal.valueOf(5));
//		scoreMap.put("һ������", BigDecimal.valueOf(5));
//		scoreMap.put("��������", BigDecimal.valueOf(4));
//		scoreMap.put("��������", BigDecimal.valueOf(3));
//		// ������Ӧ�ķ��������֤�����Ʋ�������ӳ���У�����Ĭ�Ϸ�����ֻ�����ֵ
//		return scoreMap.getOrDefault(certificateName, BigDecimal.ZERO);
//	}
//
//	@Test
//	public void moduleScoreTest() {
//		// ѧ��100��ѧ����ѯ��û��29����
//		// �����ֺܷ�ģ��ֶ���
//		ModuleAndTotal moduleAndTotal = new ModuleAndTotal();
//		moduleAndTotal.setStuId(100L);  // ѧ��
//		moduleAndTotal.setEnumId(29L);  // ö��id
//		moduleAndTotal.setAvsScore(BigDecimal.valueOf(6.0));    // ģ��ɼ�
//		moduleAndTotal.setSemesterId(3L);
//		tool2(moduleAndTotal);
//	}
//
//	public void tool2(ModuleAndTotal moduleAndTotal) {
//		// �����ֶܷ���
//		TotalScore totalScore = new TotalScore();
//		totalScore.setStuId(moduleAndTotal.getStuId());
//		totalScore.setSemesterId(moduleAndTotal.getSemesterId());
//
//		// ���Ի�ȡ�򴴽��ֶܷ���
//		TotalScore existingTotalScore = getOrCreateTotalScore(totalScore, moduleAndTotal.getAvsScore());
//
//		// ����ģ��ֶ���
//		ModuleScore moduleScore = new ModuleScore();
//		moduleScore.setEnumId(moduleAndTotal.getEnumId());
//		moduleScore.setTsId(existingTotalScore.getTsId());
//
//		// ���Ի�ȡ�򴴽�ģ��ֶ���
//		getOrCreateModuleScore(moduleScore, moduleAndTotal.getAvsScore());
//	}
//
//	// �������޸��ּܷ�¼
//	private TotalScore getOrCreateTotalScore(TotalScore totalScore, BigDecimal avsScore) {
//		List<TotalScore> totalScoreList = totalScoreMapper.selectTotalScoreList(totalScore);
//		if (totalScoreList.isEmpty()) {
//			totalScore.setEpScore(avsScore);
//			totalScoreMapper.insertTotalScore(totalScore);
//			return totalScore;
//		} else {
//			TotalScore existingTotalScore = totalScoreList.get(0);
//			existingTotalScore.setEpScore(existingTotalScore.getEpScore().add(avsScore));
//			totalScoreMapper.updateTotalScore(existingTotalScore);
//			return existingTotalScore;
//		}
//	}
//
//	// �������޸�ģ�������¼
//	private void getOrCreateModuleScore(ModuleScore moduleScore, BigDecimal avsScore) {
//		List<ModuleScore> moduleScoreList = moduleScoreMapper.selectModuleScoreList(moduleScore);
//		if (moduleScoreList.isEmpty()) {
//			moduleScore.setAvsScore(avsScore);
//			moduleScoreMapper.insertModuleScore(moduleScore);
//		} else {
//			ModuleScore existingModuleScore = moduleScoreList.get(0);
//			existingModuleScore.setAvsScore(existingModuleScore.getAvsScore().add(avsScore));
//			moduleScoreMapper.updateModuleScore(existingModuleScore);
//		}
//	}
//
//}
