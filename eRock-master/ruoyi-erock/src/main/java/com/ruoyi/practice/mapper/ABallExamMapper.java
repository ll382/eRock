package com.ruoyi.practice.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.practice.domain.ABallExam;
import org.apache.ibatis.annotations.Param;

/**
 * A 运球a_mark_sheetms_idMapper接口
 * 
 * @author ljy
 * @date 2024-04-11
 */
public interface ABallExamMapper 
{
    /**
     * 查询A 运球a_mark_sheetms_id
     * 
     * @param beId A 运球a_mark_sheetms_id主键
     * @return A 运球a_mark_sheetms_id
     */
    public ABallExam selectABallExamByBeId(Long beId);

    /**
     * 查询A 运球a_mark_sheetms_id列表
     * 
     * @param aBallExam A 运球a_mark_sheetms_id
     * @return A 运球a_mark_sheetms_id集合
     */
    public List<ABallExam> selectABallExamList(ABallExam aBallExam);

    /**
     * 查询ABallExam列表
     *
     * @param enumId 枚举ID
     * @param stuId 学生ID
     * @param crDate 上课日期
     * @return ABallExam集合
     */
    public List<ABallExam> selectABallExamByEnumIdByStuIdByCrDataList(@Param("enumId")Integer enumId,@Param("stuId")Long stuId,@Param("msDribble")String msDribble,@Param("crDate")Date crDate);

    /**
     * 新增A 运球a_mark_sheetms_id
     * 
     * @param aBallExam A 运球a_mark_sheetms_id
     * @return 结果
     */
    public int insertABallExam(ABallExam aBallExam);

    /**
     * 修改A 运球a_mark_sheetms_id
     * 
     * @param aBallExam A 运球a_mark_sheetms_id
     * @return 结果
     */
    public int updateABallExam(ABallExam aBallExam);

    /**
     * 删除A 运球a_mark_sheetms_id
     * 
     * @param beId A 运球a_mark_sheetms_id主键
     * @return 结果
     */
    public int deleteABallExamByBeId(Long beId);

    /**
     * 批量删除A 运球a_mark_sheetms_id
     * 
     * @param beIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteABallExamByBeIds(Long[] beIds);
}
