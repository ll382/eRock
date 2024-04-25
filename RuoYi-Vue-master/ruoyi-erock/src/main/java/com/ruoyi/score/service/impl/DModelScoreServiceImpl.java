package com.ruoyi.score.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.score.mapper.DModelScoreMapper;
import com.ruoyi.score.domain.DModelScore;
import com.ruoyi.score.service.IDModelScoreService;

/**
 * D 模块成绩Service业务层处理
 * 
 * @author heye
 * @date 2024-04-25
 */
@Service
public class DModelScoreServiceImpl implements IDModelScoreService 
{
    @Autowired
    private DModelScoreMapper dModelScoreMapper;

    /**
     * 查询D 模块成绩
     * 
     * @param modscId D 模块成绩主键
     * @return D 模块成绩
     */
    @Override
    public DModelScore selectDModelScoreByModscId(Long modscId)
    {
        return dModelScoreMapper.selectDModelScoreByModscId(modscId);
    }

    /**
     * 查询D 模块成绩列表
     * 
     * @param dModelScore D 模块成绩
     * @return D 模块成绩
     */
    @Override
    public List<DModelScore> selectDModelScoreList(DModelScore dModelScore)
    {
        return dModelScoreMapper.selectDModelScoreList(dModelScore);
    }

    /**
     * 新增D 模块成绩
     * 
     * @param dModelScore D 模块成绩
     * @return 结果
     */
    @Override
    public int insertDModelScore(DModelScore dModelScore)
    {
        return dModelScoreMapper.insertDModelScore(dModelScore);
    }

    /**
     * 修改D 模块成绩
     * 
     * @param dModelScore D 模块成绩
     * @return 结果
     */
    @Override
    public int updateDModelScore(DModelScore dModelScore)
    {
        return dModelScoreMapper.updateDModelScore(dModelScore);
    }

    /**
     * 批量删除D 模块成绩
     * 
     * @param modscIds 需要删除的D 模块成绩主键
     * @return 结果
     */
    @Override
    public int deleteDModelScoreByModscIds(Long[] modscIds)
    {
        return dModelScoreMapper.deleteDModelScoreByModscIds(modscIds);
    }

    /**
     * 删除D 模块成绩信息
     * 
     * @param modscId D 模块成绩主键
     * @return 结果
     */
    @Override
    public int deleteDModelScoreByModscId(Long modscId)
    {
        return dModelScoreMapper.deleteDModelScoreByModscId(modscId);
    }
}
