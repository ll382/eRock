package com.ruoyi.dModularity.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.util.DateUtil;
import com.ruoyi.dModularity.domain.D2Certificate;
import com.ruoyi.dModularity.domain.D2CertificateAuditByStuId;
import com.ruoyi.dModularity.domain.D2Resource;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.dModularity.service.ID2CertificateService;
import com.ruoyi.score.domain.ModuleAndTotal;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.mapper.TotalScoreMapper;
import com.ruoyi.score.service.ITotalScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * D2 证书表Service业务层处理
 *
 * @author houq
 * @date 2024-04-16
 */
@Service
public class D2CertificateServiceImpl implements ID2CertificateService {
	@Autowired
	private D2CertificateMapper d2CertificateMapper;

	@Autowired
	private SelectUser selectUser;

	@Autowired
	private ModuleScoreMapper moduleScoreMapper;

	@Autowired
	private TotalScoreMapper totalScoreMapper;

	@Autowired
	private ITotalScoreService totalScoreService;

	/**
	 * 查询D2 证书表
	 *
	 * @param certificateId D2 证书表主键
	 * @return D2 证书表
	 */
	@Override
	public D2Certificate selectD2CertificateByCertificateId(Long certificateId) {
		return d2CertificateMapper.selectD2CertificateByCertificateId(certificateId);
	}

	/**
	 * 查询D2 证书表列表
	 *
	 * @param d2Certificate D2 证书表
	 * @return D2 证书表
	 */
	@Override
	public List<D2Certificate> selectD2CertificateList(D2Certificate d2Certificate) {
		return selectUser.selectStudent(d2CertificateMapper.selectD2CertificateList(d2Certificate));
	}

	/**
	 * 新增D2 证书表
	 *
	 * @param d2Certificate D2 证书表
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertD2Certificate(D2Certificate d2Certificate) {
		int rows = d2CertificateMapper.insertD2Certificate(d2Certificate);
		insertD2Resource(d2Certificate);
		return rows;
	}

	/**
	 * 修改D2 证书表
	 *
	 * @param d2Certificate D2 证书表
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateD2Certificate(D2Certificate d2Certificate) {
		d2CertificateMapper.deleteD2ResourceByCertificateId(d2Certificate.getCertificateId());
		insertD2Resource(d2Certificate);
		int rows = d2CertificateMapper.updateD2Certificate(d2Certificate);

		if (d2Certificate.getAudit() == 1) {
			D2Certificate dc = d2CertificateMapper.selectD2CertificateList(d2Certificate).get(0);
			ModuleAndTotal moduleAndTotal = new ModuleAndTotal();
			moduleAndTotal.setStuId(dc.getStuId());  // 学号
			moduleAndTotal.setEnumId(dc.getEnumId());  // 枚举id
			moduleAndTotal.setAvsScore(getScoreByCertificateName(dc.getCertificateName()));    // 模块成绩
			moduleAndTotal.setSemesterId(d2CertificateMapper.selectDate(DateUtil.StringConvertDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))).getSemesterId());   // 学期id
			// 修改模块分数
			// updateModuleScore(moduleAndTotal);
		}

		return rows;
	}

	/**
	 * 批量删除D2 证书表
	 *
	 * @param certificateIds 需要删除的D2 证书表主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteD2CertificateByCertificateIds(Long[] certificateIds) {
		d2CertificateMapper.deleteD2ResourceByCertificateIds(certificateIds);
		return d2CertificateMapper.deleteD2CertificateByCertificateIds(certificateIds);
	}

	/**
	 * 删除D2 证书表信息
	 *
	 * @param certificateId D2 证书表主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteD2CertificateByCertificateId(Long certificateId) {
		d2CertificateMapper.deleteD2ResourceByCertificateId(certificateId);
		return d2CertificateMapper.deleteD2CertificateByCertificateId(certificateId);
	}

	/**
	 * 新增D2 资源信息
	 *
	 * @param d2Certificate D2 证书表对象
	 */
	public void insertD2Resource(D2Certificate d2Certificate) {
		List<D2Resource> d2ResourceList = d2Certificate.getD2ResourceList();
		Long certificateId = d2Certificate.getCertificateId();
		if (StringUtils.isNotNull(d2ResourceList)) {
			List<D2Resource> list = new ArrayList<D2Resource>();
			for (D2Resource d2Resource : d2ResourceList) {
				d2Resource.setCertificateId(certificateId);
				list.add(d2Resource);
			}
			if (list.size() > 0) {
				d2CertificateMapper.batchD2Resource(list);
			}
		}
	}

