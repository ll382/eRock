package com.ruoyi.teachingExchange.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.teachingExchange.domain.A1Communication;
import com.ruoyi.teachingExchange.domain.TeachingTable;
import com.ruoyi.teachingExchange.mapper.TeachingTableMapper;
import com.ruoyi.teachingExchange.service.ITeachingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * A1 线上学习 教学
 Service业务层处理
 *
 * @author heye
 * @date 2024-03-20
 */
@Service
public class TeachingTableServiceImpl implements ITeachingTableService
{
    @Autowired
    private TeachingTableMapper teachingTableMapper;

    /**
     * 查询A1 线上学习 教学
 
     *
     * @param teachingId A1 线上学习 教学
 主键
     * @return A1 线上学习 教学
 
     */
    @Override
    public TeachingTable selectTeachingTableByTeachingId(Long teachingId)
    {
        return teachingTableMapper.selectTeachingTableByTeachingId(teachingId);
    }

    /**
     * 查询A1 线上学习 教学
 列表
     *
     * @param teachingTable A1 线上学习 教学
 
     * @return A1 线上学习 教学
 
     */
    @Override
    public List<TeachingTable> selectTeachingTableList(TeachingTable teachingTable)
    {
        return teachingTableMapper.selectTeachingTableList(teachingTable);
    }

    /**
     * 新增A1 线上学习 教学
 
     *
     * @param teachingTable A1 线上学习 教学
 
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTeachingTable(TeachingTable teachingTable)
    {
        int rows = teachingTableMapper.insertTeachingTable(teachingTable);
        insertA1Communication(teachingTable);
        return rows;
    }

    /**
     * 修改A1 线上学习 教学
 
     *
     * @param teachingTable A1 线上学习 教学
 
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTeachingTable(TeachingTable teachingTable)
    {
        teachingTableMapper.deleteA1CommunicationByTeachingId(teachingTable.getTeachingId());
        insertA1Communication(teachingTable);
        return teachingTableMapper.updateTeachingTable(teachingTable);
    }

    /**
     * 批量删除A1 线上学习 教学
 
     *
     * @param teachingIds 需要删除的A1 线上学习 教学
 主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeachingTableByTeachingIds(Long[] teachingIds)
    {
        teachingTableMapper.deleteA1CommunicationByTeachingIds(teachingIds);
        return teachingTableMapper.deleteTeachingTableByTeachingIds(teachingIds);
    }

    /**
     * 删除A1 线上学习 教学
 信息
     *
     * @param teachingId A1 线上学习 教学
 主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTeachingTableByTeachingId(Long teachingId)
    {
        teachingTableMapper.deleteA1CommunicationByTeachingId(teachingId);
        return teachingTableMapper.deleteTeachingTableByTeachingId(teachingId);
    }

    /**
     * 新增A1交流信息
     *
     * @param teachingTable A1 线上学习 教学
 对象
     */
    public void insertA1Communication(TeachingTable teachingTable)
    {
        List<A1Communication> a1CommunicationList = teachingTable.getA1CommunicationList();
        Long teachingId = teachingTable.getTeachingId();
        if (StringUtils.isNotNull(a1CommunicationList))
        {
            List<A1Communication> list = new ArrayList<A1Communication>();
            for (A1Communication a1Communication : a1CommunicationList)
            {
                a1Communication.setTeachingId(teachingId);
                list.add(a1Communication);
            }
            if (list.size() > 0)
            {
                teachingTableMapper.batchA1Communication(list);
            }
        }
    }
}
