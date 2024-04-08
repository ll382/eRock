package com.ruoyi.knowledgeQuiz.service.impl;

import java.text.NumberFormat;
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

import static com.ruoyi.common.utils.PageUtils.startPage;

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
//        获取未处理过的学生成绩单
        startPage();
        A1Task a1Task = a1TaskMapper.selectA1TaskByTaskId(taskId);
//        计算每道题平均百分制占分
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        Double tNum = Double.parseDouble(nf.format(100.0 / a1Task.getTaskNum()));
//        为每位同学计算对应的分数
        a1Task.getAnswerList().forEach(obj -> {
            obj.setAnsScore(tNum * obj.getAnsApos());
        });
        startPage();
        a1Task.setUnpaidList(a1TaskMapper.selectStudentList(a1Task.getTaskId()));
//        返回内容
        return a1Task;
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
//        获取基础知识测试任务数据
        List<A1Task> a1Tasks = a1TaskMapper.selectTaskList(a1Task);
////        查找当前任务学生提交情况
//        a1Tasks.forEach(task -> {
////            查看未交名单
//            task.setUnpaid(a1TaskMapper.selectStudentList(task.getTaskId()));
////            查看已交名单
//            task.setSubmitted(a1TaskMapper.selectFinishStudentList(task.getTaskId()));
//        });
        return a1Tasks;
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