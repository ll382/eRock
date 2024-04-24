package com.ruoyi.practice.service;

import java.util.List;
import com.ruoyi.practice.domain.AExerciseTask;

/**
 * 练习、测试任务表Service接口
 *
 * @author houq
 * @date 2024-04-09
 */
public interface IAExerciseTaskService
{
    /**
     * 查询练习、测试任务表
     *
     * @param etId 练习、测试任务表主键
     * @return 练习、测试任务表
     */
    public AExerciseTask selectAExerciseTaskByEtId(Long etId);

    /**
     * 查询练习、测试任务表列表
     *
     * @param aExerciseTask 练习、测试任务表
     * @return 练习、测试任务表集合
     */
    public List<AExerciseTask> selectAExerciseTaskList(AExerciseTask aExerciseTask);

    /**
     * 新增练习、测试任务表
     *
     * @param aExerciseTask 练习、测试任务表
     * @return 结果
     */
    public int insertAExerciseTask(AExerciseTask aExerciseTask);

    /**
     * 修改练习、测试任务表
     *
     * @param aExerciseTask 练习、测试任务表
     * @return 结果
     */
    public int updateAExerciseTask(AExerciseTask aExerciseTask);

    /**
     * 批量删除练习、测试任务表
     *
     * @param etIds 需要删除的练习、测试任务表主键集合
     * @return 结果
     */
    public int deleteAExerciseTaskByEtIds(Long[] etIds);

    /**
     * 删除练习、测试任务表信息
     *
     * @param etId 练习、测试任务表主键
     * @return 结果
     */
    public int deleteAExerciseTaskByEtId(Long etId);
}
