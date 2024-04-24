package com.ruoyi.knowledgeQuiz.service.impl;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.core.service.SelectUser;
import com.ruoyi.teachingExchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Objects;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.knowledgeQuiz.domain.A1Answered;
import com.ruoyi.knowledgeQuiz.mapper.AnswerMapper;
import com.ruoyi.knowledgeQuiz.service.IAnswerService;

/**
 * A1 知识测试 学生成绩Service业务层处理
 *
 * @author heye
 * @date 2024-04-18
 */
@Service
public class AnswerServiceImpl implements IAnswerService
{
    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private SelectUser selectUser;

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
    @Transactional
    @Override
    public int insertAnswer(Answer answer)
    {
        answer.setAnsTime(new Date());
        int rows = answerMapper.insertAnswer(answer);
        insertA1Answered(answer);
        return Math.toIntExact(answer.getAnsId());
    }

    /**
     * 修改A1 知识测试 学生成绩
     *
     * @param answer A1 知识测试 学生成绩
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAnswer(Answer answer)
    {
        answerMapper.deleteA1AnsweredByAnsId(answer.getAnsId());
        insertA1Answered(answer);
        answer.setAnsResponse(0L);
        answer.setAnsApos(0L);
        answer.setAnsResponse((long) answer.getA1AnsweredList().size());
        answer.setAnsApos(answerMapper.selectCorrect(answer.getA1AnsweredList()));
        return answerMapper.updateAnswer(answer);
    }

    /**
     * 批量删除A1 知识测试 学生成绩
     *
     * @param ansIds 需要删除的A1 知识测试 学生成绩主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAnswerByAnsIds(Long[] ansIds)
    {
        answerMapper.deleteA1AnsweredByAnsIds(ansIds);
        return answerMapper.deleteAnswerByAnsIds(ansIds);
    }

    /**
     * 删除A1 知识测试 学生成绩信息
     *
     * @param ansId A1 知识测试 学生成绩主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAnswerByAnsId(Long ansId)
    {
        answerMapper.deleteA1AnsweredByAnsId(ansId);
        return answerMapper.deleteAnswerByAnsId(ansId);
    }

    /**
     * 新增A1 知识测试 答题信息
     *
     * @param answer A1 知识测试 学生成绩对象
     */
    public void insertA1Answered(Answer answer)
    {
        List<A1Answered> a1AnsweredList = answer.getA1AnsweredList();
        Long ansId = answer.getAnsId();
        if (StringUtils.isNotNull(a1AnsweredList))
        {
            List<A1Answered> list = new ArrayList<A1Answered>();
            for (A1Answered a1Answered : a1AnsweredList)
            {
                a1Answered.setAnsId(ansId);
                list.add(a1Answered);
            }
            if (list.size() > 0)
            {
                answerMapper.batchA1Answered(list);
            }
        }
    }
}
