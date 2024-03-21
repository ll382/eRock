package com.ruoyi.teachingExchange.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teachingExchange.mapper.A1CommunicationMapper;
import com.ruoyi.teachingExchange.domain.A1Communication;
import com.ruoyi.teachingExchange.service.IA1CommunicationService;

/**
 * A1交流Service业务层处理
 * 
 * @author heye
 * @date 2024-03-21
 */
@Service
public class A1CommunicationServiceImpl implements IA1CommunicationService 
{
    @Autowired
    private A1CommunicationMapper a1CommunicationMapper;

    /**
     * 查询A1交流
     * 
     * @param comId A1交流主键
     * @return A1交流
     */
    @Override
    public A1Communication selectA1CommunicationByComId(Long comId)
    {
        return a1CommunicationMapper.selectA1CommunicationByComId(comId);
    }

    /**
     * 查询A1交流列表
     * 
     * @param a1Communication A1交流
     * @return A1交流
     */
    @Override
    public List<A1Communication> selectA1CommunicationList(A1Communication a1Communication)
    {
        return a1CommunicationMapper.selectA1CommunicationList(a1Communication);
    }

    /**
     * 新增A1交流
     * 
     * @param a1Communication A1交流
     * @return 结果
     */
    @Override
    public int insertA1Communication(A1Communication a1Communication)
    {
        return a1CommunicationMapper.insertA1Communication(a1Communication);
    }

    /**
     * 修改A1交流
     * 
     * @param a1Communication A1交流
     * @return 结果
     */
    @Override
    public int updateA1Communication(A1Communication a1Communication)
    {
        return a1CommunicationMapper.updateA1Communication(a1Communication);
    }

    /**
     * 批量删除A1交流
     * 
     * @param comIds 需要删除的A1交流主键
     * @return 结果
     */
    @Override
    public int deleteA1CommunicationByComIds(Long[] comIds)
    {
        return a1CommunicationMapper.deleteA1CommunicationByComIds(comIds);
    }

    /**
     * 删除A1交流信息
     * 
     * @param comId A1交流主键
     * @return 结果
     */
    @Override
    public int deleteA1CommunicationByComId(Long comId)
    {
        return a1CommunicationMapper.deleteA1CommunicationByComId(comId);
    }
}
