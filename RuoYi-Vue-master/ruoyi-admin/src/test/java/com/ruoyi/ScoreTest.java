package com.ruoyi;

import com.ruoyi.core.service.SelectUser;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.match.service.MatchScoreService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreTest {
	@Autowired
	private MatchScoreService matchScoreService;

	@Autowired
	private SelectUser selectUser;

	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	// @Test
	// public void modifiedModuleScoreMain(List<CPersonnelSheet> cPersonnelSheetList) {
	// 	Long semesterId = d2CertificateMapper.selectDate(DateUtil.StringConvertDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))).getSemesterId();   // 学期id
	// 	matchScoreService.setScoreForStudent(cPersonnelSheetList).forEach(x -> {
	// 		modifiedModuleScore(x.getStuId(), semesterId, x.getModuleScore());
	// 	});
	// }
	//
	// public ModuleScore modifiedModuleScore(Long stuId, Long semesterId, BigDecimal score) {
	// 	// 插入和返回tsId
	// 	Long tsId = selectUser.judgeInformation(new TotalScore(null, semesterId, null, stuId));
	// 	return selectUser.judgeModuleScore(new ModuleScore(null, tsId, 6L, score));
	// }
}
