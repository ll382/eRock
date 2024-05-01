package com.ruoyi.afterClassModel.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.InClassModule.domain.A2A3PhysicaScore;
import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;
import com.ruoyi.afterClassModel.mapper.A3PhysicalTrainingMapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.service.SelectUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.afterClassModel.mapper.A3WeeklyTrainingMapper;
import com.ruoyi.afterClassModel.domain.A3WeeklyTraining;
import com.ruoyi.afterClassModel.service.IA3WeeklyTrainingService;
import org.springframework.transaction.annotation.Transactional;

/**
 * A3 学生周训资源Service业务层处理
 *
 * @author heye
 * @date 2024-04-15
 */
@Service
public class A3WeeklyTrainingServiceImpl implements IA3WeeklyTrainingService
{
    @Autowired
    private A3WeeklyTrainingMapper a3WeeklyTrainingMapper;

    @Autowired
    private A3PhysicalTrainingMapper a3PhysicalTrainingMapper;

    @Autowired
    SelectUser selectUser;
    /**
     * 查询A3 学生周训资源
     *
     * @param phtrId A3 学生周训资源主键
     * @return A3 学生周训资源
     */
    @Override
    public List<A3WeeklyTraining> selectA3WeeklyTrainingByWetrId(Long phtrId)
    {
        return a3WeeklyTrainingMapper.selectA3WeeklyTrainingByWetrId(phtrId);
    }

    /**
     * 查询A3 学生周训资源列表
     *
     * @param a3WeeklyTraining A3 学生周训资源
     * @return A3 学生周训资源
     */
    @Override
    public List<A3WeeklyTraining> selectA3WeeklyTrainingList(A3WeeklyTraining a3WeeklyTraining)
    {
        return selectUser.selectStudent(a3WeeklyTrainingMapper.selectA3WeeklyTrainingList(a3WeeklyTraining));
    }

    /**
     * 新增A3 学生周训资源
     *
     * @param a3WeeklyTraining A3 学生周训资源
     * @return 结果
     */
    @Transactional
    @Override
    public int insertA3WeeklyTraining(A3WeeklyTraining a3WeeklyTraining)
    {
//        设置提交时间
        a3WeeklyTraining.setWetrTime(new Date());
//        判断是否在当前时间内提交的
        A3PhysicalTraining training = a3PhysicalTrainingMapper.selectA3PhysicalTrainingByPhtrId(a3WeeklyTraining.getPhtrId());
        if (StringUtils.isNull(training)) {
            return -2;
        }else {
//            判断是否在训练时间内提交的
            Date phtrStartTime = training.getPhtrStartTime();
            Date phtrEndTime = training.getPhtrEndTime();
            Date wetrTime = a3WeeklyTraining.getWetrTime();
            System.out.println(wetrTime.before(phtrStartTime) && wetrTime.after(phtrEndTime));
            if (wetrTime.before(phtrStartTime) && wetrTime.after(phtrEndTime)) {
                return -1;
            }

        }

//        判断当天是否有重复提交
        A3WeeklyTraining a = new A3WeeklyTraining();
        a.setStuId(a3WeeklyTraining.getStuId());
        a.setPhtrId(a3WeeklyTraining.getPhtrId());
        List<A3WeeklyTraining> a3WeeklyTrainings = a3WeeklyTrainingMapper.selectA3WeeklyTrainingList(a);
        for (A3WeeklyTraining time : a3WeeklyTrainings) {
            long l = a3WeeklyTraining.getWetrTime().getTime() - time.getWetrTime().getTime();
            if (l < 86400) {
                return 0;
            }
        };
        if (a3WeeklyTrainings.size() <= 3){
//            未交三次周训，且不是同一天
            int i = a3WeeklyTrainingMapper.insertA3WeeklyTraining(a3WeeklyTraining);;
//            计算学生分数
            BigDecimal score = calculateScore(a3WeeklyTraining);
//            设置学生分数
            AModuleScore aModuleScore = new AModuleScore();
            aModuleScore.setFitnessTests3(score);
//            更新学生分数
            int updateStudentAScore = selectUser.updateStudentAScore(aModuleScore, a3WeeklyTraining.getStuId());
            System.out.println(updateStudentAScore);
            return i;
        } else {
            return -10;
        }

    }

    /**
     * 修改A3 学生周训资源
     *
     * @param a3WeeklyTraining A3 学生周训资源
     * @return 结果
     */
    @Override
    public int updateA3WeeklyTraining(A3WeeklyTraining a3WeeklyTraining)
    {
        int i = a3WeeklyTrainingMapper.updateA3WeeklyTraining(a3WeeklyTraining);
        if (i == 0) {
            return 0;
        }
//            计算学生分数
        BigDecimal score = calculateScore(a3WeeklyTraining);
//            设置学生分数
        AModuleScore aModuleScore = new AModuleScore();
        aModuleScore.setFitnessTests3(score);
//            更新学生分数
        int updateStudentAScore = selectUser.updateStudentAScore(aModuleScore, a3WeeklyTraining.getStuId());
        System.out.println(updateStudentAScore);
        return i;
    }

    /**
     * 批量删除A3 学生周训资源
     *
     * @param wetrIds 需要删除的A3 学生周训资源主键
     * @return 结果
     */
    @Override
    public int deleteA3WeeklyTrainingByWetrIds(Long[] wetrIds)
    {
        return a3WeeklyTrainingMapper.deleteA3WeeklyTrainingByWetrIds(wetrIds);
    }

    /**
     * 删除A3 学生周训资源信息
     *
     * @param wetrId A3 学生周训资源主键
     * @return 结果
     */
    @Override
    public int deleteA3WeeklyTrainingByWetrId(Long wetrId)
    {
        return a3WeeklyTrainingMapper.deleteA3WeeklyTrainingByWetrId(wetrId);
    }
    public BigDecimal calculateScore (A3WeeklyTraining a3WeeklyTraining)
    {
//            查询训练任务数
        List<A3PhysicalTraining> a3PhysicalTrainings = a3PhysicalTrainingMapper.selectA3PhysicalTrainingList(new A3PhysicalTraining());
//            任务数乘以每次必须打卡数
        double sized = a3PhysicalTrainings.size() * 3;
        System.out.println("任务数乘以每次必须打卡数");
        System.out.println(sized);
//            查询学生周训次数
        A3WeeklyTraining training = new A3WeeklyTraining();
        training.setStuId(a3WeeklyTraining.getStuId());
        List<A3WeeklyTraining> weeklyTrainings = a3WeeklyTrainingMapper.selectA3WeeklyTrainingList(training);
        double size = weeklyTrainings.size();
        System.out.println("查询学生周训次数");
        System.out.println(size);
//            计算学生周训次数所能得到的分数
        double sizeNumber = 2 / sized;
        System.out.println("计算学生周训次数所能得到的分数");
        System.out.println(sizeNumber * size);
        return BigDecimal.valueOf(sizeNumber * size);
    }
}
