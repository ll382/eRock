package com.ruoyi.match.service;

import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.score.domain.ModuleScore;

import java.util.List;

public interface MatchScoreService {
	public List<ModuleScore> modifiedModuleScoreMain(List<CPersonnelSheet> cPersonnelSheetList);
}
