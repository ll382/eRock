package com.ruoyi.InClassModule.mapper;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2StudentEvaluate;

/**
 * A2 合作学习 学生互评Mapper接口
 *
 * @author heye
 * @date 2024-04-11
 */
public interface A2StudentEvaluateMapper
{
    /**
     * 查询A2 合作学习 学生互评
     *
     * @param crId A2 合作学习 学生互评主键
     * @return A2 合作学习 学生互评
     */
    public List<A2StudentEvaluate> selectA2StudentEvaluateBySeId(Long crId);

    /**
     * 查询A2 合作学习 学生互评列表
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return A2 合作学习 学生互评集合
     */
    public List<A2StudentEvaluate> selectA2StudentEvaluateList(A2StudentEvaluate a2StudentEvaluate);

    /**
     * 新增A2 合作学习 学生互评
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return 结果
     */
    public int insertA2StudentEvaluate(A2StudentEvaluate a2StudentEvaluate);

    /**
     * 修改A2 合作学习 学生互评
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return 结果
     */
    public int updateA2StudentEvaluate(A2StudentEvaluate a2StudentEvaluate);

    /**
     * 删除A2 合作学习 学生互评
     *
     * @param seId A2 合作学习 学生互评主键
     * @return 结果
     */
    public int deleteA2StudentEvaluateBySeId(Long seId);

    /**
     * 批量删除A2 合作学习 学生互评
     *
     * @param seIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA2StudentEvaluateBySeIds(Long[] seIds);
}