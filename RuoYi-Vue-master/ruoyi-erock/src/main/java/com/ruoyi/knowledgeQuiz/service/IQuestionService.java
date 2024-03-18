package com.ruoyi.knowledgeQuiz.service;

import java.util.List;
import com.ruoyi.knowledgeQuiz.domain.Question;

/**
 * A1 知识测试 问Service接口
 * 
 * @author ljy
 * @date 2024-03-18
 */
public interface IQuestionService 
{
    /**
     * 查询A1 知识测试 问
     * 
     * @param qqId A1 知识测试 问主键
     * @return A1 知识测试 问
     */
    public Question selectQuestionByQqId(Long qqId);

    /**
     * 查询A1 知识测试 问列表
     * 
     * @param question A1 知识测试 问
     * @return A1 知识测试 问集合
     */
    public List<Question> selectQuestionList(Question question);

    /**
     * 新增A1 知识测试 问
     * 
     * @param question A1 知识测试 问
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改A1 知识测试 问
     * 
     * @param question A1 知识测试 问
     * @return 结果
     */
    public int updateQuestion(Question question);

    /**
     * 批量删除A1 知识测试 问
     * 
     * @param qqIds 需要删除的A1 知识测试 问主键集合
     * @return 结果
     */
    public int deleteQuestionByQqIds(Long[] qqIds);

    /**
     * 删除A1 知识测试 问信息
     * 
     * @param qqId A1 知识测试 问主键
     * @return 结果
     */
    public int deleteQuestionByQqId(Long qqId);
}
