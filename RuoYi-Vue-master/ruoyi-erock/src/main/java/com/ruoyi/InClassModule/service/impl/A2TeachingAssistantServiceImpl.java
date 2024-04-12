package com.ruoyi.InClassModule.service.impl;

import java.util.List;

import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2TeachingAssistantMapper;
import com.ruoyi.InClassModule.domain.A2TeachingAssistant;
import com.ruoyi.InClassModule.service.IA2TeachingAssistantService;

/**
 * A2 合作学习 助教评价
Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2TeachingAssistantServiceImpl implements IA2TeachingAssistantService
{
    @Autowired
    private A2TeachingAssistantMapper a2TeachingAssistantMapper;
    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 合作学习 助教评价

     *
     * @param taId A2 合作学习 助教评价
主键
     * @return A2 合作学习 助教评价

     */
    @Override
    public A2TeachingAssistant selectA2TeachingAssistantByTaId(Long taId)
    {
        return (A2TeachingAssistant)selectUser.selectStudent(a2TeachingAssistantMapper.selectA2TeachingAssistantByTaId(taId));
    }

    /**
     * 查询A2 合作学习 助教评价
列表
     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return A2 合作学习 助教评价

     */
    @Override
    public List<A2TeachingAssistant> selectA2TeachingAssistantList(A2TeachingAssistant a2TeachingAssistant)
    {
        return selectUser.selectStudent(a2TeachingAssistantMapper.selectA2TeachingAssistantList(a2TeachingAssistant));
    }

    /**
     * 新增A2 合作学习 助教评价

     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return 结果
     */
    @Override
    public int insertA2TeachingAssistant(A2TeachingAssistant a2TeachingAssistant)
    {
        return a2TeachingAssistantMapper.insertA2TeachingAssistant(a2TeachingAssistant);
    }

    /**
     * 修改A2 合作学习 助教评价

     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return 结果
     */
    @Override
    public int updateA2TeachingAssistant(A2TeachingAssistant a2TeachingAssistant)
    {
        return a2TeachingAssistantMapper.updateA2TeachingAssistant(a2TeachingAssistant);
    }

    /**
     * 批量删除A2 合作学习 助教评价

     *
     * @param taIds 需要删除的A2 合作学习 助教评价
主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantByTaIds(Long[] taIds)
    {
        return a2TeachingAssistantMapper.deleteA2TeachingAssistantByTaIds(taIds);
    }

    /**
     * 删除A2 合作学习 助教评价
信息
     *
     * @param taId A2 合作学习 助教评价
主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantByTaId(Long taId)
    {
        return a2TeachingAssistantMapper.deleteA2TeachingAssistantByTaId(taId);
    }
}
