package com.ruoyi;

import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.domain.KwUploadResource;
import com.ruoyi.match.mapper.CBallteamMapper;
import com.ruoyi.match.mapper.CPersonnelSheetMapper;
import com.ruoyi.match.mapper.CompetitionRecordMapper;
import com.ruoyi.match.service.ICBallteamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class ScoreTest {
	@Autowired
	private CBallteamMapper cBallteamMapper;

	@Autowired
	private ICBallteamService cBallteamService;

	@Autowired
	private CompetitionRecordMapper competitionRecordMapper;

	@Autowired
	private CPersonnelSheetMapper cPersonnelSheetMapper;

	@Test
	public void c2Test() {
		CPersonnelSheet abc = new CPersonnelSheet();

		KwUploadResource kwUploadResource = new KwUploadResource("友谊赛", 1L, 62L, "https://www.baidu.com/");
		CPersonnelSheet cPersonnelSheet = cPersonnelSheetMapper.judgeNull(kwUploadResource);
		if (cPersonnelSheet == null || !cPersonnelSheet.getStuId().equals(kwUploadResource.getStuId())) {
			abc = insertCbUrl(kwUploadResource);
		} else {
			cPersonnelSheet.setPsUrl(kwUploadResource.getPsUrl());
			cPersonnelSheetMapper.updateCPersonnelSheet(cPersonnelSheet);
			abc = cPersonnelSheet;
		}
		System.out.println(abc);
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
