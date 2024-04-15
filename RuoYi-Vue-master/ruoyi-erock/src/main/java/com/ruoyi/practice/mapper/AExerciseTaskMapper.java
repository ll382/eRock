package com.ruoyi.practice.mapper;

import java.util.List;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;
import org.apache.ibatis.annotations.Param;

/**
 * 练习、测试任务表Mapper接口
 *
 * @author houq
 * @date 2024-04-09
 */
public interface AExerciseTaskMapper
{
    /**
     * 查询练习、测试任务表
     *
     * @param etId 练习、测试任务表主键
     * @return 练习、测试任务表
     */
    public AExerciseTask selectAExerciseTaskByEtId(@Param("etId") Long etId);

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
     * 删除练习、测试任务表
     *
     * @param etId 练习、测试任务表主键
     * @return 结果
     */
    public int deleteAExerciseTaskByEtId(Long etId);

    /**
     * 批量删除练习、测试任务表
     *
     * @param etIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAExerciseTaskByEtIds(Long[] etIds);

    /**
     * 批量删除练习、测试评分表
     *
     * @param etIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAMarkSheetByEtIds(Long[] etIds);

    /**
     * 批量新增练习、测试评分表
     *
     * @param aMarkSheetList 练习、测试评分表列表
     * @return 结果
     */
    public int batchAMarkSheet(List<AMarkSheet> aMarkSheetList);


    /**
     * 通过练习、测试任务表主键删除练习、测试评分表信息
     *
     * @param etId 练习、测试任务表ID
     * @return 结果
     */
    public int deleteAMarkSheetByEtId(Long etId);
}
