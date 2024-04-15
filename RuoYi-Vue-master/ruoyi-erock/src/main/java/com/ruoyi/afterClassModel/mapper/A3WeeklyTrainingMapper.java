package com.ruoyi.afterClassModel.mapper;

import java.util.List;
import com.ruoyi.afterClassModel.domain.A3WeeklyTraining;

/**
 * A3 学生周训资源Mapper接口
 *
 * @author heye
 * @date 2024-04-15
 */
public interface A3WeeklyTrainingMapper
{
    /**
     * 查询A3 学生周训资源
     *
     * @param stuId A3 学生周训资源主键
     * @return A3 学生周训资源
     */
    public List<A3WeeklyTraining> selectA3WeeklyTrainingByWetrId(Long stuId);

    /**
     * 查询A3 学生周训资源列表
     *
     * @param a3WeeklyTraining A3 学生周训资源
     * @return A3 学生周训资源集合
     */
    public List<A3WeeklyTraining> selectA3WeeklyTrainingList(A3WeeklyTraining a3WeeklyTraining);

    /**
     * 新增A3 学生周训资源
     *
     * @param a3WeeklyTraining A3 学生周训资源
     * @return 结果
     */
    public int insertA3WeeklyTraining(A3WeeklyTraining a3WeeklyTraining);

    /**
     * 修改A3 学生周训资源
     *
     * @param a3WeeklyTraining A3 学生周训资源
     * @return 结果
     */
    public int updateA3WeeklyTraining(A3WeeklyTraining a3WeeklyTraining);

    /**
     * 删除A3 学生周训资源
     *
     * @param wetrId A3 学生周训资源主键
     * @return 结果
     */
    public int deleteA3WeeklyTrainingByWetrId(Long wetrId);

    /**
     * 批量删除A3 学生周训资源
     *
     * @param wetrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA3WeeklyTrainingByWetrIds(Long[] wetrIds);
}
