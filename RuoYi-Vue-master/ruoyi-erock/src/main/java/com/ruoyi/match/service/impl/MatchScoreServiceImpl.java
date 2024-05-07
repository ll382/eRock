package com.ruoyi.match.service.impl;

import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.util.DateUtil;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.mapper.CPersonnelSheetMapper;
import com.ruoyi.match.service.MatchScoreService;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class MatchScoreServiceImpl implements MatchScoreService {
	@Autowired
	private CPersonnelSheetMapper cPersonnelSheetMapper;

	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	@Autowired
	private SelectUser selectUser;

	/**
	 * C1模块分数新增或修改
	 */
	@Override
	public List<ModuleScore> modifiedModuleScoreMain(List<CPersonnelSheet> cPersonnelSheetList) {
		List<ModuleScore> moduleScoreList = new ArrayList<>();
		Long semesterId = d2CertificateMapper.selectDate(DateUtil.StringConvertDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))).getSemesterId();   // 学期id
		if (cPersonnelSheetList.isEmpty()) {
			return null;
		}
		setScoreForStudent(cPersonnelSheetList).forEach(x -> {
			moduleScoreList.add(modifiedModuleScore(x.getStuId(), semesterId, x.getModuleScore()));
		});
		return moduleScoreList;
	}

	public ModuleScore modifiedModuleScore(Long stuId, Long semesterId, BigDecimal score) {
		// 插入和返回tsId
		Long tsId = selectUser.judgeInformation(new TotalScore(null, semesterId, null, stuId));
		return selectUser.judgeModuleScore(new ModuleScore(null, tsId, 6L, score));
	}

	/**
	 * 获取c1成绩
	 * 前10%的计20分
	 * 前11%-30%的计18分
	 * 前31%-50%的计16分
	 * 前51%-70%的计14分
	 * 前71%-90%的计12分
	 * 后10%的计10分
	 */
	public List<CPersonnelSheet> setScoreForStudent(List<CPersonnelSheet> cPersonnelSheetList) {
		List<CPersonnelSheet> list = cPersonnelSheetMapper.selectList();
		if (list == null || list.isEmpty()) {
			return cPersonnelSheetList;
		}

		cPersonnelSheetList.forEach(cPersonnelSheet -> {
			int listSize = list.size();
			OptionalInt studentIndexOpt = IntStream.range(0, listSize).filter(i -> cPersonnelSheet.getStuId().equals(list.get(i).getStuId())).findFirst();

			// 如果未找到学生stuId，返回null
			if (!studentIndexOpt.isPresent()) {
				cPersonnelSheet.setModuleScore(null);
			}
			cPersonnelSheet.setModuleScore(getScoreByIndex(studentIndexOpt.getAsInt(), listSize));
		});
		return cPersonnelSheetList;
	}

	private BigDecimal getScoreByIndex(int index, int listSize) {
		if (index < Math.ceil(listSize * 0.1)) {
			return BigDecimal.valueOf(20);
		} else if (index < Math.ceil(listSize * 0.3)) {
			return BigDecimal.valueOf(18);
		} else if (index < Math.ceil(listSize * 0.5)) {
			return BigDecimal.valueOf(16);
		} else if (index < Math.ceil(listSize * 0.7)) {
			return BigDecimal.valueOf(14);
		} else if (index < Math.ceil(listSize * 0.9)) {
			return BigDecimal.valueOf(12);
		} else {
			return BigDecimal.valueOf(10);
		}
	}
}
