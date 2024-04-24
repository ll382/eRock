package com.ruoyi.InClassModule.service;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2A3PhysicaScore;

/**
 * A2 A3 体能成绩Service接口
 *
 * @author heye
 * @date 2024-04-14
 */
public interface IA2A3PhysicaScoreService
{
    /**
     * 查询A2 A3 体能成绩
     *
     * @param phyId A2 A3 体能成绩主键
     * @return A2 A3 体能成绩
     */
    public List<A2A3PhysicaScore> selectA2A3PhysicaScoreByPhysId(Long phyId);

    /**
     * 查询A2 A3 体能成绩列表
     *
     * @param a2A3PhysicaScore A2 A3 体能成绩
     * @return A2 A3 体能成绩集合
     */
    public List<A2A3PhysicaScore> selectA2A3PhysicaScoreList(A2A3PhysicaScore a2A3PhysicaScore);

    /**
     * 新增A2 A3 体能成绩
     *
     * @param a2A3PhysicaScore A2 A3 体能成绩
     * @return 结果
     */
    public int insertA2A3PhysicaScore(A2A3PhysicaScore a2A3PhysicaScore);

    /**
     * 修改A2 A3 体能成绩
     *
     * @param a2A3PhysicaScore A2 A3 体能成绩
     * @return 结果
     */
    public int updateA2A3PhysicaScore(A2A3PhysicaScore a2A3PhysicaScore);

    /**
     * 批量删除A2 A3 体能成绩
     *
     * @param physIds 需要删除的A2 A3 体能成绩主键集合
     * @return 结果
     */
    public int deleteA2A3PhysicaScoreByPhysIds(Long[] physIds);

    /**
     * 删除A2 A3 体能成绩信息
     *
     * @param physId A2 A3 体能成绩主键
     * @return 结果
     */
    public int deleteA2A3PhysicaScoreByPhysId(Long physId);
}
