package com.ruoyi.knowledgeQuiz.service.impl;

import java.util.List;

import com.ruoyi.knowledgeQuiz.domain.A1Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.teachingExchange.domain.Answer;
import com.ruoyi.knowledgeQuiz.mapper.A1TaskMapper;
import com.ruoyi.knowledgeQuiz.service.IA1TaskService;

/**
 * A1 知识测试任务Service业务层处理
 *
 * @author heye
 * @date 2024-04-06
 */
@Service
public class A1TaskServiceImpl implements IA1TaskService
{
    @Autowired
    private A1TaskMapper a1TaskMapper;

    /**
     * 查询A1 知识测试任务
     *
     * @param taskId A1 知识测试任务主键
     * @return A1 知识测试任务
     */
    @Override
    public A1Task selectA1TaskByTaskId(Long taskId)
    {
        return a1TaskMapper.selectA1TaskByTaskId(taskId);
    }

    /**
     * 查询A1 知识测试任务列表
     *
     * @param a1Task A1 知识测试任务
     * @return A1 知识测试任务
     */
    @Override
    public List<A1Task> selectA1TaskList(A1Task a1Task)
    {
        return a1TaskMapper.selectA1TaskList(a1Task);
    }

    /**
     * 新增A1 知识测试任务
     *
     * @param a1Task A1 知识测试任务
     * @return 结果
     */
    @Transactional
    @Override
    public int insertA1Task(A1Task a1Task)
    {
        int rows = a1TaskMapper.insertA1Task(a1Task);
        insertAnswer(a1Task);
        return rows;
    }

    /**
     * 修改A1 知识测试任务
     *
     * @param a1Task A1 知识测试任务
     * @return 结果
     */
    @Transactional
    @Override
    public int updateA1Task(A1Task a1Task)
    {
        a1TaskMapper.deleteAnswerByTaskId(a1Task.getTaskId());
        insertAnswer(a1Task);
        return a1TaskMapper.updateA1Task(a1Task);
    }

    /**
     * 批量删除A1 知识测试任务
     *
     * @param taskIds 需要删除的A1 知识测试任务主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteA1TaskByTaskIds(Long[] taskIds)
    {
        a1TaskMapper.deleteAnswerByTaskIds(taskIds);
        return a1TaskMapper.deleteA1TaskByTaskIds(taskIds);
    }

    /**
     * 删除A1 知识测试任务信息
     *
     * @param taskId A1 知识测试任务主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteA1TaskByTaskId(Long taskId)
    {
        a1TaskMapper.deleteAnswerByTaskId(taskId);
        return a1TaskMapper.deleteA1TaskByTaskId(taskId);
    }

    /**
     * 新增A1 知识测试 学生成绩信息
     *
     * @param a1Task A1 知识测试任务对象
     */
    public void insertAnswer(A1Task a1Task)
    {
        List<Answer> answerList = a1Task.getAnswerList();
        Long taskId = a1Task.getTaskId();
        if (StringUtils.isNotNull(answerList))
        {
            List<Answer> list = new ArrayList<Answer>();
            for (Answer answer : answerList)
            {
                answer.setTaskId(taskId);
                list.add(answer);
            }
            if (list.size() > 0)
            {
                a1TaskMapper.batchAnswer(list);
            }
        }
    }
}
