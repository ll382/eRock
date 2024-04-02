package com.ruoyi.teachingExchange.service;

import java.util.List;
import com.ruoyi.teachingExchange.domain.LessonUnits;
import com.ruoyi.teachingExchange.domain.TeachingTable;

/**
 * 课时单元Service接口
 *
 * @author heye
 * @date 2024-04-01
 */
public interface ILessonUnitsService
{
    /**
     * 查询课时单元
     *
     * @param lesId 课时单元主键
     * @return 课时单元
     */
    public LessonUnits selectLessonUnitsByLesId(Long lesId);

    /**
     * 查询课时单元列表
     *
     * @param lessonUnits 课时单元
     * @return 课时单元集合
     */
    public List<LessonUnits> selectLessonUnitsList(LessonUnits lessonUnits);

    /**
     * 新增课时单元
     *
     * @param lessonUnits 课时单元
     * @return 结果
     */
    public int insertLessonUnits(LessonUnits lessonUnits);

    /**
     * 修改课时单元
     *
     * @param lessonUnits 课时单元
     * @return 结果
     */
    public int updateLessonUnits(LessonUnits lessonUnits);

    /**
     * 批量删除课时单元
     *
     * @param lesIds 需要删除的课时单元主键集合
     * @return 结果
     */
    public int deleteLessonUnitsByLesIds(Long[] lesIds);

    /**
     * 删除课时单元信息
     *
     * @param lesId 课时单元主键
     * @return 结果
     */
    public int deleteLessonUnitsByLesId(Long lesId);

    public  int insertUnitsTable(TeachingTable lessonUnits);
}
