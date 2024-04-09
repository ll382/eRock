package com.ruoyi.match.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.core.service.SelectUser;
import com.ruoyi.practice.mapper.AmodeClassRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.mapper.ClassRegisterMapper;
import com.ruoyi.match.domain.ClassRegister;
import com.ruoyi.match.service.IClassRegisterService;

/**
 * 课堂记录Service业务层处理
 *
 * @author houq
 * @date 2024-04-08
 */
@Service
public class ClassRegisterServiceImpl implements IClassRegisterService {
	@Autowired
	private ClassRegisterMapper classRegisterMapper;
	@Autowired
	private AmodeClassRegisterMapper amodeClassRegisterMapper;

	@Autowired
	private SelectUser selectUser;


	/**
	 * 查询课堂记录
	 *
	 * @param crId 课堂记录主键
	 * @return 课堂记录
	 */
	@Override
	public ClassRegister selectAmodeClassRegisterByCrId(Long crId) {

		System.out.println(selectUser.selectUser(crId));

		return amodeClassRegisterMapper.selectAmodeClassRegisterByCrId(crId);
	}

//   --------------------分界线-----------------------


	/**
	 * 查询课堂记录
	 *
	 * @param crId 课堂记录主键
	 * @return 课堂记录
	 */
	@Override
	public ClassRegister selectClassRegisterByCrId(Long crId) {
		return classRegisterMapper.selectClassRegisterByCrId(crId);
	}

	/**
	 * 查询课堂记录列表
	 *
	 * @param classRegister 课堂记录
	 * @return 课堂记录
	 */
	@Override
	public List<ClassRegister> selectClassRegisterList(ClassRegister classRegister) {
		return classRegisterMapper.selectClassRegisterList(classRegister);
	}

	/**
	 * 新增课堂记录
	 *
	 * @param classRegister 课堂记录
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertClassRegister(ClassRegister classRegister) {
		int rows = classRegisterMapper.insertClassRegister(classRegister);
		insertCompetitionRecord(classRegister);
		return rows;
	}

	/**
	 * 修改课堂记录
	 *
	 * @param classRegister 课堂记录
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateClassRegister(ClassRegister classRegister) {
		classRegisterMapper.deleteCompetitionRecordByCrId(classRegister.getCrId());
		insertCompetitionRecord(classRegister);
		return classRegisterMapper.updateClassRegister(classRegister);
	}

	/**
	 * 批量删除课堂记录
	 *
	 * @param crIds 需要删除的课堂记录主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteClassRegisterByCrIds(Long[] crIds) {
		classRegisterMapper.deleteCompetitionRecordByCrIds(crIds);
		return classRegisterMapper.deleteClassRegisterByCrIds(crIds);
	}

	/**
	 * 删除课堂记录信息
	 *
	 * @param crId 课堂记录主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteClassRegisterByCrId(Long crId) {
		classRegisterMapper.deleteCompetitionRecordByCrId(crId);
		return classRegisterMapper.deleteClassRegisterByCrId(crId);
	}

	/**
	 * 新增C 比赛记录信息
	 *
	 * @param classRegister 课堂记录对象
	 */
	public void insertCompetitionRecord(ClassRegister classRegister) {
		List<CompetitionRecord> competitionRecordList = classRegister.getCompetitionRecordList();
		Long crId = classRegister.getCrId();
		if (StringUtils.isNotNull(competitionRecordList)) {
			List<CompetitionRecord> list = new ArrayList<CompetitionRecord>();
			for (CompetitionRecord competitionRecord : competitionRecordList) {
				competitionRecord.setCrId(crId);
				list.add(competitionRecord);
			}
			if (list.size() > 0) {
				classRegisterMapper.batchCompetitionRecord(list);
			}
		}
	}

	/**
	 * 新增课内赛课堂记录
	 *
	 * @param map 课堂记录
	 * @return
	 */
	@Transactional
	@Override
	public int insertClassRegisterKn(HashMap<String, Object> map) {
		ClassRegister classRegister = new ClassRegister();
		classRegister.setSemesterId(1L);
		classRegister.setCrDate(parseDate((String) map.get("dateTime")));
		classRegister.setCrMain((String) map.get("name"));
		classRegister.setCrDuration(BigDecimal.valueOf(40));
		classRegister.setClassId(Long.valueOf((Integer) ((HashMap) map.get("class")).get("classId")));

		// 比赛记录
		CompetitionRecord competitionRecord = new CompetitionRecord();
		competitionRecord.setEnumId(6L);
		competitionRecord.setCcRTime(parseDate((String) map.get("dateTime")));
		competitionRecord.setCcRSpeci("课内赛");
		competitionRecord.setCcRName((String) map.get("name"));
		competitionRecord.setCcRAudit(0L);

		// ClassRegister classRegister = JSON.parseObject(JSON.toJSONString(map), ClassRegister.class);
		// CompetitionRecord competitionRecord = JSON.parseObject(JSON.toJSONString(map), CompetitionRecord.class);

		classRegister.setCompetitionRecordList(Collections.singletonList(competitionRecord));
		System.out.println(classRegister);
		return insertClassRegister(classRegister);
	}

	/**
	 * 解析时间
	 *
	 * @param dateString
	 * @return
	 */
	private static Date parseDate(String dateString) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}