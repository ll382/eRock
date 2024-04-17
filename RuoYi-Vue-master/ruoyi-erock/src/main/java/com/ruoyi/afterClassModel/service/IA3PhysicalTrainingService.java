package com.ruoyi.afterClassModel.service;

import java.util.List;
import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;

/**
 * A3 体能训练周Service接口
 * 
 * @author heye
 * @date 2024-04-15
 */
public interface IA3PhysicalTrainingService 
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
     * 批量删除A3 体能训练周
     * 
     * @param phtrIds 需要删除的A3 体能训练周主键集合
     * @return 结果
     */
    public int deleteA3PhysicalTrainingByPhtrIds(Long[] phtrIds);

    /**
     * 删除A3 体能训练周信息
     * 
     * @param phtrId A3 体能训练周主键
     * @return 结果
     */
    public int deleteA3PhysicalTrainingByPhtrId(Long phtrId);
}
