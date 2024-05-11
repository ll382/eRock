package com.ruoyi.InClassModule.mapper;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2IdeologicalPerformance;

/**
 * A2  思政教育 思政现Mapper接口
 *
 * @author heye
 * @date 2024-04-11
 */
public interface A2IdeologicalPerformanceMapper
{
    /**
     * 查询A2  思政教育 思政现
     *
     * @param crId A2  思政教育 思政现主键
     * @return A2  思政教育 思政现
     */
    public List<A2IdeologicalPerformance> selectA2IdeologicalPerformanceByIpId(Long crId);

    /**
     * 查询A2  思政教育 思政现列表
     *
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return A2  思政教育 思政现集合
     */
    public List<A2IdeologicalPerformance> selectA2IdeologicalPerformanceList(A2IdeologicalPerformance a2IdeologicalPerformance);

    /**
     * 新增A2  思政教育 思政现
     *
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return 结果
     */
    public int insertA2IdeologicalPerformance(A2IdeologicalPerformance a2IdeologicalPerformance);

    /**
     * 修改A2  思政教育 思政现
     *
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return 结果
     */
    public int updateA2IdeologicalPerformance(A2IdeologicalPerformance a2IdeologicalPerformance);

    /**
     * 删除A2  思政教育 思政现
     *
     * @param ipId A2  思政教育 思政现主键
     * @return 结果
     */
    public int deleteA2IdeologicalPerformanceByIpId(Long ipId);

    /**
     * 批量删除A2  思政教育 思政现
     *
     * @param ipIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA2IdeologicalPerformanceByIpIds(Long[] ipIds);
}
