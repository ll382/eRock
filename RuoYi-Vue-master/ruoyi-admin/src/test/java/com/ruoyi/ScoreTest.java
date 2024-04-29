package com.ruoyi;

import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.service.ITotalScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreTest {
	@Autowired
	private ModuleScoreMapper moduleScoreMapper;

	@Autowired
	private ITotalScoreService totalScoreService;

	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	@Test
	public void totalScoreTest() {
		TotalScore totalScore = new TotalScore();
		totalScore.setTsId(6L);
		System.out.println(totalScoreService.addingTotalScore(totalScore));
	}
}
