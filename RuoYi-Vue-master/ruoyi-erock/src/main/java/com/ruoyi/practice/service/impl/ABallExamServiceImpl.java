package com.ruoyi.practice.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.practice.mapper.ABallExamMapper;
import com.ruoyi.practice.domain.ABallExam;
import com.ruoyi.practice.service.IABallExamService;

/**
 * A 运球a_mark_sheetms_idService业务层处理
 * 
 * @author ljy
 * @date 2024-04-11
 */
@Service
public class ABallExamServiceImpl implements IABallExamService 
{
    @Autowired
    private ABallExamMapper aBallExamMapper;

    /**
     * 查询A 运球a_mark_sheetms_id
     * 
     * @param beId A 运球a_mark_sheetms_id主键
     * @return A 运球a_mark_sheetms_id
     */
    @Override
    public ABallExam selectABallExamByBeId(Long beId)
    {
        return aBallExamMapper.selectABallExamByBeId(beId);
    }

    /**
     * 查询A 运球a_mark_sheetms_id列表
     * 
     * @param aBallExam A 运球a_mark_sheetms_id
     * @return A 运球a_mark_sheetms_id
     */
    @Override
    public List<ABallExam> selectABallExamList(ABallExam aBallExam)
    {
        return aBallExamMapper.selectABallExamList(aBallExam);
    }

    /**
     * 查询ABallExam
     *
     * @param enumId 枚举ID
     * @param stuId 学生ID
     * @param crDate 上课日期
     * @return ABallExam
     */
    public ABallExam selectABallExamByEnumIdByStuIdByCrData(Integer enumId, Long stuId,String msDribble, String crDate){
        List<ABallExam> aBallExams = aBallExamMapper.selectABallExamByEnumIdByStuIdByCrDataList(enumId, stuId,msDribble,DateUtil.StringConvertDate(crDate));
        ABallExam exam = null;
        for (ABallExam aBallExam:aBallExams){
            exam = aBallExam;
            break;
        }
        return exam;
    }

    /**
     * 新增A 运球a_mark_sheetms_id
     * 
     * @param aBallExam A 运球a_mark_sheetms_id
     * @return 结果
     */
    @Override
    public int insertABallExam(ABallExam aBallExam)
    {
        return aBallExamMapper.insertABallExam(aBallExam);
    }

    /**
     * 修改A 运球a_mark_sheetms_id
     * 
     * @param aBallExam A 运球a_mark_sheetms_id
     * @return 结果
     */
    @Override
    public int updateABallExam(ABallExam aBallExam)
    {
        return aBallExamMapper.updateABallExam(aBallExam);
    }

    /**
     * 批量删除A 运球a_mark_sheetms_id
     * 
     * @param beIds 需要删除的A 运球a_mark_sheetms_id主键
     * @return 结果
     */
    @Override
    public int deleteABallExamByBeIds(Long[] beIds)
    {
        return aBallExamMapper.deleteABallExamByBeIds(beIds);
    }

    /**
     * 删除A 运球a_mark_sheetms_id信息
     * 
     * @param beId A 运球a_mark_sheetms_id主键
     * @return 结果
     */
    @Override
    public int deleteABallExamByBeId(Long beId)
    {
        return aBallExamMapper.deleteABallExamByBeId(beId);
    }
}
