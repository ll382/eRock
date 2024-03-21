package com.ruoyi.teachingExchange.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.teachingExchange.domain.A1Communication;
import com.ruoyi.teachingExchange.mapper.TeachingTableMapper;
import com.ruoyi.teachingExchange.domain.TeachingTable;
import com.ruoyi.teachingExchange.service.ITeachingTableService;

/**
 * A1 线上学习学生线上观看记录表Service业务层处理
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
     * 查询A1 线上学习学生线上观看记录表
     *
     * @param teachingId A1 线上学习学生线上观看记录表主键
     * @return A1 线上学习学生线上观看记录表
     */
    @Override
    public TeachingTable selectTeachingTableByTeachingId(Long teachingId)
    {
        return teachingTableMapper.selectTeachingTableByTeachingId(teachingId);
    }

    /**
     * 查询A1 线上学习学生线上观看记录表
     *
     * @return A1 线上学习学生线上观看记录表
     */
    @Override
    public TeachingTable selectCommunicationList(Long commId)
    {
        TeachingTable comm = teachingTableMapper.selectCommunicationList(commId);
        if (comm == null) {
            return comm;
        }else {
            for (A1Communication com: comm.getA1CommunicationList()) {
                if (com.getComId() == null){
                    continue;
                }
                List<A1Communication> InComm = teachingTableMapper.selectCommunicationId(com.getComId());
                com.setReplys(InComm);
            }
            return comm;
        }

    }

    /**
     * 查询A1 线上学习学生线上观看记录表列表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
     * @return A1 线上学习学生线上观看记录表
     */
    @Override
    public List<TeachingTable> selectTeachingTableList(TeachingTable teachingTable)
    {
        return teachingTableMapper.selectTeachingTableList(teachingTable);
    }

    /**
     * 新增A1 线上学习学生线上观看记录表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
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
     * 修改A1 线上学习学生线上观看记录表
     *
     * @param teachingTable A1 线上学习学生线上观看记录表
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
     * 批量删除A1 线上学习学生线上观看记录表
     *
     * @param teachingIds 需要删除的A1 线上学习学生线上观看记录表主键
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
     * 删除A1 线上学习学生线上观看记录表信息
     *
     * @param teachingId A1 线上学习学生线上观看记录表主键
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
     * @param teachingTable A1 线上学习学生线上观看记录表对象
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
