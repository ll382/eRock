package com.ruoyi.teachingExchange.mapper;

import java.util.List;

import com.ruoyi.core.domain.Student;
import com.ruoyi.teachingExchange.domain.A1Viewed;
import com.ruoyi.teachingExchange.domain.TeachingTable;
import com.ruoyi.teachingExchange.domain.A1Communication;
import com.ruoyi.teachingExchange.domain.TeachingUnit;
import io.lettuce.core.dynamic.annotation.Value;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.data.annotation.Version;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

/**
 * A1 线上学习学生线上观看记录表Mapper接口
 *
 * @author heye
 * @date 2024-03-20
 */
public interface TeachingTableMapper
{
    /**
     * 查询A1 线上学习学生线上观看记录表
     *
     * @param teachingId A1 线上学习学生线上观看记录表主键
     * @return A1 线上学习学生线上观看记录表
     */
    public TeachingTable selectTeachingTableByTeachingId(Long teachingId);

    /**
     * 查询A1 线上学习学生线上观看记录表评论列表
     *
     * @return A1 线上学习学生线上观看记录表集合列表
     */
    public TeachingTable selectCommunicationList(Long commId);

//    评论表子查询
    public List<A1Communication> selectCommunicationId(Long subId);

//    父级单元全查询
    public List<TeachingUnit> selectTeachingUnitList();

//    观看记录表查询
    public List<TeachingTable> selectTeachingViewedTableByTeachingId();

    //    观看记录表插入
    public int insertTeachingViewedTable(A1Viewed a1Viewed);

//    批量插入
    public int batchA1Viewed(List<A1Viewed> a1ViewedList);



//    test
    public int updateStudent(Student stu);
    public List<Student> selectViewed();



    /**
     * 查询A1 线上学习学生线上观看记录表列表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
     * @return A1 线上学习学生线上观看记录表集合
     */
    public List<TeachingTable> selectTeachingTableList(TeachingTable teachingTable);

    /**
     * 新增A1 线上学习学生线上观看记录表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
     * @return 结果
     */
    public int insertTeachingTable(TeachingTable teachingTable);

    /**
     * 修改A1 线上学习学生线上观看记录表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
     * @return 结果
     */
    public int updateTeachingTable(TeachingTable teachingTable);

    /**
     * 删除A1 线上学习学生线上观看记录表
     *
     * @param teachingId A1 线上学习学生线上观看记录表主键
     * @return 结果
     */
    public int deleteTeachingTableByTeachingId(Long teachingId);

    /**
     * 批量删除A1 线上学习学生线上观看记录表
     *
     * @param teachingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachingTableByTeachingIds(Long[] teachingIds);

    /**
     * 批量删除A1交流
     *
     * @param teachingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA1CommunicationByTeachingIds(Long[] teachingIds);

    /**
     * 批量新增A1交流
     *
     * @param a1CommunicationList A1交流列表
     * @return 结果
     */
    public int batchA1Communication(List<A1Communication> a1CommunicationList);


    /**
     * 通过A1 线上学习学生线上观看记录表主键删除A1交流信息
     *
     * @param teachingId A1 线上学习学生线上观看记录表ID
     * @return 结果
     */
    public int deleteA1CommunicationByTeachingId(Long teachingId);
}
