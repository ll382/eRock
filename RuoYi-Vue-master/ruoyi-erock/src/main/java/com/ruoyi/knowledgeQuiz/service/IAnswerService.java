package com.ruoyi.knowledgeQuiz.service;

import java.util.List;
import com.ruoyi.knowledgeQuiz.domain.Answer;

/**
 * A1 知识测试 学生成绩Service接口
 * 
 * @author ljy
 * @date 2024-03-20
 */
public interface IAnswerService 
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
     * 批量删除A1 知识测试 学生成绩
     * 
     * @param ansIds 需要删除的A1 知识测试 学生成绩主键集合
     * @return 结果
     */
    public int deleteAnswerByAnsIds(Long[] ansIds);

    /**
     * 删除A1 知识测试 学生成绩信息
     * 
     * @param ansId A1 知识测试 学生成绩主键
     * @return 结果
     */
    public int deleteAnswerByAnsId(Long ansId);
}
