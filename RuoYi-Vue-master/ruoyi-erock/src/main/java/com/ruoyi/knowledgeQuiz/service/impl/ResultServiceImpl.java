package com.ruoyi.knowledgeQuiz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.knowledgeQuiz.mapper.ResultMapper;
import com.ruoyi.knowledgeQuiz.domain.Result;
import com.ruoyi.knowledgeQuiz.service.IResultService;

/**
 * A1 知识测试 答Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-18
 */
@Service
public class ResultServiceImpl implements IResultService 
{
    @Autowired
    private ResultMapper resultMapper;

    /**
     * 查询A1 知识测试 答
     * 
     * @param ooId A1 知识测试 答主键
     * @return A1 知识测试 答
     */
    @Override
    public Result selectResultByOoId(Long ooId)
    {
        return resultMapper.selectResultByOoId(ooId);
    }

    /**
     * 查询A1 知识测试 答列表
     * 
     * @param result A1 知识测试 答
     * @return A1 知识测试 答
     */
    @Override
    public List<Result> selectResultList(Result result)
    {
        return resultMapper.selectResultList(result);
    }

    /**
     * 新增A1 知识测试 答
     * 
     * @param result A1 知识测试 答
     * @return 结果
     */
    @Override
    public int insertResult(Result result)
    {
        return resultMapper.insertResult(result);
    }

    /**
     * 修改A1 知识测试 答
     * 
     * @param result A1 知识测试 答
     * @return 结果
     */
    @Override
    public int updateResult(Result result)
    {
        return resultMapper.updateResult(result);
    }

    /**
     * 批量删除A1 知识测试 答
     * 
     * @param ooIds 需要删除的A1 知识测试 答主键
     * @return 结果
     */
    @Override
    public int deleteResultByOoIds(Long[] ooIds)
    {
        return resultMapper.deleteResultByOoIds(ooIds);
    }

    /**
     * 删除A1 知识测试 答信息
     * 
     * @param ooId A1 知识测试 答主键
     * @return 结果
     */
    @Override
    public int deleteResultByOoId(Long ooId)
    {
        return resultMapper.deleteResultByOoId(ooId);
    }
}
