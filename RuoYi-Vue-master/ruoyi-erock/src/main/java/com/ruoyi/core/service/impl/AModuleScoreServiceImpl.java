package com.ruoyi.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.core.mapper.AModuleScoreMapper;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.service.IAModuleScoreService;

/**
 *  A 模块成绩Service业务层处理
 * 
 * @author heye
 * @date 2024-04-28
 */
@Service
public class AModuleScoreServiceImpl implements IAModuleScoreService 
{
    @Autowired
    private AModuleScoreMapper aModuleScoreMapper;

    /**
     * 查询 A 模块成绩
     * 
     * @param modscoId  A 模块成绩主键
     * @return  A 模块成绩
     */
    @Override
    public AModuleScore selectAModuleScoreByModscoId(Long modscoId)
    {
        return aModuleScoreMapper.selectAModuleScoreByModscoId(modscoId);
    }

    /**
     * 查询 A 模块成绩列表
     * 
     * @param aModuleScore  A 模块成绩
     * @return  A 模块成绩
     */
    @Override
    public List<AModuleScore> selectAModuleScoreList(AModuleScore aModuleScore)
    {
        return aModuleScoreMapper.selectAModuleScoreList(aModuleScore);
    }

    /**
     * 新增 A 模块成绩
     * 
     * @param aModuleScore  A 模块成绩
     * @return 结果
     */
    @Override
    public int insertAModuleScore(AModuleScore aModuleScore)
    {
        return aModuleScoreMapper.insertAModuleScore(aModuleScore);
    }

    /**
     * 修改 A 模块成绩
     * 
     * @param aModuleScore  A 模块成绩
     * @return 结果
     */
    @Override
    public int updateAModuleScore(AModuleScore aModuleScore)
    {
        return aModuleScoreMapper.updateAModuleScore(aModuleScore);
    }

    /**
     * 批量删除 A 模块成绩
     * 
     * @param modscoIds 需要删除的 A 模块成绩主键
     * @return 结果
     */
    @Override
    public int deleteAModuleScoreByModscoIds(Long[] modscoIds)
    {
        return aModuleScoreMapper.deleteAModuleScoreByModscoIds(modscoIds);
    }

    /**
     * 删除 A 模块成绩信息
     * 
     * @param modscoId  A 模块成绩主键
     * @return 结果
     */
    @Override
    public int deleteAModuleScoreByModscoId(Long modscoId)
    {
        return aModuleScoreMapper.deleteAModuleScoreByModscoId(modscoId);
    }
}
