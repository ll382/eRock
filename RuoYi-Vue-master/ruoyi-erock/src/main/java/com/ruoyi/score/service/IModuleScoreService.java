package com.ruoyi.score.service;

import java.util.List;
import com.ruoyi.score.domain.ModuleScore;

/**
 * 模块分数Service接口
 *
 * @author houq
 * @date 2024-04-22
 */
public interface IModuleScoreService
{
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
     * 批量删除模块分数
     *
     * @param modIds 需要删除的模块分数主键集合
     * @return 结果
     */
    public int deleteModuleScoreByModIds(Long[] modIds);

    /**
     * 删除模块分数信息
     *
     * @param modId 模块分数主键
     * @return 结果
     */
    public int deleteModuleScoreByModId(Long modId);
}
