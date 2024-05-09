// package com.ruoyi;
//
// import com.ruoyi.bModularity.mapper.B1FranchiseClubMapper;
// import com.ruoyi.core.domain.Semester;
// import com.ruoyi.core.service.SelectUser;
// import com.ruoyi.dModularity.mapper.D2CertificateMapper;
// import com.ruoyi.score.domain.ModuleScore;
// import com.ruoyi.score.domain.TotalScore;
// import com.ruoyi.score.mapper.ModuleScoreMapper;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import java.math.BigDecimal;
// import java.util.Date;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class ScoreTest {
// 	@Autowired
// 	private B1FranchiseClubMapper b1FranchiseClubMapper;
//
// 	@Autowired
// 	private ModuleScoreMapper moduleScoreMapper;
//
// 	@Autowired
// 	private SelectUser selectUser;
// 	@Autowired
// 	private D2CertificateMapper d2CertificateMapper;
//
// 	@Test
// 	public void b1ScoreTest() {
// 		Long id = 1L;
// 		Semester semester = b1FranchiseClubMapper.selectSemester();
// 		long startDate = semester.getStartDate().getTime();
// 		long finishDate = semester.getFinishDate().getTime();
// 		int requiresTrainNum = (int) ((finishDate - startDate) / 1000 / 60 / 60 / 24 / 7 * 2);
// 		int stuTrainNum = b1FranchiseClubMapper.countTrainScore(semester.getStartDate(), semester.getFinishDate(), id);
//
// 		TotalScore totalScore = new TotalScore();
// 		totalScore.setStuId(id);
// 		totalScore.setSemesterId(d2CertificateMapper.selectDate(new Date()).getSemesterId());
// 		Long tsId = selectUser.judgeInformation(totalScore);
//
// 		ModuleScore moduleScore = new ModuleScore();
// 		moduleScore.setTsId(tsId);
// 		moduleScore.setEnumId(4L);
// 		moduleScore.setAvsScore(scoreCount(requiresTrainNum, stuTrainNum));
//
// 		System.out.println(selectUser.judgeModuleScore(moduleScore));
// 	}
//
// 	public BigDecimal scoreCount(int requiresTrainNum, int stuTrainNum) {
// 		BigDecimal defaultScore = new BigDecimal("20");
// 		int difference = Math.max(requiresTrainNum - stuTrainNum, 0);
// 		BigDecimal deduction = new BigDecimal(String.valueOf(difference));
// 		BigDecimal finalScore = defaultScore.subtract(deduction);
// 		return finalScore.max(BigDecimal.ZERO);
// 	}
// }
