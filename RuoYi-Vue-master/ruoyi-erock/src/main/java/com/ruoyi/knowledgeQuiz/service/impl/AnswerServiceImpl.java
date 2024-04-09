package com.ruoyi.knowledgeQuiz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.knowledgeQuiz.mapper.AnswerMapper;
import com.ruoyi.teachingExchange.domain.Answer;
import com.ruoyi.knowledgeQuiz.service.IAnswerService;

/**
 * A1 知识测试 学生成绩Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-20
 */
@Service
public class AnswerServiceImpl implements IAnswerService 
{
    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 查询A1 知识测试 学生成绩
     * 
     * @param ansId A1 知识测试 学生成绩主键
     * @return A1 知识测试 学生成绩
     */
    @Override
    public Answer selectAnswerByAnsId(Long ansId)
    {
        return answerMapper.selectAnswerByAnsId(ansId);
    }

    /**
     * 查询A1 知识测试 学生成绩列表
     * 
     * @param answer A1 知识测试 学生成绩
     * @return A1 知识测试 学生成绩
     */
    @Override
    public List<Answer> selectAnswerList(Answer answer)
    {
        return answerMapper.selectAnswerList(answer);
    }

    /**
     * 新增A1 知识测试 学生成绩
     * 
     * @param answer A1 知识测试 学生成绩
     * @return 结果
     */
    @Override
    public int insertAnswer(Answer answer)
    {
        return answerMapper.insertAnswer(answer);
    }

    /**
     * 修改A1 知识测试 学生成绩
     * 
     * @param answer A1 知识测试 学生成绩
     * @return 结果
     */
    @Override
    public int updateAnswer(Answer answer)
    {
        return answerMapper.updateAnswer(answer);
    }

    /**
     * 批量删除A1 知识测试 学生成绩
     * 
     * @param ansIds 需要删除的A1 知识测试 学生成绩主键
     * @return 结果
     */
    @Override
    public int deleteAnswerByAnsIds(Long[] ansIds)
    {
        return answerMapper.deleteAnswerByAnsIds(ansIds);
    }

    /**
     * 删除A1 知识测试 学生成绩信息
     * 
     * @param ansId A1 知识测试 学生成绩主键
     * @return 结果
     */
    @Override
    public int deleteAnswerByAnsId(Long ansId)
    {
        return answerMapper.deleteAnswerByAnsId(ansId);
    }
}
