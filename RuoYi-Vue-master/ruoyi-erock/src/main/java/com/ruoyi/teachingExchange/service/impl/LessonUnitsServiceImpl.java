package com.ruoyi.teachingExchange.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.teachingExchange.mapper.TeachingTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.teachingExchange.domain.TeachingTable;
import com.ruoyi.teachingExchange.mapper.LessonUnitsMapper;
import com.ruoyi.teachingExchange.domain.LessonUnits;
import com.ruoyi.teachingExchange.service.ILessonUnitsService;

/**
 * 课时单元Service业务层处理
 *
 * @author heye
 * @date 2024-04-01
 */
@Service
public class LessonUnitsServiceImpl implements ILessonUnitsService
{
    @Autowired
    private LessonUnitsMapper lessonUnitsMapper;

    @Autowired
    private TeachingTableMapper teachingTableMapper;

    /**
     * 查询课时单元
     *
     * @param lesId 课时单元主键
     * @return 课时单元
     */
    @Override
    public LessonUnits selectLessonUnitsByLesId(Long lesId)
    {
        return lessonUnitsMapper.selectLessonUnitsByLesId(lesId);
    }

    /**
     * 查询课时单元列表
     *
     * @param lessonUnits 课时单元
     * @return 课时单元
     */
    @Override
    public List<LessonUnits> selectLessonUnitsList(LessonUnits lessonUnits)
    {
        return lessonUnitsMapper.selectLessonUnitsList(lessonUnits);
    }

    /**
     * 新增课时单元
     *
     * @param lessonUnits 课时单元
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLessonUnits(LessonUnits lessonUnits)
    {
//        给出排序
        List<Long> bd = new ArrayList<>();
        if (lessonUnits.getLesId() == 0) {
            bd.add(lessonUnitsMapper.batchLessonUnitsLesList());
        } else {
            bd = lessonUnitsMapper.batchLessonUnitsByLesId(lessonUnits.getLesId());
        }
        Long bd1 = bd.get(0);
        Long bd2 = 0L;
//        判断是否是最后一个排序，如果是，加100
        if (bd.size() == 1) {
            lessonUnits.setLesOrderId(bd1 + 100L);
        }else {
//            不是则讲其和后面顺序的值之差的一半与自生相加
            bd2 = bd.get(1);
            lessonUnits.setLesOrderId(bd1 + ((bd2 - bd1)/2));
        }
        return lessonUnitsMapper.insertLessonUnits(lessonUnits);
    }

    /**
     * 修改课时单元
     *
     * @param lessonUnits 课时单元
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLessonUnits(LessonUnits lessonUnits)
    {
//        lessonUnitsMapper.deleteTeachingTableByLesId(lessonUnits.getLesId());
//        insertTeachingTable(lessonUnits);
        return lessonUnitsMapper.updateLessonUnits(lessonUnits);
    }

    /**
     * 批量删除课时单元
     *
     * @param lesIds 需要删除的课时单元主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLessonUnitsByLesIds(Long[] lesIds)
    {
        lessonUnitsMapper.deleteTeachingTableByLesIds(lesIds);
        return lessonUnitsMapper.deleteLessonUnitsByLesIds(lesIds);
    }

    /**
     * 删除课时单元信息
     *
     * @param lesId 课时单元主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLessonUnitsByLesId(Long lesId)
    {
        lessonUnitsMapper.selectByLesId(lesId).forEach(teachingId -> {
//            删除观看时常
            teachingTableMapper.deleteViewedTeachingId(teachingId);
//            删除评论
            teachingTableMapper.deleteA1CommunicationByTeachingId(teachingId);
        });
//        删除课时
        lessonUnitsMapper.deleteTeachingTableByLesId(lesId);
//        删除单元
        return lessonUnitsMapper.deleteLessonUnitsByLesId(lesId);
    }

    /**
     * 新增A1 线上学习学生线上观看记录表信息
     *
     * @param lessonUnits 课时单元对象
     */
    public void insertTeachingTable(LessonUnits lessonUnits)
    {
        List<TeachingTable> teachingTableList = lessonUnits.getTeachingTableList();
        Long lesId = lessonUnits.getLesId();
        if (StringUtils.isNotNull(teachingTableList))
        {
            List<TeachingTable> list = new ArrayList<TeachingTable>();
            for (TeachingTable teachingTable : teachingTableList)
            {
                teachingTable.setLesId(lesId);
                list.add(teachingTable);
            }
            if (list.size() > 0)
            {
                lessonUnitsMapper.batchTeachingTable(list);
            }
        }
    }

    /**
     * 新增A1 线上学习学生线上观看记录表信息
     *
     * @param teachingTableList 课时单元对象
     */
    public int insertUnitsTable(TeachingTable teachingTableList)
    {
//        增加时间
        teachingTableList.setCreatedAt(new Date());
//        给出排序
        List<BigDecimal> bd = new ArrayList<BigDecimal>();
        if (teachingTableList.getTeachingId().equals(0L)) {
            bd.add(lessonUnitsMapper.batchTeachingTableLesList());
        }else {
            bd = lessonUnitsMapper.batchTeachingTableByLesId(teachingTableList.getTeachingId());
        }
        BigDecimal bd1 = bd.get(0);
        BigDecimal bd2 = BigDecimal.ZERO;
//        判断是否是最后一个排序，如果是，加100
        if (bd.size() == 1) {
            teachingTableList.setTeachingOrder(bd1.add(BigDecimal.valueOf(100)));
        } else {
            bd2 = bd.get(1);
            teachingTableList.setTeachingOrder(bd1.add(bd2.subtract(bd1).divide(BigDecimal.valueOf(2))));
        }
        return lessonUnitsMapper.batchUnitsTable(teachingTableList);
    }
}
