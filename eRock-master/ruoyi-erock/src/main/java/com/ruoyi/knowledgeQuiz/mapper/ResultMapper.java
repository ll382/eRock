package com.ruoyi.knowledgeQuiz.mapper;

import java.util.List;
import com.ruoyi.knowledgeQuiz.domain.Result;

/**
 * A1 知识测试 答Mapper接口
 * 
 * @author ljy
 * @date 2024-03-18
 */
public interface ResultMapper 
{
    /**
     * 查询A1 知识测试 答
     * 
     * @param ooId A1 知识测试 答主键
     * @return A1 知识测试 答
     */
    public Result selectResultByOoId(Long ooId);

    /**
     * 查询A1 知识测试 答列表
     * 
     * @param result A1 知识测试 答
     * @return A1 知识测试 答集合
     */
    public List<Result> selectResultList(Result result);

    /**
     * 新增A1 知识测试 答
     * 
     * @param result A1 知识测试 答
     * @return 结果
     */
    public int insertResult(Result result);

    /**
     * 修改A1 知识测试 答
     * 
     * @param result A1 知识测试 答
     * @return 结果
     */
    public int updateResult(Result result);

    /**
     * 删除A1 知识测试 答
     * 
     * @param ooId A1 知识测试 答主键
     * @return 结果
     */
    public int deleteResultByOoId(Long ooId);

    /**
     * 批量删除A1 知识测试 答
     * 
     * @param ooIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResultByOoIds(Long[] ooIds);
}
