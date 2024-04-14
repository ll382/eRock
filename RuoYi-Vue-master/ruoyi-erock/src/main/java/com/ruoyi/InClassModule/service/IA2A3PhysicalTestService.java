package com.ruoyi.InClassModule.service;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2A3PhysicalTest;

/**
 * A2 A3体能测试Service接口
 *
 * @author heye
 * @date 2024-04-14
 */
public interface IA2A3PhysicalTestService
{
    /**
     * 查询A2 A3体能测试
     *
     * @param enumId A2 A3体能测试主键
     * @return A2 A3体能测试
     */
    public A2A3PhysicalTest selectA2A3PhysicalTestByPhyId(Long enumId, Long crId);

    /**
     * 查询A2 A3体能测试列表
     *
     * @param a2A3PhysicalTest A2 A3体能测试
     * @return A2 A3体能测试集合
     */
    public List<A2A3PhysicalTest> selectA2A3PhysicalTestList(A2A3PhysicalTest a2A3PhysicalTest);

    /**
     * 新增A2 A3体能测试
     *
     * @param a2A3PhysicalTest A2 A3体能测试
     * @return 结果
     */
    public int insertA2A3PhysicalTest(A2A3PhysicalTest a2A3PhysicalTest);

    /**
     * 修改A2 A3体能测试
     *
     * @param a2A3PhysicalTest A2 A3体能测试
     * @return 结果
     */
    public int updateA2A3PhysicalTest(A2A3PhysicalTest a2A3PhysicalTest);

    /**
     * 批量删除A2 A3体能测试
     *
     * @param phyIds 需要删除的A2 A3体能测试主键集合
     * @return 结果
     */
    public int deleteA2A3PhysicalTestByPhyIds(Long[] phyIds);

    /**
     * 删除A2 A3体能测试信息
     *
     * @param phyId A2 A3体能测试主键
     * @return 结果
     */
    public int deleteA2A3PhysicalTestByPhyId(Long phyId);
}
