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
 * ��ʱ��ԪServiceҵ��㴦��
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
     * ��ѯ��ʱ��Ԫ
     *
     * @param lesId ��ʱ��Ԫ����
     * @return ��ʱ��Ԫ
     */
    @Override
    public LessonUnits selectLessonUnitsByLesId(Long lesId)
    {
        return lessonUnitsMapper.selectLessonUnitsByLesId(lesId);
    }

    /**
     * ��ѯ��ʱ��Ԫ�б�
     *
     * @param lessonUnits ��ʱ��Ԫ
     * @return ��ʱ��Ԫ
     */
    @Override
    public List<LessonUnits> selectLessonUnitsList(LessonUnits lessonUnits)
    {
        return lessonUnitsMapper.selectLessonUnitsList(lessonUnits);
    }

    /**
     * ������ʱ��Ԫ
     *
     * @param lessonUnits ��ʱ��Ԫ
     * @return ���
     */
    @Transactional
    @Override
    public int insertLessonUnits(LessonUnits lessonUnits)
    {
//        ��������
        List<Long> bd = new ArrayList<>();
        if (lessonUnits.getLesId() == 0) {
            bd.add(lessonUnitsMapper.batchLessonUnitsLesList());
        } else {
            bd = lessonUnitsMapper.batchLessonUnitsByLesId(lessonUnits.getLesId());
        }
        Long bd1 = bd.get(0);
        Long bd2 = 0L;
//        �ж��Ƿ������һ����������ǣ���100
        if (bd.size() == 1) {
            lessonUnits.setLesOrderId(bd1 + 100L);
        }else {
//            ��������ͺ���˳���ֵ֮���һ�����������
            bd2 = bd.get(1);
            lessonUnits.setLesOrderId(bd1 + ((bd2 - bd1)/2));
        }
        return lessonUnitsMapper.insertLessonUnits(lessonUnits);
    }

    /**
     * �޸Ŀ�ʱ��Ԫ
     *
     * @param lessonUnits ��ʱ��Ԫ
     * @return ���
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
     * ����ɾ����ʱ��Ԫ
     *
     * @param lesIds ��Ҫɾ���Ŀ�ʱ��Ԫ����
     * @return ���
     */
    @Transactional
    @Override
    public int deleteLessonUnitsByLesIds(Long[] lesIds)
    {
        lessonUnitsMapper.deleteTeachingTableByLesIds(lesIds);
        return lessonUnitsMapper.deleteLessonUnitsByLesIds(lesIds);
    }

    /**
     * ɾ����ʱ��Ԫ��Ϣ
     *
     * @param lesId ��ʱ��Ԫ����
     * @return ���
     */
    @Transactional
    @Override
    public int deleteLessonUnitsByLesId(Long lesId)
    {
        lessonUnitsMapper.selectByLesId(lesId).forEach(teachingId -> {
//            ɾ���ۿ�ʱ��
            teachingTableMapper.deleteViewedTeachingId(teachingId);
//            ɾ������
            teachingTableMapper.deleteA1CommunicationByTeachingId(teachingId);
        });
//        ɾ����ʱ
        lessonUnitsMapper.deleteTeachingTableByLesId(lesId);
//        ɾ����Ԫ
        return lessonUnitsMapper.deleteLessonUnitsByLesId(lesId);
    }

    /**
     * ����A1 ����ѧϰѧ�����Ϲۿ���¼����Ϣ
     *
     * @param lessonUnits ��ʱ��Ԫ����
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
     * ����A1 ����ѧϰѧ�����Ϲۿ���¼����Ϣ
     *
     * @param teachingTableList ��ʱ��Ԫ����
     */
    public int insertUnitsTable(TeachingTable teachingTableList)
    {
//        ����ʱ��
        teachingTableList.setCreatedAt(new Date());
//        ��������
        List<BigDecimal> bd = new ArrayList<BigDecimal>();
        if (teachingTableList.getTeachingId().equals(0L)) {
            bd.add(lessonUnitsMapper.batchTeachingTableLesList());
        }else {
            bd = lessonUnitsMapper.batchTeachingTableByLesId(teachingTableList.getTeachingId());
        }
        BigDecimal bd1 = bd.get(0);
        BigDecimal bd2 = BigDecimal.ZERO;
//        �ж��Ƿ������һ����������ǣ���100
        if (bd.size() == 1) {
            teachingTableList.setTeachingOrder(bd1.add(BigDecimal.valueOf(100)));
        } else {
            bd2 = bd.get(1);
            teachingTableList.setTeachingOrder(bd1.add(bd2.subtract(bd1).divide(BigDecimal.valueOf(2))));
        }
        return lessonUnitsMapper.batchUnitsTable(teachingTableList);
    }
}
