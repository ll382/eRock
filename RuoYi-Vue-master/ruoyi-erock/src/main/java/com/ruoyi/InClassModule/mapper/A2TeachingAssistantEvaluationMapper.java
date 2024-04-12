package com.ruoyi.InClassModule.mapper;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2TeachingAssistantEvaluation;

/**
 * A2 合作学习 教师评价Mapper接口
 *
 * @author heye
 * @date 2024-04-11
 */
public interface A2TeachingAssistantEvaluationMapper
{
    /**
     * 查询A2 合作学习 教师评价
     *
     * @param crId A2 合作学习 教师评价主键
     * @return A2 合作学习 教师评价
     */
    public List<A2TeachingAssistantEvaluation> selectA2TeachingAssistantEvaluationByTaeId(Long crId);

    /**
     * 查询A2 合作学习 教师评价列表
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return A2 合作学习 教师评价集合
     */
    public List<A2TeachingAssistantEvaluation> selectA2TeachingAssistantEvaluationList(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation);

    /**
     * 新增A2 合作学习 教师评价
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return 结果
     */
    public int insertA2TeachingAssistantEvaluation(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation);

    /**
     * 修改A2 合作学习 教师评价
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return 结果
     */
    public int updateA2TeachingAssistantEvaluation(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation);

    /**
     * 删除A2 合作学习 教师评价
     *
     * @param taeId A2 合作学习 教师评价主键
     * @return 结果
     */
    public int deleteA2TeachingAssistantEvaluationByTaeId(Long taeId);

    /**
     * 批量删除A2 合作学习 教师评价
     *
     * @param taeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA2TeachingAssistantEvaluationByTaeIds(Long[] taeIds);
}
