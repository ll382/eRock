package com.ruoyi.match.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.util.DateUtil;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.mapper.CPersonnelSheetMapper;
import com.ruoyi.match.mapper.CompetitionRecordMapper;
import com.ruoyi.match.service.ICompetitionRecordService;
import com.ruoyi.match.service.MatchScoreService;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * C 比赛记录Service业务层处理
 *
 * @author houq
 * @date 2024-04-08
 */
@Service
public class CompetitionRecordServiceImpl implements ICompetitionRecordService {
	@Autowired
	private CompetitionRecordMapper competitionRecordMapper;

	@Autowired
	private CPersonnelSheetMapper cPersonnelSheetMapper;

	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	@Autowired
	private SelectUser selectUser;

	@Autowired
	private MatchScoreService matchScoreService;

	/**
	 * 查询C 比赛记录
	 *
	 * @param ccRId C 比赛记录主键
	 * @return C 比赛记录
	 */
	@Override
	public CompetitionRecord selectCompetitionRecordByCcRId(Long ccRId) {
		return competitionRecordMapper.selectCompetitionRecordByCcRId(ccRId);
	}

	/**
	 * 查询C 比赛记录列表
	 *
	 * @param competitionRecord C 比赛记录
	 * @return C 比赛记录
	 */
	@Override
	public List<CompetitionRecord> selectCompetitionRecordList(CompetitionRecord competitionRecord) {
		return competitionRecordMapper.selectCompetitionRecordList(competitionRecord);
	}

	/**
	 * 新增C 比赛记录
	 *
	 * @param competitionRecord C 比赛记录
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertCompetitionRecord(CompetitionRecord competitionRecord) {
		int rows = competitionRecordMapper.insertCompetitionRecord(competitionRecord);
		insertCBallteam(competitionRecord);
		return rows;
	}

	/**
	 * 修改C 比赛记录
	 *
	 * @param competitionRecord C 比赛记录
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateCompetitionRecord(CompetitionRecord competitionRecord) {
		competitionRecordMapper.deleteCBallteamByCcRId(competitionRecord.getCcRId());
		insertCBallteam(competitionRecord);
		return competitionRecordMapper.updateCompetitionRecord(competitionRecord);
	}

	/**
	 * 批量删除C 比赛记录
	 *
	 * @param ccRIds 需要删除的C 比赛记录主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCompetitionRecordByCcRIds(Long[] ccRIds) {
		competitionRecordMapper.deleteCBallteamByCcRIds(ccRIds);
		return competitionRecordMapper.deleteCompetitionRecordByCcRIds(ccRIds);
	}

	/**
	 * 删除C 比赛记录信息
	 *
	 * @param ccRId C 比赛记录主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCompetitionRecordByCcRId(Long ccRId) {
		competitionRecordMapper.deleteCBallteamByCcRId(ccRId);
		return competitionRecordMapper.deleteCompetitionRecordByCcRId(ccRId);
	}

	/**
	 * 新增球队参赛信息
	 *
	 * @param competitionRecord C 比赛记录对象
	 */
	public void insertCBallteam(CompetitionRecord competitionRecord) {
		List<CBallteam> cBallteamList = competitionRecord.getCBallteamList();
		Long ccRId = competitionRecord.getCcRId();
		if (StringUtils.isNotNull(cBallteamList)) {
			List<CBallteam> list = new ArrayList<CBallteam>();
			for (CBallteam cBallteam : cBallteamList) {
				cBallteam.setCcRId(ccRId);
				list.add(cBallteam);
			}
			if (list.size() > 0) {
				competitionRecordMapper.batchCBallteam(list);
			}
		}
	}

	/**
	 * 获取比赛记录记录
	 *
	 * @param speci
	 * @return
	 */
	@Override
	public List<HashMap<String, String>> selectGameRecord(String speci) {
		return competitionRecordMapper.selectGameRecord(speci);
	}


	/**
	 * 获取课外赛学生信息
	 *
	 * @return
	 */
	@Override
	public List<HashMap<String, String>> findCBallteamKw() {
		return competitionRecordMapper.findCBallteamKw();
	}

