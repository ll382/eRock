package com.ruoyi.match.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.core.domain.Semester;
import com.ruoyi.match.domain.ClassRegister;
import com.ruoyi.match.domain.CompetitionRecord;
import org.apache.ibatis.annotations.Select;

/**
 * 课堂记录Mapper接口
 *
 * @author houq
 * @date 2024-04-08
 */
public interface ClassRegisterMapper
{
    /**
     * 查询课堂记录
     *
     * @param crId 课堂记录主键
     * @return 课堂记录
     */
    public ClassRegister selectClassRegisterByCrId(Long crId);

    /**
     * 查询课堂记录列表
     *
     * @param classRegister 课堂记录
     * @return 课堂记录集合
     */
    public List<ClassRegister> selectClassRegisterList(ClassRegister classRegister);

    /**
     * 新增课堂记录
     *
     * @param classRegister 课堂记录
     * @return 结果
     */
    public int insertClassRegister(ClassRegister classRegister);

    /**
     * 修改课堂记录
     *
     * @param classRegister 课堂记录
     * @return 结果
     */
    public int updateClassRegister(ClassRegister classRegister);

    /**
     * 删除课堂记录
     *
     * @param crId 课堂记录主键
     * @return 结果
     */
    public int deleteClassRegisterByCrId(Long crId);

    /**
     * 批量删除课堂记录
     *
     * @param crIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassRegisterByCrIds(Long[] crIds);

    /**
     * 批量删除C 比赛记录
     *
     * @param crIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionRecordByCrIds(Long[] crIds);

    /**
     * 批量新增C 比赛记录
     *
     * @param competitionRecordList C 比赛记录列表
     * @return 结果
     */
    public int batchCompetitionRecord(List<CompetitionRecord> competitionRecordList);


    /**
     * 通过课堂记录主键删除C 比赛记录信息
     *
     * @param crId 课堂记录ID
     * @return 结果
     */
    public int deleteCompetitionRecordByCrId(Long crId);
}
