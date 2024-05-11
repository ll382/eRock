package com.ruoyi.InClassModule.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2A3PhysicaScoreMapper;
import com.ruoyi.InClassModule.domain.A2A3PhysicaScore;
import com.ruoyi.InClassModule.service.IA2A3PhysicaScoreService;
import org.springframework.transaction.annotation.Transactional;

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
        return selectUser.selectStudent(a2A3PhysicaScoreMapper.selectA2A3PhysicaScoreByPhysId(phyId));
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
    @Transactional
    public int insertA2A3PhysicaScore(A2A3PhysicaScore a2A3PhysicaScore)
    {
        A2A3PhysicaScore physicaScore = new A2A3PhysicaScore();
        physicaScore.setStuId(a2A3PhysicaScore.getStuId());
        physicaScore.setPhyId(a2A3PhysicaScore.getPhyId());
        List<A2A3PhysicaScore> a2A3PhysicaScores = a2A3PhysicaScoreMapper.selectA2A3PhysicaScoreList(physicaScore);
        if (StringUtils.isNotEmpty(a2A3PhysicaScores)) {
            return 0;
        }else {
//            插入数据
            int a3PhysicaScore = a2A3PhysicaScoreMapper.insertA2A3PhysicaScore(a2A3PhysicaScore);
//            计算分数
            BigDecimal score = calculateScore(a2A3PhysicaScore);
            AModuleScore aModuleScore = new AModuleScore();
            aModuleScore.setFitnessTests2(score);
//            更新分数
            int i = selectUser.updateStudentAScore(aModuleScore, a2A3PhysicaScore.getStuId());
            System.out.println(i);
            return a3PhysicaScore;
        }
    }

    /**
     * 修改A2 A3 体能成绩
     *
     * @param a2A3PhysicaScore A2 A3 体能成绩
     * @return 结果
     */
    @Override
    @Transactional
    public int updateA2A3PhysicaScore(A2A3PhysicaScore a2A3PhysicaScore)
    {
        int u = a2A3PhysicaScoreMapper.updateA2A3PhysicaScore(a2A3PhysicaScore);
        if (u == 0) {
            return 0;
        }
        BigDecimal score = calculateScore(a2A3PhysicaScore);
        AModuleScore aModuleScore = new AModuleScore();
        aModuleScore.setFitnessTests2(score);
        int i = selectUser.updateStudentAScore(aModuleScore, a2A3PhysicaScore.getStuId());
        System.out.println(i);
        return u;
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

    public BigDecimal calculateScore (A2A3PhysicaScore a2A3PhysicaScore) {
//        获取所有学生的体能成绩
        A2A3PhysicaScore physicaScore = new A2A3PhysicaScore();
        physicaScore.setStuId(a2A3PhysicaScore.getStuId());
        List<A2A3PhysicaScore> a2A3PhysicaScores = a2A3PhysicaScoreMapper.selectA2A3PhysicaScoreList(physicaScore);
//        获取所有学生的体能成绩
        System.out.println("获取所有学生的体能成绩");
        List<BigDecimal> stuPhysSpeedIds = a2A3PhysicaScores.stream().map(A2A3PhysicaScore::getPhysSpeed2).toList();
        System.out.println(stuPhysSpeedIds);
        List<BigDecimal> stuPhysStaminaIds = a2A3PhysicaScores.stream().map(A2A3PhysicaScore::getPhysStamina2).toList();
        System.out.println(stuPhysStaminaIds);
//        计算总和
        System.out.println("计算总和");
        BigDecimal stuPhysSpeed = stuPhysSpeedIds.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(stuPhysSpeed);
        BigDecimal stuPhysStamina = stuPhysStaminaIds.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(stuPhysStamina);
//        计算平均值
        BigDecimal dividePhysSpeed = BigDecimal.ZERO;
        if (stuPhysSpeedIds.size() > 0) {
            dividePhysSpeed = stuPhysSpeed.divide(BigDecimal.valueOf(stuPhysSpeedIds.size()), 2, RoundingMode.HALF_UP);
        }
        System.out.println(dividePhysSpeed);

        BigDecimal dividePhysStamina = BigDecimal.ZERO;
        if (stuPhysStaminaIds.size() > 0) {
            dividePhysStamina = stuPhysStamina.divide(BigDecimal.valueOf(stuPhysStaminaIds.size()), 2, RoundingMode.HALF_UP);
        }
        System.out.println(dividePhysStamina);
//        计算总分
        System.out.println("计算总分");
        BigDecimal endScore = dividePhysSpeed.add(dividePhysStamina);
//        20代表体能成绩为200的总分
        endScore = endScore.divide(BigDecimal.valueOf(20), 2, RoundingMode.HALF_UP);
        System.out.println(endScore);
        return endScore;
    }
}
