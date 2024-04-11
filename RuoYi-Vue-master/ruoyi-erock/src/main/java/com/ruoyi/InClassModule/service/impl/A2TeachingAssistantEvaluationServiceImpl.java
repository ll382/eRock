package com.ruoyi.InClassModule.service.impl;

import java.util.List;

import com.ruoyi.core.service.SelectUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2TeachingAssistantEvaluationMapper;
import com.ruoyi.InClassModule.domain.A2TeachingAssistantEvaluation;
import com.ruoyi.InClassModule.service.IA2TeachingAssistantEvaluationService;

/**
 * A2 合作学习 教师评价Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2TeachingAssistantEvaluationServiceImpl implements IA2TeachingAssistantEvaluationService
{
    @Autowired
    private A2TeachingAssistantEvaluationMapper a2TeachingAssistantEvaluationMapper;
    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 合作学习 教师评价
     *
     * @param taeId A2 合作学习 教师评价主键
     * @return A2 合作学习 教师评价
     */
    @Override
    public A2TeachingAssistantEvaluation selectA2TeachingAssistantEvaluationByTaeId(Long taeId)
    {
        return a2TeachingAssistantEvaluationMapper.selectA2TeachingAssistantEvaluationByTaeId(taeId);
    }

    /**
     * 查询A2 合作学习 教师评价列表
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return A2 合作学习 教师评价
     */
    @Override
    public List<A2TeachingAssistantEvaluation> selectA2TeachingAssistantEvaluationList(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        return a2TeachingAssistantEvaluationMapper.selectA2TeachingAssistantEvaluationList(a2TeachingAssistantEvaluation);
    }

    /**
     * 新增A2 合作学习 教师评价
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return 结果
     */
    @Override
    public int insertA2TeachingAssistantEvaluation(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        return a2TeachingAssistantEvaluationMapper.insertA2TeachingAssistantEvaluation(a2TeachingAssistantEvaluation);
    }

    /**
     * 修改A2 合作学习 教师评价
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return 结果
     */
    @Override
    public int updateA2TeachingAssistantEvaluation(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        return a2TeachingAssistantEvaluationMapper.updateA2TeachingAssistantEvaluation(a2TeachingAssistantEvaluation);
    }

    /**
     * 批量删除A2 合作学习 教师评价
     *
     * @param taeIds 需要删除的A2 合作学习 教师评价主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantEvaluationByTaeIds(Long[] taeIds)
    {
        return a2TeachingAssistantEvaluationMapper.deleteA2TeachingAssistantEvaluationByTaeIds(taeIds);
    }

    /**
     * 删除A2 合作学习 教师评价信息
     *
     * @param taeId A2 合作学习 教师评价主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantEvaluationByTaeId(Long taeId)
    {
        return a2TeachingAssistantEvaluationMapper.deleteA2TeachingAssistantEvaluationByTaeId(taeId);
    }
}
