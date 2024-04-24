package com.ruoyi.InClassModule.service.impl;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2StudentEvaluateMapper;
import com.ruoyi.InClassModule.domain.A2StudentEvaluate;
import com.ruoyi.InClassModule.service.IA2StudentEvaluateService;

/**
 * A2 合作学习 学生互评Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2StudentEvaluateServiceImpl implements IA2StudentEvaluateService
{
    @Autowired
    private A2StudentEvaluateMapper a2StudentEvaluateMapper;
    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 合作学习 学生互评
     *
     * @param crId A2 合作学习 学生互评主键
     * @return A2 合作学习 学生互评
     */
    @Override
    public List<A2StudentEvaluate> selectA2StudentEvaluateBySeId(Long crId)
    {
        return selectUser.selectStudent(a2StudentEvaluateMapper.selectA2StudentEvaluateBySeId(crId));
    }

    /**
     * 查询A2 合作学习 学生互评列表
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return A2 合作学习 学生互评
     */
    @Override
    public List<A2StudentEvaluate> selectA2StudentEvaluateList(A2StudentEvaluate a2StudentEvaluate)
    {
        return selectUser.selectStudent(a2StudentEvaluateMapper.selectA2StudentEvaluateList(a2StudentEvaluate));
    }

    /**
     * 新增A2 合作学习 学生互评
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return 结果
     */
    @Override
    public int insertA2StudentEvaluate(A2StudentEvaluate a2StudentEvaluate)
    {
        return a2StudentEvaluateMapper.insertA2StudentEvaluate(a2StudentEvaluate);
    }

    /**
     * 修改A2 合作学习 学生互评
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return 结果
     */
    @Override
    public int updateA2StudentEvaluate(A2StudentEvaluate a2StudentEvaluate)
    {
        return a2StudentEvaluateMapper.updateA2StudentEvaluate(a2StudentEvaluate);
    }

    /**
     * 批量删除A2 合作学习 学生互评
     *
     * @param seIds 需要删除的A2 合作学习 学生互评主键
     * @return 结果
     */
    @Override
    public int deleteA2StudentEvaluateBySeIds(Long[] seIds)
    {
        return a2StudentEvaluateMapper.deleteA2StudentEvaluateBySeIds(seIds);
    }

    /**
     * 删除A2 合作学习 学生互评信息
     *
     * @param seId A2 合作学习 学生互评主键
     * @return 结果
     */
    @Override
    public int deleteA2StudentEvaluateBySeId(Long seId)
    {
        return a2StudentEvaluateMapper.deleteA2StudentEvaluateBySeId(seId);
    }
}
