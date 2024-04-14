package com.ruoyi.InClassModule.mapper;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2A3PhysicalTest;
import com.ruoyi.InClassModule.domain.A2A3PhysicaScore;
import org.apache.ibatis.annotations.Param;

/**
 * A2 A3体能测试Mapper接口
 *
 * @author heye
 * @date 2024-04-14
 */
public interface A2A3PhysicalTestMapper
{
    /**
     * 查询A2 A3体能测试
     *
     * @param enumId A2 A3体能测试主键
     * @return A2 A3体能测试
     */
    public A2A3PhysicalTest selectA2A3PhysicalTestByPhyId(@Param("enumId") Long enumId,@Param("crId") Long crId);

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
     * 删除A2 A3体能测试
     *
     * @param phyId A2 A3体能测试主键
     * @return 结果
     */
    public int deleteA2A3PhysicalTestByPhyId(Long phyId);

    /**
     * 批量删除A2 A3体能测试
     *
     * @param phyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA2A3PhysicalTestByPhyIds(Long[] phyIds);

    /**
     * 批量删除A2 A3 体能成绩
     *
     * @param phyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA2A3PhysicaScoreByPhyIds(Long[] phyIds);

    /**
     * 批量新增A2 A3 体能成绩
     *
     * @param a2A3PhysicaScoreList A2 A3 体能成绩列表
     * @return 结果
     */
    public int batchA2A3PhysicaScore(List<A2A3PhysicaScore> a2A3PhysicaScoreList);


    /**
     * 通过A2 A3体能测试主键删除A2 A3 体能成绩信息
     *
     * @param phyId A2 A3体能测试ID
     * @return 结果
     */
    public int deleteA2A3PhysicaScoreByPhyId(Long phyId);
}
