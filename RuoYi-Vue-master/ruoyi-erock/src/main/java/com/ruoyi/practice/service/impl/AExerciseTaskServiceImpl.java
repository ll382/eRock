package com.ruoyi.practice.service.impl;

import java.util.List;

import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.mapper.AExerciseTaskMapper;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.service.IAExerciseTaskService;

/**
 * 练习、测试任务表Service业务层处理
 *
 * @author houq
 * @date 2024-04-09
 */
@Service
public class AExerciseTaskServiceImpl implements IAExerciseTaskService
{
    @Autowired
    private AExerciseTaskMapper aExerciseTaskMapper;


    /**
     * 查询练习、测试任务表
     *
     * @param etId 练习、测试任务表主键
     * @return 练习、测试任务表
     */
    @Override
    public AExerciseTask selectAExerciseTaskByEtId(Long etId)
    {
        return aExerciseTaskMapper.selectAExerciseTaskByEtId(etId);
    }

    /**
     * 查询练习、测试任务表列表
     *
     * @param aExerciseTask 练习、测试任务表
     * @return 练习、测试任务表
     */
    @Override
    public List<AExerciseTask> selectAExerciseTaskList(AExerciseTask aExerciseTask)
    {
        return aExerciseTaskMapper.selectAExerciseTaskList(aExerciseTask);
    }

    /**
     * 新增练习、测试任务表
     *
     * @param aExerciseTask 练习、测试任务表
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAExerciseTask(AExerciseTask aExerciseTask)
    {
        int rows = aExerciseTaskMapper.insertAExerciseTask(aExerciseTask);
        insertAMarkSheet(aExerciseTask);
        return rows;
    }

    /**
     * 修改练习、测试任务表
     *
     * @param aExerciseTask 练习、测试任务表
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAExerciseTask(AExerciseTask aExerciseTask)
    {
        aExerciseTaskMapper.deleteAMarkSheetByEtId(aExerciseTask.getEtId());
        insertAMarkSheet(aExerciseTask);
        return aExerciseTaskMapper.updateAExerciseTask(aExerciseTask);
    }

    /**
     * 批量删除练习、测试任务表
     *
     * @param etIds 需要删除的练习、测试任务表主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAExerciseTaskByEtIds(Long[] etIds)
    {
        aExerciseTaskMapper.deleteAMarkSheetByEtIds(etIds);
        return aExerciseTaskMapper.deleteAExerciseTaskByEtIds(etIds);
    }

    /**
     * 删除练习、测试任务表信息
     *
     * @param etId 练习、测试任务表主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAExerciseTaskByEtId(Long etId)
    {
        aExerciseTaskMapper.deleteAMarkSheetByEtId(etId);
        return aExerciseTaskMapper.deleteAExerciseTaskByEtId(etId);
    }

    /**
     * 新增练习、测试评分表信息
     *
     * @param aExerciseTask 练习、测试任务表对象
     */
    public void insertAMarkSheet(AExerciseTask aExerciseTask)
    {
        List<AMarkSheet> aMarkSheetList = aExerciseTask.getAMarkSheetList();
        Long etId = aExerciseTask.getEtId();
        if (StringUtils.isNotNull(aMarkSheetList))
        {
            List<AMarkSheet> list = new ArrayList<AMarkSheet>();
            for (AMarkSheet aMarkSheet : aMarkSheetList)
            {
                aMarkSheet.setEtId(etId);
                list.add(aMarkSheet);
            }
            if (list.size() > 0)
            {
                aExerciseTaskMapper.batchAMarkSheet(list);
            }
        }
    }
}
