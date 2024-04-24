package com.ruoyi.practice.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStudentService;
import com.ruoyi.practice.mapper.AExerciseResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.practice.domain.AExerciseResource;
import com.ruoyi.practice.service.IAExerciseResourceService;

/**
 * 练习资源表Service业务层处理
 *
 * @author houq
 * @date 2024-03-19
 */
@Service
public class AExerciseResourceServiceImpl implements IAExerciseResourceService
{
    @Autowired
    private AExerciseResourceMapper aExerciseResourceMapper;


    /**
     * 查询练习资源表
     *
     * @param erId 练习资源表主键
     * @return 练习资源表
     */
    @Override
    public AExerciseResource selectAExerciseResourceByErId(Long erId)
    {
        return aExerciseResourceMapper.selectAExerciseResourceByErId(erId);
    }

    /**
     * 查询练习资源表列表
     *
     * @param aExerciseResource 练习资源表
     * @return 练习资源表
     */
    @Override
    public List<AExerciseResource> selectAExerciseResourceList(AExerciseResource aExerciseResource)
    {
        return aExerciseResourceMapper.selectAExerciseResourceList(aExerciseResource);
    }

    /**
     * 新增练习资源表
     *
     * @param aExerciseResource 练习资源表
     * @return 结果
     */
    @Override
    public int insertAExerciseResource(AExerciseResource aExerciseResource)
    {
        aExerciseResource.setErTime(new Date());
        return aExerciseResourceMapper.insertAExerciseResource(aExerciseResource);
    }

    /**
     * 修改练习资源表
     *
     * @param aExerciseResource 练习资源表
     * @return 结果
     */
    @Override
    public int updateAExerciseResource(AExerciseResource aExerciseResource)
    {
        return aExerciseResourceMapper.updateAExerciseResource(aExerciseResource);
    }

    /**
     * 批量删除练习资源表
     *
     * @param erIds 需要删除的练习资源表主键
     * @return 结果
     */
    @Override
    public int deleteAExerciseResourceByErIds(Long[] erIds)
    {
        return aExerciseResourceMapper.deleteAExerciseResourceByErIds(erIds);
    }

    /**
     * 删除练习资源表信息
     *
     * @param erId 练习资源表主键
     * @return 结果
     */
    @Override
    public int deleteAExerciseResourceByErId(Long erId)
    {
        return aExerciseResourceMapper.deleteAExerciseResourceByErId(erId);
    }
}
