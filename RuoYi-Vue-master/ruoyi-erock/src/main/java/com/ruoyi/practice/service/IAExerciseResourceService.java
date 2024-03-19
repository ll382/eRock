package com.ruoyi.practice.service;

import java.util.List;
import com.ruoyi.practice.domain.AExerciseResource;

/**
 * 练习资源表Service接口
 * 
 * @author houq
 * @date 2024-03-19
 */
public interface IAExerciseResourceService 
{
    /**
     * 查询练习资源表
     * 
     * @param erId 练习资源表主键
     * @return 练习资源表
     */
    public AExerciseResource selectAExerciseResourceByErId(Long erId);

    /**
     * 查询练习资源表列表
     * 
     * @param aExerciseResource 练习资源表
     * @return 练习资源表集合
     */
    public List<AExerciseResource> selectAExerciseResourceList(AExerciseResource aExerciseResource);

    /**
     * 新增练习资源表
     * 
     * @param aExerciseResource 练习资源表
     * @return 结果
     */
    public int insertAExerciseResource(AExerciseResource aExerciseResource);

    /**
     * 修改练习资源表
     * 
     * @param aExerciseResource 练习资源表
     * @return 结果
     */
    public int updateAExerciseResource(AExerciseResource aExerciseResource);

    /**
     * 批量删除练习资源表
     * 
     * @param erIds 需要删除的练习资源表主键集合
     * @return 结果
     */
    public int deleteAExerciseResourceByErIds(Long[] erIds);

    /**
     * 删除练习资源表信息
     * 
     * @param erId 练习资源表主键
     * @return 结果
     */
    public int deleteAExerciseResourceByErId(Long erId);
}
