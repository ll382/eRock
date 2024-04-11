package com.ruoyi.InClassModule.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2IdeologicalPerformanceMapper;
import com.ruoyi.InClassModule.domain.A2IdeologicalPerformance;
import com.ruoyi.InClassModule.service.IA2IdeologicalPerformanceService;

/**
 * A2  思政教育 思政现Service业务层处理
 * 
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2IdeologicalPerformanceServiceImpl implements IA2IdeologicalPerformanceService 
{
    @Autowired
    private A2IdeologicalPerformanceMapper a2IdeologicalPerformanceMapper;

    /**
     * 查询A2  思政教育 思政现
     * 
     * @param ipId A2  思政教育 思政现主键
     * @return A2  思政教育 思政现
     */
    @Override
    public A2IdeologicalPerformance selectA2IdeologicalPerformanceByIpId(Long ipId)
    {
        return a2IdeologicalPerformanceMapper.selectA2IdeologicalPerformanceByIpId(ipId);
    }

    /**
     * 查询A2  思政教育 思政现列表
     * 
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return A2  思政教育 思政现
     */
    @Override
    public List<A2IdeologicalPerformance> selectA2IdeologicalPerformanceList(A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        return a2IdeologicalPerformanceMapper.selectA2IdeologicalPerformanceList(a2IdeologicalPerformance);
    }

    /**
     * 新增A2  思政教育 思政现
     * 
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return 结果
     */
    @Override
    public int insertA2IdeologicalPerformance(A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        return a2IdeologicalPerformanceMapper.insertA2IdeologicalPerformance(a2IdeologicalPerformance);
    }

    /**
     * 修改A2  思政教育 思政现
     * 
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return 结果
     */
    @Override
    public int updateA2IdeologicalPerformance(A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        return a2IdeologicalPerformanceMapper.updateA2IdeologicalPerformance(a2IdeologicalPerformance);
    }

    /**
     * 批量删除A2  思政教育 思政现
     * 
     * @param ipIds 需要删除的A2  思政教育 思政现主键
     * @return 结果
     */
    @Override
    public int deleteA2IdeologicalPerformanceByIpIds(Long[] ipIds)
    {
        return a2IdeologicalPerformanceMapper.deleteA2IdeologicalPerformanceByIpIds(ipIds);
    }

    /**
     * 删除A2  思政教育 思政现信息
     * 
     * @param ipId A2  思政教育 思政现主键
     * @return 结果
     */
    @Override
    public int deleteA2IdeologicalPerformanceByIpId(Long ipId)
    {
        return a2IdeologicalPerformanceMapper.deleteA2IdeologicalPerformanceByIpId(ipId);
    }
}