	/**
	 * D2学生名单列表
	 *
	 * @return
	 */
	@Override
	public Map<String, List<D2CertificateAuditByStuId>> selectD2CertificateAudit(Integer enumId) {
		List<D2CertificateAuditByStuId> auditList = selectUser.selectStudent(d2CertificateMapper.selectD2CertificateAudit(enumId));

		return auditList.stream().collect(Collectors.groupingBy(audit -> audit.getAudit() == 1 ? "yes" : "no"));
	}

	/**
	 * D2查询学生上传资料信息
	 *
	 * @param map
	 * @return
	 */
	@Override
	public List<HashMap<String, Object>> selectD2CertificateAuditByStuId(HashMap<String, Object> map) {
		return d2CertificateMapper.selectD2CertificateAuditByStuId(map);
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

	/**
	 * 修改模块分数
	 */
	public void updateModuleScore(ModuleAndTotal moduleAndTotal) {
		// 声明总分对象
		TotalScore totalScore = new TotalScore();
		totalScore.setStuId(moduleAndTotal.getStuId()); // 学号
		totalScore.setSemesterId(moduleAndTotal.getSemesterId()); // 学期Id

		// 尝试获取或创建总分对象
		// TotalScore existingTotalScore = getOrCreateTotalScore(totalScore, moduleAndTotal.getAvsScore());

		// 声明模块分对象
		ModuleScore moduleScore = new ModuleScore();
		moduleScore.setEnumId(moduleAndTotal.getEnumId());
		// 新建或更新总分返回tsId
		moduleScore.setTsId(selectUser.judgeInformation(totalScore));

		// 尝试获取或创建模块分对象
		// getOrCreateModuleScore(moduleScore, moduleAndTotal.getAvsScore());
	}
	//
	// /**
	//  * 新增和修改总分记录
	//  */
	// private TotalScore getOrCreateTotalScore(TotalScore totalScore, BigDecimal avsScore) {
	// 	List<TotalScore> totalScoreList = totalScoreMapper.selectTotalScoreList(totalScore);
	// 	if (totalScoreList.isEmpty()) {
	// 		totalScore.setEpScore(avsScore);
	// 		totalScoreMapper.insertTotalScore(totalScore);
	// 		return totalScore;
	// 	} else {
	// 		TotalScore existingTotalScore = totalScoreList.get(0);
	// 		totalScoreService.addingTotalScore(existingTotalScore.getTsId(), avsScore);
	// 		return existingTotalScore;
	// 	}
	// }
	//
	// /**
	//  * 新增和修改模块分数记录
	//  */
	// private void getOrCreateModuleScore(ModuleScore moduleScore, BigDecimal avsScore) {
	// 	List<ModuleScore> moduleScoreList = moduleScoreMapper.selectModuleScoreList(moduleScore);
	// 	if (moduleScoreList.isEmpty()) {
	// 		moduleScore.setAvsScore(avsScore);
	// 		moduleScoreMapper.insertModuleScore(moduleScore);
	// 	} else {
	// 		ModuleScore existingModuleScore = moduleScoreList.get(0);
	// 		existingModuleScore.setAvsScore(existingModuleScore.getAvsScore().add(avsScore));
	// 		moduleScoreMapper.updateModuleScore(existingModuleScore);
	// 	}
	// }
}
