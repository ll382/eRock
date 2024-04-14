package com.ruoyi.InClassModule.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.InClassModule.domain.A2A3PhysicaScore;
import com.ruoyi.InClassModule.mapper.A2A3PhysicalTestMapper;
import com.ruoyi.InClassModule.domain.A2A3PhysicalTest;
import com.ruoyi.InClassModule.service.IA2A3PhysicalTestService;

/**
 * A2 A3体能测试Service业务层处理
 *
 * @author heye
 * @date 2024-04-14
 */
@Service
public class A2A3PhysicalTestServiceImpl implements IA2A3PhysicalTestService
{
    @Autowired
    private A2A3PhysicalTestMapper a2A3PhysicalTestMapper;

    @Autowired
    SelectUser selectUser;

    /**
     * 查询A2 A3体能测试
     *
     * @param enumId A2 A3体能测试主键
     * @return A2 A3体能测试
     */
    @Override
    public A2A3PhysicalTest selectA2A3PhysicalTestByPhyId(Long enumId,Long crId)
    {
//        查询基础数据
        A2A3PhysicalTest physical = a2A3PhysicalTestMapper.selectA2A3PhysicalTestByPhyId(enumId,crId);
//        查内部学生
        physical.setA2A3PhysicaScoreList(selectUser.selectStudent(physical.getA2A3PhysicaScoreList()));
//        查外部老师并直接返回内容
        return (A2A3PhysicalTest) selectUser.selectTeacher(physical);
    }

    /**
     * 查询A2 A3体能测试列表
     *
     * @param a2A3PhysicalTest A2 A3体能测试
     * @return A2 A3体能测试
     */
    @Override
    public List<A2A3PhysicalTest> selectA2A3PhysicalTestList(A2A3PhysicalTest a2A3PhysicalTest)
    {
        return selectUser.selectTeacher(a2A3PhysicalTestMapper.selectA2A3PhysicalTestList(a2A3PhysicalTest));
    }

    /**
     * 新增A2 A3体能测试
     *
     * @param a2A3PhysicalTest A2 A3体能测试
     * @return 结果
     */
    @Transactional
    @Override
    public int insertA2A3PhysicalTest(A2A3PhysicalTest a2A3PhysicalTest)
    {
//        设置创建时间
        a2A3PhysicalTest.setPhyTime(new Date());
        int rows = a2A3PhysicalTestMapper.insertA2A3PhysicalTest(a2A3PhysicalTest);
        insertA2A3PhysicaScore(a2A3PhysicalTest);
        return rows;
    }

    /**
     * 修改A2 A3体能测试
     *
     * @param a2A3PhysicalTest A2 A3体能测试
     * @return 结果
     */
    @Transactional
    @Override
    public int updateA2A3PhysicalTest(A2A3PhysicalTest a2A3PhysicalTest)
    {
        a2A3PhysicalTestMapper.deleteA2A3PhysicaScoreByPhyId(a2A3PhysicalTest.getPhyId());
        insertA2A3PhysicaScore(a2A3PhysicalTest);
        return a2A3PhysicalTestMapper.updateA2A3PhysicalTest(a2A3PhysicalTest);
    }

    /**
     * 批量删除A2 A3体能测试
     *
     * @param phyIds 需要删除的A2 A3体能测试主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteA2A3PhysicalTestByPhyIds(Long[] phyIds)
    {
        a2A3PhysicalTestMapper.deleteA2A3PhysicaScoreByPhyIds(phyIds);
        return a2A3PhysicalTestMapper.deleteA2A3PhysicalTestByPhyIds(phyIds);
    }

    /**
     * 删除A2 A3体能测试信息
     *
     * @param phyId A2 A3体能测试主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteA2A3PhysicalTestByPhyId(Long phyId)
    {
        a2A3PhysicalTestMapper.deleteA2A3PhysicaScoreByPhyId(phyId);
        return a2A3PhysicalTestMapper.deleteA2A3PhysicalTestByPhyId(phyId);
    }

    /**
     * 新增A2 A3 体能成绩信息
     *
     * @param a2A3PhysicalTest A2 A3体能测试对象
     */
    public void insertA2A3PhysicaScore(A2A3PhysicalTest a2A3PhysicalTest)
    {
        List<A2A3PhysicaScore> a2A3PhysicaScoreList = a2A3PhysicalTest.getA2A3PhysicaScoreList();
        Long phyId = a2A3PhysicalTest.getPhyId();
        if (StringUtils.isNotNull(a2A3PhysicaScoreList))
        {
            List<A2A3PhysicaScore> list = new ArrayList<A2A3PhysicaScore>();
            for (A2A3PhysicaScore a2A3PhysicaScore : a2A3PhysicaScoreList)
            {
                a2A3PhysicaScore.setPhyId(phyId);
                list.add(a2A3PhysicaScore);
            }
            if (list.size() > 0)
            {
                a2A3PhysicalTestMapper.batchA2A3PhysicaScore(list);
            }
        }
    }
}
