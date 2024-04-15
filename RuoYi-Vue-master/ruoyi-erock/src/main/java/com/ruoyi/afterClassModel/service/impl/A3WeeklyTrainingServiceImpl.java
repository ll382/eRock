package com.ruoyi.afterClassModel.service.impl;

import java.util.List;

import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.afterClassModel.mapper.A3WeeklyTrainingMapper;
import com.ruoyi.afterClassModel.domain.A3WeeklyTraining;
import com.ruoyi.afterClassModel.service.IA3WeeklyTrainingService;

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
    SelectUser selectUser;
    /**
     * 查询A3 学生周训资源
     *
     * @param stuId A3 学生周训资源主键
     * @return A3 学生周训资源
     */
    @Override
    public List<A3WeeklyTraining> selectA3WeeklyTrainingByWetrId(Long stuId)
    {
        return selectUser.selectStudent(a3WeeklyTrainingMapper.selectA3WeeklyTrainingByWetrId(stuId));
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
        return a3WeeklyTrainingMapper.selectA3WeeklyTrainingList(a3WeeklyTraining);
    }

    /**
     * 新增A3 学生周训资源
     *
     * @param a3WeeklyTraining A3 学生周训资源
     * @return 结果
     */
    @Override
    public int insertA3WeeklyTraining(A3WeeklyTraining a3WeeklyTraining)
    {
        return a3WeeklyTrainingMapper.insertA3WeeklyTraining(a3WeeklyTraining);
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
        return a3WeeklyTrainingMapper.updateA3WeeklyTraining(a3WeeklyTraining);
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
}
