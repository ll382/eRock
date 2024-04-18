package com.ruoyi.knowledgeQuiz.mapper;

import java.util.List;

import com.ruoyi.knowledgeQuiz.domain.A1Answered;
import com.ruoyi.teachingExchange.domain.Answer;

/**
 * A1 知识测试 学生成绩Mapper接口
 *
 * @author heye
 * @date 2024-04-18
 */
public interface AnswerMapper
{
    /**
     * 查询A1 知识测试 学生成绩
     *
     * @param ansId A1 知识测试 学生成绩主键
     * @return A1 知识测试 学生成绩
     */
    public Answer selectAnswerByAnsId(Long ansId);

    /**
     * 查询A1 知识测试 学生成绩列表
     *
     * @param answer A1 知识测试 学生成绩
     * @return A1 知识测试 学生成绩集合
     */
    public List<Answer> selectAnswerList(Answer answer);

    /**
     * 新增A1 知识测试 学生成绩
     *
     * @param answer A1 知识测试 学生成绩
     * @return 结果
     */
    public int insertAnswer(Answer answer);

    /**
     * 修改A1 知识测试 学生成绩
     *
     * @param answer A1 知识测试 学生成绩
     * @return 结果
     */
    public int updateAnswer(Answer answer);

    /**
     * 删除A1 知识测试 学生成绩
     *
     * @param ansId A1 知识测试 学生成绩主键
     * @return 结果
     */
    public int deleteAnswerByAnsId(Long ansId);

    /**
     * 批量删除A1 知识测试 学生成绩
     *
     * @param ansIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnswerByAnsIds(Long[] ansIds);

    /**
     * 批量删除A1 知识测试 答题
     *
     * @param ansIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA1AnsweredByAnsIds(Long[] ansIds);

    /**
     * 批量新增A1 知识测试 答题
     *
     * @param a1AnsweredList A1 知识测试 答题列表
     * @return 结果
     */
    public int batchA1Answered(List<A1Answered> a1AnsweredList);


    /**
     * 通过A1 知识测试 学生成绩主键删除A1 知识测试 答题信息
     *
     * @param ansId A1 知识测试 学生成绩ID
     * @return 结果
     */
    public int deleteA1AnsweredByAnsId(Long ansId);
}
