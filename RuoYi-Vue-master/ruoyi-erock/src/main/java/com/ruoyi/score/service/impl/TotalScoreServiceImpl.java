package com.ruoyi.score.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.score.mapper.TotalScoreMapper;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.service.ITotalScoreService;

/**
 * 总分Service业务层处理
 * 
 * @author houq
 * @date 2024-04-22
 */
@Service
public class TotalScoreServiceImpl implements ITotalScoreService 
{
    @Autowired
    private TotalScoreMapper totalScoreMapper;

    /**
     * 查询总分
     * 
     * @param tsId 总分主键
     * @return 总分
     */
    @Override
    public TotalScore selectTotalScoreByTsId(Long tsId)
    {
        return totalScoreMapper.selectTotalScoreByTsId(tsId);
    }

    /**
     * 查询总分列表
     * 
     * @param totalScore 总分
     * @return 总分
     */
    @Override
    public List<TotalScore> selectTotalScoreList(TotalScore totalScore)
    {
        return totalScoreMapper.selectTotalScoreList(totalScore);
    }

    /**
     * 新增总分
     * 
     * @param totalScore 总分
     * @return 结果
     */
    @Override
    public int insertTotalScore(TotalScore totalScore)
    {
        return totalScoreMapper.insertTotalScore(totalScore);
    }

    /**
     * 修改总分
     * 
     * @param totalScore 总分
     * @return 结果
     */
    @Override
    public int updateTotalScore(TotalScore totalScore)
    {
        return totalScoreMapper.updateTotalScore(totalScore);
    }

    /**
     * 批量删除总分
     * 
     * @param tsIds 需要删除的总分主键
     * @return 结果
     */
    @Override
    public int deleteTotalScoreByTsIds(Long[] tsIds)
    {
        return totalScoreMapper.deleteTotalScoreByTsIds(tsIds);
    }

    /**
     * 删除总分信息
     * 
     * @param tsId 总分主键
     * @return 结果
     */
    @Override
    public int deleteTotalScoreByTsId(Long tsId)
    {
        return totalScoreMapper.deleteTotalScoreByTsId(tsId);
    }
}
