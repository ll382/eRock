package com.ruoyi.InClassModule.service.impl;

import java.util.List;

import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2A3PhysicaScoreMapper;
import com.ruoyi.InClassModule.domain.A2A3PhysicaScore;
import com.ruoyi.InClassModule.service.IA2A3PhysicaScoreService;

/**
 * A2 A3 体能成绩Service业务层处理
 *
 * @author heye
 * @date 2024-04-14
 */
@Service
public class A2A3PhysicaScoreServiceImpl implements IA2A3PhysicaScoreService
{
    @Autowired
    private A2A3PhysicaScoreMapper a2A3PhysicaScoreMapper;

    @Autowired
    SelectUser selectUser;

    /**
     * 查询A2 A3 体能成绩
     *
     * @param phyId A2 A3 体能成绩主键
     * @return A2 A3 体能成绩
     */
    @Override
    public List<A2A3PhysicaScore> selectA2A3PhysicaScoreByPhysId(Long phyId)
    {
        return a2A3PhysicaScoreMapper.selectA2A3PhysicaScoreByPhysId(phyId);
    }

    /**
     * 查询A2 A3 体能成绩列表
     *
     * @param a2A3PhysicaScore A2 A3 体能成绩
     * @return A2 A3 体能成绩
     */
    @Override
    public List<A2A3PhysicaScore> selectA2A3PhysicaScoreList(A2A3PhysicaScore a2A3PhysicaScore)
    {
        return selectUser.selectStudent(a2A3PhysicaScoreMapper.selectA2A3PhysicaScoreList(a2A3PhysicaScore));
    }

    /**
     * 新增A2 A3 体能成绩
     *
     * @param a2A3PhysicaScore A2 A3 体能成绩
     * @return 结果
     */
    @Override
    public int insertA2A3PhysicaScore(A2A3PhysicaScore a2A3PhysicaScore)
    {
        return a2A3PhysicaScoreMapper.insertA2A3PhysicaScore(a2A3PhysicaScore);
    }

    /**
     * 修改A2 A3 体能成绩
     *
     * @param a2A3PhysicaScore A2 A3 体能成绩
     * @return 结果
     */
    @Override
    public int updateA2A3PhysicaScore(A2A3PhysicaScore a2A3PhysicaScore)
    {
        return a2A3PhysicaScoreMapper.updateA2A3PhysicaScore(a2A3PhysicaScore);
    }

    /**
     * 批量删除A2 A3 体能成绩
     *
     * @param physIds 需要删除的A2 A3 体能成绩主键
     * @return 结果
     */
    @Override
    public int deleteA2A3PhysicaScoreByPhysIds(Long[] physIds)
    {
        return a2A3PhysicaScoreMapper.deleteA2A3PhysicaScoreByPhysIds(physIds);
    }

    /**
     * 删除A2 A3 体能成绩信息
     *
     * @param physId A2 A3 体能成绩主键
     * @return 结果
     */
    @Override
    public int deleteA2A3PhysicaScoreByPhysId(Long physId)
    {
        return a2A3PhysicaScoreMapper.deleteA2A3PhysicaScoreByPhysId(physId);
    }
}