	/**
	 * 获取课外赛信息
	 *
	 * @return
	 */
	@Override
	public Map<String, List<HashMap<String, Object>>> findRecordKwByStuId(Long stuId) {
		List<HashMap<String, Object>> maps = competitionRecordMapper.findRecordKwByStuId(stuId);
		return maps.stream()
				.collect(Collectors.groupingBy(map -> (Integer) map.get("ps_audit") == 2 ? "yes" : "no"));
	}

	/**
	 * 根据学号和比赛id获取学生信息
	 *
	 * @return
	 */
	@Override
	public HashMap<String, Object> findRecordKwByStuIdAndCcrId(HashMap<String, String> map) {
		HashMap<String, Object> stuIdAndCcrIdMap = competitionRecordMapper.findRecordKwByStuIdAndCcrId(map);
		if (stuIdAndCcrIdMap == null) {
			return null;
		}

		String[] studentKeys = {"stu_id", "stu_name", "job_name", "ps_num"};
		String[] studentInfoKeys = {"id", "name", "role", "score"};

		HashMap<String, Object> stuInfo = new HashMap<>();
		for (int i = 0; i < studentKeys.length; i++) {
			stuInfo.put(studentInfoKeys[i], stuIdAndCcrIdMap.get(studentKeys[i]));
			stuIdAndCcrIdMap.remove(studentKeys[i]);
		}
		stuIdAndCcrIdMap.put("teammates", stuInfo);

		String url = (String) stuIdAndCcrIdMap.get("ps_urls");
		if (url != null) {
			List<HashMap<String, String>> urlList = Arrays.stream(url.split("; "))
					.map(u -> {
						HashMap<String, String> urlMap = new HashMap<>();
						urlMap.put("url", u);
						return urlMap;
					})
					.collect(Collectors.toList());
			stuIdAndCcrIdMap.put("ps_urls", urlList);
		}
		return stuIdAndCcrIdMap;
	}

	/**
	 * 审核学生上传的资料
	 *
	 * @param map
	 * @return
	 */
	@Override
	public Integer updateAudit(HashMap<String, String> map) {
		// 修改人员表psNum分数(单人)
		if ("2".equals(map.get("psAudti"))) {
			List<CPersonnelSheet> cPersonnelSheetList = competitionRecordMapper.selectScoreByCcrId(map);
			cPersonnelSheetList.forEach(cPersonnelSheet -> {
				cPersonnelSheetMapper.updateCPersonnelSheet(cPersonnelSheet);
			});
		}
		// 学期id
		Long semesterId = d2CertificateMapper.selectDate(DateUtil.StringConvertDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))).getSemesterId();
		// map.put("semesterId", String.valueOf(semesterId));
		// map.put("enumId", "7");
		// if (cPersonnelSheetMapper.selectMsIsExits(map).isEmpty()) {
		List<CPersonnelSheet> cPersonnelSheetList = cPersonnelSheetMapper.cumulativeScoreByEnumId(7L);
		// 修改或创建指定stuId的期末和模块成绩
		cPersonnelSheetList.stream()
				.filter(cPersonnelSheet -> cPersonnelSheet.getStuId().equals(Long.valueOf(map.get("stuId")))) // 过滤出stuId为13的学生
				.forEach(cPersonnelSheet -> {
					Long tsId = selectUser.judgeInformation(new TotalScore(cPersonnelSheet.getStuId(), semesterId));
					selectUser.judgeModuleScore(new ModuleScore(null, tsId, 7L, cPersonnelSheet.getModuleScore()));
				});
		return competitionRecordMapper.updateAudit(map);
		// }
		// List<CPersonnelSheet> cPersonnelSheetList = cPersonnelSheetMapper.cumulativeScoreByEnumId(7L);
		// cPersonnelSheetList.forEach(cPersonnelSheet -> {
		// 	Long tsId = selectUser.judgeInformation(new TotalScore(cPersonnelSheet.getStuId(), semesterId));
		// 	selectUser.judgeModuleScore(new ModuleScore(null, tsId, 7L, cPersonnelSheet.getModuleScore()));
		// });
		// return competitionRecordMapper.updateAudit(map);
	}
}
