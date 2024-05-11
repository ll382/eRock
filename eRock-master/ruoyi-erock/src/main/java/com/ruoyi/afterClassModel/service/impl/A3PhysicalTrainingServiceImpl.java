package com.ruoyi.afterClassModel.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.core.service.SelectUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.afterClassModel.domain.A3WeeklyTraining;
import com.ruoyi.afterClassModel.mapper.A3PhysicalTrainingMapper;
import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;
import com.ruoyi.afterClassModel.service.IA3PhysicalTrainingService;

/**
 * A3 体能训练周Service业务层处理
 *
 * @author heye
 * @date 2024-04-15
 */
@Service
public class A3PhysicalTrainingServiceImpl implements IA3PhysicalTrainingService
{
    @Autowired
    private A3PhysicalTrainingMapper a3PhysicalTrainingMapper;

    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A3 体能训练周
     *
     * @param phtrId A3 体能训练周主键
     * @return A3 体能训练周
     */
    @Override
    public A3PhysicalTraining selectA3PhysicalTrainingByPhtrId(Long phtrId)
    {
        BaseEntity a3PhysicalTraining = selectUser.selectStudent(a3PhysicalTrainingMapper.selectA3PhysicalTrainingByPhtrId(phtrId));
        return (A3PhysicalTraining)selectUser.selectTeacher(a3PhysicalTraining);
    }

    /**
     * 查询A3 体能训练周列表
     *
     * @param a3PhysicalTraining A3 体能训练周
     * @return A3 体能训练周
     */
    @Override
    public List<A3PhysicalTraining> selectA3PhysicalTrainingList(A3PhysicalTraining a3PhysicalTraining)
    {
        return selectUser.selectTeacher(a3PhysicalTrainingMapper.selectA3PhysicalTrainingList(a3PhysicalTraining));
    }

    /**
     * 新增A3 体能训练周
     *
     * @param a3PhysicalTraining A3 体能训练周
     * @return 结果
     */
    @Transactional
    @Override
    public int insertA3PhysicalTraining(A3PhysicalTraining a3PhysicalTraining)
    {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        a3PhysicalTraining.setPhtrStartTime(currentDate);
        // 将当前时间加七天
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date futureDate = calendar.getTime();
        a3PhysicalTraining.setPhtrEndTime(futureDate);
        int rows = a3PhysicalTrainingMapper.insertA3PhysicalTraining(a3PhysicalTraining);
        insertA3WeeklyTraining(a3PhysicalTraining);
        return rows;
    }


    /**
     * 修改A3 体能训练周
     *
     * @param a3PhysicalTraining A3 体能训练周
     * @return 结果
     */
    @Transactional
    @Override
    public int updateA3PhysicalTraining(A3PhysicalTraining a3PhysicalTraining)
    {
        a3PhysicalTrainingMapper.deleteA3WeeklyTrainingByPhtrId(a3PhysicalTraining.getPhtrId());
        insertA3WeeklyTraining(a3PhysicalTraining);
        return a3PhysicalTrainingMapper.updateA3PhysicalTraining(a3PhysicalTraining);
    }

    /**
     * 批量删除A3 体能训练周
     *
     * @param phtrIds 需要删除的A3 体能训练周主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteA3PhysicalTrainingByPhtrIds(Long[] phtrIds)
    {
        a3PhysicalTrainingMapper.deleteA3WeeklyTrainingByPhtrIds(phtrIds);
        return a3PhysicalTrainingMapper.deleteA3PhysicalTrainingByPhtrIds(phtrIds);
    }

    /**
     * 删除A3 体能训练周信息
     *
     * @param phtrId A3 体能训练周主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteA3PhysicalTrainingByPhtrId(Long phtrId)
    {
        a3PhysicalTrainingMapper.deleteA3WeeklyTrainingByPhtrId(phtrId);
        return a3PhysicalTrainingMapper.deleteA3PhysicalTrainingByPhtrId(phtrId);
    }

    /**
     * 新增A3 学生周训资源信息
     *
     * @param a3PhysicalTraining A3 体能训练周对象
     */
    public void insertA3WeeklyTraining(A3PhysicalTraining a3PhysicalTraining)
    {
        List<A3WeeklyTraining> a3WeeklyTrainingList = a3PhysicalTraining.getA3WeeklyTrainingList();
        Long phtrId = a3PhysicalTraining.getPhtrId();
        if (StringUtils.isNotNull(a3WeeklyTrainingList))
        {
            List<A3WeeklyTraining> list = new ArrayList<A3WeeklyTraining>();
            for (A3WeeklyTraining a3WeeklyTraining : a3WeeklyTrainingList)
            {
                a3WeeklyTraining.setPhtrId(phtrId);
                list.add(a3WeeklyTraining);
            }
            if (list.size() > 0)
            {
                a3PhysicalTrainingMapper.batchA3WeeklyTraining(list);
            }
        }
    }
}
