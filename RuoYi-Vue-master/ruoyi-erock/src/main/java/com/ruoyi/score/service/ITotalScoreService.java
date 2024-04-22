package com.ruoyi.score.service;

import java.util.List;
import com.ruoyi.score.domain.TotalScore;

/**
 * 总分Service接口
 * 
 * @author houq
 * @date 2024-04-22
 */
public interface ITotalScoreService 
{
    /**
     * 查询总分
     * 
     * @param tsId 总分主键
     * @return 总分
     */
    public TotalScore selectTotalScoreByTsId(Long tsId);

    /**
     * 查询总分列表
     * 
     * @param totalScore 总分
     * @return 总分集合
     */
    public List<TotalScore> selectTotalScoreList(TotalScore totalScore);

    /**
     * 新增总分
     * 
     * @param totalScore 总分
     * @return 结果
     */
    public int insertTotalScore(TotalScore totalScore);

    /**
     * 修改总分
     * 
     * @param totalScore 总分
     * @return 结果
     */
    public int updateTotalScore(TotalScore totalScore);

    /**
     * 批量删除总分
     * 
     * @param tsIds 需要删除的总分主键集合
     * @return 结果
     */
    public int deleteTotalScoreByTsIds(Long[] tsIds);

    /**
     * 删除总分信息
     * 
     * @param tsId 总分主键
     * @return 结果
     */
    public int deleteTotalScoreByTsId(Long tsId);
}
