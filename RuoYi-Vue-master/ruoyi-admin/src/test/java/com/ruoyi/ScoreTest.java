package com.ruoyi;

import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.mapper.CPersonnelSheetMapper;
import com.ruoyi.match.mapper.CompetitionRecordMapper;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreTest {
	@Autowired
	private ModuleScoreMapper moduleScoreMapper;

	@Autowired
	private ITotalScoreService totalScoreService;

	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	@Autowired
	private CompetitionRecordMapper competitionRecordMapper;

	@Autowired
	private CPersonnelSheetMapper cPersonnelSheetMapper;

	@Test
	public void totalScoreTest() {
		TotalScore totalScore = new TotalScore();
		totalScore.setTsId(6L);
		System.out.println(totalScoreService.addingTotalScore(totalScore));
	}

	@Test
	public void c2Test() {
		// HashMap<String, String> map = new HashMap<>();
		// map.put("ccrId", "4");
		// map.put("stuId", "15");
		// HashMap<String, Object> map1 = competitionRecordMapper.selectSpeci(map);
		// System.out.println(getScore((String) map1.get("speci"), (Integer) map1.get("jobId")));
		// System.out.println(getScore("����", 1));
	}

	public BigDecimal getScore(String matchType, int position) {
		Map<String, List<Map<Integer, BigDecimal>>> jsonData = initializeData();

		List<Map<Integer, BigDecimal>> matchData = jsonData.get(matchType);

		if (matchData != null && !matchData.isEmpty()) {
			Map<Integer, BigDecimal> positionScores = matchData.get(0);
			BigDecimal score = positionScores.get(position);
			if (score != null) {
				return score;
			}
		}

		return BigDecimal.ZERO;
	}

	public Map<String, List<Map<Integer, BigDecimal>>> initializeData() {
		// ����HashMap��������ʾJSON���ݽṹ
		Map<String, List<Map<Integer, BigDecimal>>> jsonData = new HashMap<>();

		// ������
		List<Map<Integer, BigDecimal>> friendshipMatches = new ArrayList<>();
		Map<Integer, BigDecimal> friendshipMatch = new HashMap<>();
		friendshipMatch.put(1, BigDecimal.valueOf(1));  // ������Ա
		friendshipMatch.put(2, BigDecimal.valueOf(1));  // ������Ա
		friendshipMatch.put(3, BigDecimal.valueOf(1));  // ������Ա
		friendshipMatches.add(friendshipMatch);
		jsonData.put("������", friendshipMatches);

		// ����
		List<Map<Integer, BigDecimal>> classMatches = new ArrayList<>();
		Map<Integer, BigDecimal> classMatch = new HashMap<>();
		classMatch.put(1, BigDecimal.valueOf(4));   // ������Ա1-3��
		classMatch.put(2, BigDecimal.valueOf(2));   // ������Ա7���Ժ� ������Ա
		classMatch.put(3, BigDecimal.valueOf(1));   // ������Ա
		classMatch.put(4, BigDecimal.valueOf(3));   // ������Ա4-6��
		classMatches.add(classMatch);
		jsonData.put("����", classMatches);

		// У��
		List<Map<Integer, BigDecimal>> schoolMatches = new ArrayList<>();
		Map<Integer, BigDecimal> schoolMatch = new HashMap<>();
		schoolMatch.put(1, BigDecimal.valueOf(5));  // ������Ա1-3��
		schoolMatch.put(2, BigDecimal.valueOf(3));  // ������Ա7���Ժ� ������Ա
		schoolMatch.put(3, BigDecimal.valueOf(2));  // ������Ա
		schoolMatch.put(4, BigDecimal.valueOf(4));  // ������Ա4-6��
		schoolMatches.add(schoolMatch);
		jsonData.put("У��", schoolMatches);

		// У����
		List<Map<Integer, BigDecimal>> interSchoolMatches = new ArrayList<>();
		Map<Integer, BigDecimal> interSchoolMatch = new HashMap<>();
		interSchoolMatch.put(1, BigDecimal.valueOf(6));  // ������Ա1-3��
		interSchoolMatch.put(2, BigDecimal.valueOf(4));  // ������Ա7���Ժ� ������Ա
		interSchoolMatch.put(3, BigDecimal.valueOf(3));  // ������Ա
		interSchoolMatch.put(4, BigDecimal.valueOf(5));  // ������Ա4-6��
		interSchoolMatches.add(interSchoolMatch);
		jsonData.put("У����", interSchoolMatches);

		return jsonData;
	}

	@Test
	public void mainTest1() {
		HashMap<String, String> map = new HashMap<>();
		map.put("ccrId", "4");
		map.put("stuId", "15");
		System.out.println(competitionRecordMapper.updateAudit(map));
	}

	@Test
	public void mainTest2() {
		HashMap<String, String> map = new HashMap<>();
		map.put("ccrId", "7");
		map.put("stuId", "15");
		List<CPersonnelSheet> cPersonnelSheetList = competitionRecordMapper.selectScoreByCcrId(map);
		cPersonnelSheetList.forEach(cPersonnelSheet -> {
			cPersonnelSheetMapper.updateCPersonnelSheet(cPersonnelSheet);
		});
	}

	@Test
	public void mainTest3() {
		List<CPersonnelSheet> list = cPersonnelSheetMapper.cumulativeScoreByEnumId(6L);
		System.out.println(list.size());
		list.forEach(System.out::println);
	}
}
