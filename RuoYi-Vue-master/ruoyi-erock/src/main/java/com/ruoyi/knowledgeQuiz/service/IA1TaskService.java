package com.ruoyi.knowledgeQuiz.service;

import java.util.List;
import com.ruoyi.knowledgeQuiz.domain.A1Task;

/**
 * A1 知识测试任务Service接口
 * 
 * @author heye
 * @date 2024-04-06
 */
public interface IA1TaskService 
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
     * 批量删除A1 知识测试任务
     * 
     * @param taskIds 需要删除的A1 知识测试任务主键集合
     * @return 结果
     */
    public int deleteA1TaskByTaskIds(Long[] taskIds);

    /**
     * 删除A1 知识测试任务信息
     * 
     * @param taskId A1 知识测试任务主键
     * @return 结果
     */
    public int deleteA1TaskByTaskId(Long taskId);
}
