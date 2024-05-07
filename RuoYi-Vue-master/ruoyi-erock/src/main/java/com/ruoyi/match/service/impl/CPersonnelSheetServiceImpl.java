package com.ruoyi.match.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.domain.CProof;
import com.ruoyi.match.mapper.CPersonnelSheetMapper;
import com.ruoyi.match.service.ICPersonnelSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * C 球队内人员Service业务层处理
 *
 * @author heye
 * @date 2024-03-19
 */
@Service
public class CPersonnelSheetServiceImpl implements ICPersonnelSheetService {
	@Autowired
	private CPersonnelSheetMapper cPersonnelSheetMapper;

	/**
	 * 查询C 球队内人员
	 *
	 * @param psId C 球队内人员主键
	 * @return C 球队内人员
	 */
	@Override
	public CPersonnelSheet selectCPersonnelSheetByPsId(Long psId) {
		return cPersonnelSheetMapper.selectCPersonnelSheetByPsId(psId);
	}

	/**
	 * 查询C 球队内人员列表
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return C 球队内人员
	 */
	@Override
	public List<CPersonnelSheet> selectCPersonnelSheetList(CPersonnelSheet cPersonnelSheet) {
		return cPersonnelSheetMapper.selectCPersonnelSheetList(cPersonnelSheet);
	}

	/**
	 * 新增C 球队内人员
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return 结果
	 */
	@Transactional
	@Override
	public int insertCPersonnelSheet(CPersonnelSheet cPersonnelSheet) {
		int rows = cPersonnelSheetMapper.insertCPersonnelSheet(cPersonnelSheet);
		insertCProof(cPersonnelSheet);
		return rows;
	}

	/**
	 * 修改C 球队内人员
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return 结果
	 */
	@Transactional
	@Override
	public int updateCPersonnelSheet(CPersonnelSheet cPersonnelSheet) {
		cPersonnelSheetMapper.deleteCProofByPsId(cPersonnelSheet.getPsId());
		insertCProof(cPersonnelSheet);
		return cPersonnelSheetMapper.updateCPersonnelSheet(cPersonnelSheet);
	}

	/**
	 * 批量删除C 球队内人员
	 *
	 * @param psIds 需要删除的C 球队内人员主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCPersonnelSheetByPsIds(Long[] psIds) {
		cPersonnelSheetMapper.deleteCProofByPsIds(psIds);
		return cPersonnelSheetMapper.deleteCPersonnelSheetByPsIds(psIds);
	}

	/**
	 * 删除C 球队内人员信息
	 *
	 * @param psId C 球队内人员主键
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCPersonnelSheetByPsId(Long psId) {
		cPersonnelSheetMapper.deleteCProofByPsId(psId);
		return cPersonnelSheetMapper.deleteCPersonnelSheetByPsId(psId);
	}

	/**
	 * 新增C 佐证信息
	 *
	 * @param cPersonnelSheet C 球队内人员对象
	 */
	public void insertCProof(CPersonnelSheet cPersonnelSheet) {
		List<CProof> cProofList = cPersonnelSheet.getCProofList();
		Long psId = cPersonnelSheet.getPsId();
		if (StringUtils.isNotNull(cProofList)) {
			List<CProof> list = new ArrayList<CProof>();
			for (CProof cProof : cProofList) {
				cProof.setPsId(psId);
				list.add(cProof);
			}
			if (list.size() > 0) {
				cPersonnelSheetMapper.batchCProof(list);
			}
		}
	}


	/**
	 * 根据球队id查询C 球队内人员列表
	 *
	 * @param balId
	 * @return
	 */
	@Override
	public List<CPersonnelSheet> findPersonnelShellByBalId(Long balId) {
		return cPersonnelSheetMapper.findPersonnelShellByBalId(balId);
	}
}
