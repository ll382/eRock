package com.ruoyi.knowledgeQuiz.mapper;

import java.util.List;
import com.ruoyi.knowledgeQuiz.domain.A1Task;
import com.ruoyi.teachingExchange.domain.Answer;

/**
 * A1 知识测试任务Mapper接口
 * 
 * @author heye
 * @date 2024-04-06
 */
public interface A1TaskMapper 
{
    /**
     * 查询A1 知识测试任务
     * 
     * @param taskId A1 知识测试任务主键
     * @return A1 知识测试任务
     */
    public A1Task selectA1TaskByTaskId(Long taskId);

    /**
     * 查询A1 知识测试任务列表
     * 
     * @param a1Task A1 知识测试任务
     * @return A1 知识测试任务集合
     */
    public List<A1Task> selectA1TaskList(A1Task a1Task);

    /**
     * 新增A1 知识测试任务
     * 
     * @param a1Task A1 知识测试任务
     * @return 结果
     */
    public int insertA1Task(A1Task a1Task);

    /**
     * 修改A1 知识测试任务
     * 
     * @param a1Task A1 知识测试任务
     * @return 结果
     */
    public int updateA1Task(A1Task a1Task);

    /**
     * 删除A1 知识测试任务
     * 
     * @param taskId A1 知识测试任务主键
     * @return 结果
     */
    public int deleteA1TaskByTaskId(Long taskId);

    /**
     * 批量删除A1 知识测试任务
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA1TaskByTaskIds(Long[] taskIds);

    /**
     * 批量删除A1 知识测试 学生成绩
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnswerByTaskIds(Long[] taskIds);
    
    /**
     * 批量新增A1 知识测试 学生成绩
     * 
     * @param answerList A1 知识测试 学生成绩列表
     * @return 结果
     */
    public int batchAnswer(List<Answer> answerList);
    

    /**
     * 通过A1 知识测试任务主键删除A1 知识测试 学生成绩信息
     * 
     * @param taskId A1 知识测试任务ID
     * @return 结果
     */
    public int deleteAnswerByTaskId(Long taskId);
}
