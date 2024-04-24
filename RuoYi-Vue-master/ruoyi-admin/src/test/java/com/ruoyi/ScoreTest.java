package com.ruoyi;

import com.ruoyi.dModularity.domain.D2Certificate;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.score.domain.ModuleAndTotal;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.mapper.TotalScoreMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreTest {
	@Autowired
	private ModuleScoreMapper moduleScoreMapper;

	@Autowired
	private TotalScoreMapper totalScoreMapper;

	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	@Test
	public void totalScoreTest() {
		TotalScore totalScore = new TotalScore();
		totalScore.setSemesterId(3L);
		totalScore.setStuId(100L);

		// 判断学号为100的学生有没有第三个学期的总分记录
		List<TotalScore> totalScoreList = totalScoreMapper.selectTotalScoreList(totalScore);
		if (totalScoreList.isEmpty()) { // 如果没有则插入记录
			totalScore.setEpScore(BigDecimal.valueOf(0.0));
			totalScoreMapper.insertTotalScore(totalScore);
		}
	}

	@Test
	public void test() {
		D2Certificate d2Certificate = new D2Certificate();
		d2Certificate.setCertificateId(1L);
		d2Certificate.setAudit(0L);

		// 获取学号以及需要加的分数
		D2Certificate d2Certificate1 = d2CertificateMapper.selectD2CertificateList(d2Certificate).get(0);
		ModuleAndTotal moduleAndTotal = new ModuleAndTotal();
		moduleAndTotal.setStuId(d2Certificate1.getStuId());
		moduleAndTotal.setAvsScore(getScoreByCertificateName(d2Certificate1.getCertificateName()));
		System.out.println("d2Certificate1=" + d2Certificate1);
		System.out.println("moduleAndTotal=" + moduleAndTotal);
	}

	/**
	 * 根据证书名称获取分数
	 */
	private BigDecimal getScoreByCertificateName(String certificateName) {
		certificateName = certificateName.trim();
		HashMap<String, BigDecimal> scoreMap = new HashMap<>();
		scoreMap.put("班赛优秀运动员", BigDecimal.valueOf(3));
		scoreMap.put("校赛优秀运动员", BigDecimal.valueOf(4));
		scoreMap.put("校级以上比赛优秀运动员", BigDecimal.valueOf(5));
		scoreMap.put("一级裁判", BigDecimal.valueOf(5));
		scoreMap.put("二级裁判", BigDecimal.valueOf(4));
		scoreMap.put("三级裁判", BigDecimal.valueOf(3));
		// 返回相应的分数，如果证书名称不存在于映射中，可以默认返回零分或其他值
		return scoreMap.getOrDefault(certificateName, BigDecimal.ZERO);
	}

	@Test
	public void moduleScoreTest() {
		// 学号100的学生查询有没有29荣誉
		// 声明总分和模块分对象
		ModuleAndTotal moduleAndTotal = new ModuleAndTotal();
		moduleAndTotal.setStuId(100L);  // 学号
		moduleAndTotal.setEnumId(29L);  // 枚举id
		moduleAndTotal.setAvsScore(BigDecimal.valueOf(6.0));    // 模块成绩
		moduleAndTotal.setSemesterId(3L);
		tool2(moduleAndTotal);
	}

	public void tool2(ModuleAndTotal moduleAndTotal) {
		// 声明总分对象
		TotalScore totalScore = new TotalScore();
		totalScore.setStuId(moduleAndTotal.getStuId());
		totalScore.setSemesterId(moduleAndTotal.getSemesterId());

		// 尝试获取或创建总分对象
		TotalScore existingTotalScore = getOrCreateTotalScore(totalScore, moduleAndTotal.getAvsScore());

		// 声明模块分对象
		ModuleScore moduleScore = new ModuleScore();
		moduleScore.setEnumId(moduleAndTotal.getEnumId());
		moduleScore.setTsId(existingTotalScore.getTsId());

		// 尝试获取或创建模块分对象
		getOrCreateModuleScore(moduleScore, moduleAndTotal.getAvsScore());
	}

	// 新增和修改总分记录
	private TotalScore getOrCreateTotalScore(TotalScore totalScore, BigDecimal avsScore) {
		List<TotalScore> totalScoreList = totalScoreMapper.selectTotalScoreList(totalScore);
		if (totalScoreList.isEmpty()) {
			totalScore.setEpScore(avsScore);
			totalScoreMapper.insertTotalScore(totalScore);
			return totalScore;
		} else {
			TotalScore existingTotalScore = totalScoreList.get(0);
			existingTotalScore.setEpScore(existingTotalScore.getEpScore().add(avsScore));
			totalScoreMapper.updateTotalScore(existingTotalScore);
			return existingTotalScore;
		}
	}

	// 新增和修改模块分数记录
	private void getOrCreateModuleScore(ModuleScore moduleScore, BigDecimal avsScore) {
		List<ModuleScore> moduleScoreList = moduleScoreMapper.selectModuleScoreList(moduleScore);
		if (moduleScoreList.isEmpty()) {
			moduleScore.setAvsScore(avsScore);
			moduleScoreMapper.insertModuleScore(moduleScore);
		} else {
			ModuleScore existingModuleScore = moduleScoreList.get(0);
			existingModuleScore.setAvsScore(existingModuleScore.getAvsScore().add(avsScore));
			moduleScoreMapper.updateModuleScore(existingModuleScore);
		}
	}

}
