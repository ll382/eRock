package com.ruoyi.score.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.service.IModuleScoreService;

/**
 * 模块分数Service业务层处理
 * 
 * @author houq
 * @date 2024-04-22
 */
@Service
public class ModuleScoreServiceImpl implements IModuleScoreService 
{
    @Autowired
    private ModuleScoreMapper moduleScoreMapper;

    /**
     * 查询模块分数
     * 
     * @param modId 模块分数主键
     * @return 模块分数
     */
    @Override
    public ModuleScore selectModuleScoreByModId(Long modId)
    {
        return moduleScoreMapper.selectModuleScoreByModId(modId);
    }

    /**
     * 查询模块分数列表
     * 
     * @param moduleScore 模块分数
     * @return 模块分数
     */
    @Override
    public List<ModuleScore> selectModuleScoreList(ModuleScore moduleScore)
    {
        return moduleScoreMapper.selectModuleScoreList(moduleScore);
    }

    /**
     * 新增模块分数
     * 
     * @param moduleScore 模块分数
     * @return 结果
     */
    @Override
    public int insertModuleScore(ModuleScore moduleScore)
    {
        return moduleScoreMapper.insertModuleScore(moduleScore);
    }

    /**
     * 修改模块分数
     * 
     * @param moduleScore 模块分数
     * @return 结果
     */
    @Override
    public int updateModuleScore(ModuleScore moduleScore)
    {
        return moduleScoreMapper.updateModuleScore(moduleScore);
    }

    /**
     * 批量删除模块分数
     * 
     * @param modIds 需要删除的模块分数主键
     * @return 结果
     */
    @Override
    public int deleteModuleScoreByModIds(Long[] modIds)
    {
        return moduleScoreMapper.deleteModuleScoreByModIds(modIds);
    }

    /**
     * 删除模块分数信息
     * 
     * @param modId 模块分数主键
     * @return 结果
     */
    @Override
    public int deleteModuleScoreByModId(Long modId)
    {
        return moduleScoreMapper.deleteModuleScoreByModId(modId);
    }
}
