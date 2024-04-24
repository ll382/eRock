package com.ruoyi.InClassModule.mapper;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2TeachingAssistant;

/**
 * A2 合作学习 助教评价
Mapper接口
 *
 * @author heye
 * @date 2024-04-11
 */
public interface A2TeachingAssistantMapper
{
    /**
     * 查询A2 合作学习 助教评价

     *
     * @param crId A2 合作学习 助教评价
主键
     * @return A2 合作学习 助教评价

     */
    public List<A2TeachingAssistant> selectA2TeachingAssistantByTaId(Long crId);

    /**
     * 查询A2 合作学习 助教评价
列表
     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return A2 合作学习 助教评价
集合
     */
    public List<A2TeachingAssistant> selectA2TeachingAssistantList(A2TeachingAssistant a2TeachingAssistant);

    /**
     * 新增A2 合作学习 助教评价

     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return 结果
     */
    public int insertA2TeachingAssistant(A2TeachingAssistant a2TeachingAssistant);

    /**
     * 修改A2 合作学习 助教评价

     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return 结果
     */
    public int updateA2TeachingAssistant(A2TeachingAssistant a2TeachingAssistant);

    /**
     * 删除A2 合作学习 助教评价

     *
     * @param taId A2 合作学习 助教评价
主键
     * @return 结果
     */
    public int deleteA2TeachingAssistantByTaId(Long taId);

    /**
     * 批量删除A2 合作学习 助教评价

     *
     * @param taIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA2TeachingAssistantByTaIds(Long[] taIds);
}
