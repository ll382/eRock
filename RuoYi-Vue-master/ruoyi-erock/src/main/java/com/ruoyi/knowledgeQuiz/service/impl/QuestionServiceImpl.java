package com.ruoyi.knowledgeQuiz.service.impl;

import java.util.*;

import com.ruoyi.core.service.SelectUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.knowledgeQuiz.domain.Result;
import com.ruoyi.knowledgeQuiz.mapper.QuestionMapper;
import com.ruoyi.knowledgeQuiz.domain.Question;
import com.ruoyi.knowledgeQuiz.service.IQuestionService;

import static com.ruoyi.core.service.impl.SelectUserImpl.generateRandomNumbers;

/**
 * A1 知识测试 问Service业务层处理
 *
 * @author ljy
 * @date 2024-03-18
 */
@Service
public class QuestionServiceImpl implements IQuestionService
{
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 查询A1 知识测试 问
     *
     * @param qqId A1 知识测试 问主键
     * @return A1 知识测试 问
     */
    @Override
    public Question selectQuestionByQqId(Long qqId)
    {
        return questionMapper.selectQuestionByQqId(qqId);
    }

    @Override
    public List<Question> selectQuestionByQqIdList(Long num) {
//        获得所有题库id
        List<Integer> byMax = questionMapper.selectQuestionByMax();
//        查出随机于其数组的所有索引随机数
        LinkedHashSet<Integer> set = generateRandomNumbers(Math.toIntExact(num), byMax.size() - 1);
//        建立随机结果数组
        List<Integer> taskIdList = new ArrayList<>();
//        根据随机索引数查题库
        set.forEach(lset -> {
            taskIdList.add(byMax.get(lset));
        });
//        通过随即结果数组查询对应的详细结果
        return questionMapper.selectQuestionByList(taskIdList);
    }

    /**
     * 查询A1 知识测试 问列表
     *
     * @param question A1 知识测试 问
     * @return A1 知识测试 问
     */
    @Override
    public List<Question> selectQuestionList(Question question)
    {
        return questionMapper.selectQuestionList(question);
    }

    /**
     * 新增A1 知识测试 问
     *
     * @param question A1 知识测试 问
     * @return 结果
     */
    @Transactional
    @Override
    public int insertQuestion(Question question)
    {
        int rows = questionMapper.insertQuestion(question);
        insertResult(question);
        return rows;
    }

    /**
     * 修改A1 知识测试 问
     *
     * @param question A1 知识测试 问
     * @return 结果
     */
    @Transactional
    @Override
    public int updateQuestion(Question question)
    {
        questionMapper.deleteResultByQqId(question.getQqId());
        insertResult(question);
        return questionMapper.updateQuestion(question);
    }

    /**
     * 批量删除A1 知识测试 问
     *
     * @param qqIds 需要删除的A1 知识测试 问主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteQuestionByQqIds(Long[] qqIds)
    {
        questionMapper.deleteResultByQqIds(qqIds);
        return questionMapper.deleteQuestionByQqIds(qqIds);
    }

    /**
     * 删除A1 知识测试 问信息
     *
     * @param qqId A1 知识测试 问主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteQuestionByQqId(Long qqId)
    {
        questionMapper.deleteResultByQqId(qqId);
        return questionMapper.deleteQuestionByQqId(qqId);
    }

    /**
     * 新增A1 知识测试 答信息
     *
     * @param question A1 知识测试 问对象
     */
    public void insertResult(Question question)
    {
        List<Result> resultList = question.getResultList();
        Long qqId = question.getQqId();
        if (StringUtils.isNotNull(resultList))
        {
            List<Result> list = new ArrayList<Result>();
            for (Result result : resultList)
            {
                result.setQqId(qqId);
                list.add(result);
            }
            if (list.size() > 0)
            {
                questionMapper.batchResult(list);
            }
        }
    }
}
