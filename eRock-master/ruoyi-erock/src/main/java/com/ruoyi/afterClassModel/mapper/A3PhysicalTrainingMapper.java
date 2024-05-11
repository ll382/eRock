package com.ruoyi.afterClassModel.mapper;

import java.util.List;
import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;
import com.ruoyi.afterClassModel.domain.A3WeeklyTraining;

/**
 * A3 体能训练周Mapper接口
 * 
 * @author heye
 * @date 2024-04-15
 */
public interface A3PhysicalTrainingMapper 
{
    /**
     * 查询A3 体能训练周
     * 
     * @param phtrId A3 体能训练周主键
     * @return A3 体能训练周
     */
    public A3PhysicalTraining selectA3PhysicalTrainingByPhtrId(Long phtrId);

    /**
     * 查询A3 体能训练周列表
     * 
     * @param a3PhysicalTraining A3 体能训练周
     * @return A3 体能训练周集合
     */
    public List<A3PhysicalTraining> selectA3PhysicalTrainingList(A3PhysicalTraining a3PhysicalTraining);

    /**
     * 新增A3 体能训练周
     * 
     * @param a3PhysicalTraining A3 体能训练周
     * @return 结果
     */
    public int insertA3PhysicalTraining(A3PhysicalTraining a3PhysicalTraining);

    /**
     * 修改A3 体能训练周
     * 
     * @param a3PhysicalTraining A3 体能训练周
     * @return 结果
     */
    public int updateA3PhysicalTraining(A3PhysicalTraining a3PhysicalTraining);

    /**
     * 删除A3 体能训练周
     * 
     * @param phtrId A3 体能训练周主键
     * @return 结果
     */
    public int deleteA3PhysicalTrainingByPhtrId(Long phtrId);

    /**
     * 批量删除A3 体能训练周
     * 
     * @param phtrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA3PhysicalTrainingByPhtrIds(Long[] phtrIds);

    /**
     * 批量删除A3 学生周训资源
     * 
     * @param phtrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA3WeeklyTrainingByPhtrIds(Long[] phtrIds);
    
    /**
     * 批量新增A3 学生周训资源
     * 
     * @param a3WeeklyTrainingList A3 学生周训资源列表
     * @return 结果
     */
    public int batchA3WeeklyTraining(List<A3WeeklyTraining> a3WeeklyTrainingList);
    

    /**
     * 通过A3 体能训练周主键删除A3 学生周训资源信息
     * 
     * @param phtrId A3 体能训练周ID
     * @return 结果
     */
    public int deleteA3WeeklyTrainingByPhtrId(Long phtrId);
}
