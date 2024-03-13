package com.ruoyi.userslist.service;

import java.util.List;
import com.ruoyi.userslist.domain.Grade;

/**
 * 班级Service接口
 * 
 * @author eRock
 * @date 2024-03-13
 */
public interface IGradeService 
{
    /**
     * 查询班级
     * 
     * @param classId 班级主键
     * @return 班级
     */
    public Grade selectGradeByClassId(Long classId);

    /**
     * 查询班级列表
     * 
     * @param grade 班级
     * @return 班级集合
     */
    public List<Grade> selectGradeList(Grade grade);

    /**
     * 新增班级
     * 
     * @param grade 班级
     * @return 结果
     */
    public int insertGrade(Grade grade);

    /**
     * 修改班级
     * 
     * @param grade 班级
     * @return 结果
     */
    public int updateGrade(Grade grade);

    /**
     * 批量删除班级
     * 
     * @param classIds 需要删除的班级主键集合
     * @return 结果
     */
    public int deleteGradeByClassIds(Long[] classIds);

    /**
     * 删除班级信息
     * 
     * @param classId 班级主键
     * @return 结果
     */
    public int deleteGradeByClassId(Long classId);
}
