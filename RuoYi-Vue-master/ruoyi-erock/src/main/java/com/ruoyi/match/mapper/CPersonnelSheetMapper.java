package com.ruoyi.match.mapper;

import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.domain.CProof;
import com.ruoyi.score.domain.ModuleScore;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * C 球队内人员Mapper接口
 *
 * @author heye
 * @date 2024-03-19
 */
public interface CPersonnelSheetMapper {
	/**
	 * 查询C 球队内人员
	 *
	 * @param psId C 球队内人员主键
	 * @return C 球队内人员
	 */
	public CPersonnelSheet selectCPersonnelSheetByPsId(Long psId);

	/**
	 * 查询C 球队内人员列表
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return C 球队内人员集合
	 */
	public List<CPersonnelSheet> selectCPersonnelSheetList(CPersonnelSheet cPersonnelSheet);

	/**
	 * 新增C 球队内人员
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return 结果
	 */
	public int insertCPersonnelSheet(CPersonnelSheet cPersonnelSheet);

	/**
	 * 修改C 球队内人员
	 *
	 * @param cPersonnelSheet C 球队内人员
	 * @return 结果
	 */
	public int updateCPersonnelSheet(CPersonnelSheet cPersonnelSheet);

	/**
	 * 删除C 球队内人员
	 *
	 * @param psId C 球队内人员主键
	 * @return 结果
	 */
	public int deleteCPersonnelSheetByPsId(Long psId);

	/**
	 * 批量删除C 球队内人员
	 *
	 * @param psIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteCPersonnelSheetByPsIds(Long[] psIds);

	/**
	 * 批量删除C 佐证
	 *
	 * @param psIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteCProofByPsIds(Long[] psIds);

	/**
	 * 批量新增C 佐证
	 *
	 * @param cProofList C 佐证列表
	 * @return 结果
	 */
	public int batchCProof(List<CProof> cProofList);


	/**
	 * 通过C 球队内人员主键删除C 佐证信息
	 *
	 * @param psId C 球队内人员ID
	 * @return 结果
	 */
	public int deleteCProofByPsId(Long psId);

	/**
	 * 根据球队id查询C 球队内人员列表
	 *
	 * @param balId
	 * @return
	 */
	public List<CPersonnelSheet> findPersonnelShellByBalId(Long balId);


	@Select("SELECT stu_id AS stuId, SUM(ps_num) AS psNum FROM c_personnel_sheet GROUP BY stu_id ORDER BY psNum DESC")
	public List<CPersonnelSheet> selectList();

	/**
	 * 根据枚举id累计评分
	 */
	public List<CPersonnelSheet> cumulativeScoreByEnumId(Long enumId);

	/**
	 * 判断有没有模块成绩
	 */
	public List<ModuleScore> selectMsIsExits(HashMap<String,String> map);
}
