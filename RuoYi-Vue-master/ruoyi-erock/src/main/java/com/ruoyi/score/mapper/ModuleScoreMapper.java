package com.ruoyi.score.mapper;

import java.util.List;

import com.ruoyi.score.domain.ModuleAndTotal;
import com.ruoyi.score.domain.ModuleScore;

/**
 * 模块分数Mapper接口
 *
 * @author houq
 * @date 2024-04-22
 */
public interface ModuleScoreMapper {
	/**
	 * 查询模块分数
	 *
	 * @param modId 模块分数主键
	 * @return 模块分数
	 */
	public ModuleScore selectModuleScoreByModId(Long modId);

	/**
	 * 查询模块分数列表
	 *
	 * @param moduleScore 模块分数
	 * @return 模块分数集合
	 */
	public List<ModuleScore> selectModuleScoreList(ModuleScore moduleScore);

	/**
	 * 新增模块分数
	 *
	 * @param moduleScore 模块分数
	 * @return 结果
	 */
	public int insertModuleScore(ModuleScore moduleScore);

	/**
	 * 修改模块分数
	 *
	 * @param moduleScore 模块分数
	 * @return 结果
	 */
	public int updateModuleScore(ModuleScore moduleScore);

	/**
	 * 删除模块分数
	 *
	 * @param modId 模块分数主键
	 * @return 结果
	 */
	public int deleteModuleScoreByModId(Long modId);

	/**
	 * 批量删除模块分数
	 *
	 * @param modIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteModuleScoreByModIds(Long[] modIds);

	/**
	 * 查询学生是否有指定模块的成绩
	 *
	 * @param moduleAndTotal 总分和模块分数类
	 * @return 结果
	 */
	public List<ModuleScore> selectStudentByStuIdAndEnumId(ModuleAndTotal moduleAndTotal);
}
