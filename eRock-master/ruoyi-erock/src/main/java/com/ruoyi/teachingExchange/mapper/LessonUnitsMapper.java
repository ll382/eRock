package com.ruoyi.teachingExchange.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.teachingExchange.domain.LessonUnits;
import com.ruoyi.teachingExchange.domain.TeachingTable;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.annotation.Version;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 课时单元Mapper接口
 *
 * @author heye
 * @date 2024-04-01
 */
public interface LessonUnitsMapper
{
    /**
     * 查询课时单元
     *
     * @param lesId 课时单元主键
     * @return 课时单元
     */
    public LessonUnits selectLessonUnitsByLesId(Long lesId);

    @Select("select b.teaching_id from lesson_units a left join teaching_table b on b.les_id = a.les_id where a.les_id = #{lesId}")
    public List<Long> selectByLesId(Long lesId);

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
     * 删除课时单元
     *
     * @param lesId 课时单元主键
     * @return 结果
     */
    public int deleteLessonUnitsByLesId(Long lesId);

    /**
     * 批量删除课时单元
     *
     * @param lesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLessonUnitsByLesIds(Long[] lesIds);

    /**
     * 批量删除A1 线上学习学生线上观看记录表
     *
     * @param lesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachingTableByLesIds(Long[] lesIds);

    /**
     * 批量新增A1 线上学习学生线上观看记录表
     *
     * @param teachingTableList A1 线上学习学生线上观看记录表列表
     * @return 结果
     */
    public int batchTeachingTable(List<TeachingTable> teachingTableList);
    public int batchUnitsTable(TeachingTable teachingTableList);

//    插入子查询之查询order排序字段
    @Select("SELECT teaching_order FROM teaching_table WHERE teaching_order >= (select teaching_order from teaching_table where teaching_id = #{orderId}) ORDER BY teaching_order asc limit 2")
    public List<BigDecimal> batchTeachingTableByLesId(@PathVariable("orderId") Long orderId);

    @Select("SELECT MAX(teaching_order) AS max_teaching_order FROM teaching_table;")
    public BigDecimal batchTeachingTableLesList();

    @Select("SELECT les_order_id  FROM lesson_units WHERE les_order_id >= (select les_order_id from lesson_units where les_id = #{orderId}) ORDER BY les_order_id asc limit 2;")
    public List<Long> batchLessonUnitsByLesId(@PathVariable("orderId") Long orderId);

    @Select("SELECT MAX(les_order_id) AS max_les_order_id FROM lesson_units ;")
    public Long batchLessonUnitsLesList();

    /**
     * 通过课时单元主键删除A1 线上学习学生线上观看记录表信息
     *
     * @param lesId 课时单元ID
     * @return 结果
     */
    public int deleteTeachingTableByLesId(Long lesId);
}
