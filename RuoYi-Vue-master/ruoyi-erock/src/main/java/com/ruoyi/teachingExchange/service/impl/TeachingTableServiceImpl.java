package com.ruoyi.teachingExchange.service.impl;

import java.util.*;

import com.ruoyi.core.domain.Student;
import com.ruoyi.teachingExchange.domain.A1Viewed;
import com.ruoyi.teachingExchange.domain.TeachingUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
//        查询老师发布内容以及主贴回复
        TeachingTable comm = teachingTableMapper.selectCommunicationList(commId);
        if (comm == null) {
//            如果没有内容则返回
            return comm;
        }else {
//            循环遍历里面list主贴内容
            for (A1Communication com: comm.getA1CommunicationList()) {
//                如果主贴ID为空则跳过本次循环
                if (com.getComId() == null){
                    continue;
                }
//                递归遍历主贴内所有子帖内容，带出所有排好序的子帖
                List<A1Communication> recursion = recursion(teachingTableMapper.selectCommunicationId(com.getComId()));
//                将所有内容放到Replys中
                com.setReplys(recursion);
            }
            return comm;
        }
    }

//    递归方法
    public List<A1Communication> recursion(List<A1Communication> InComm) {
//        申明一个用于存储历史查询对象的空数组
        List<A1Communication> OutCom = new ArrayList<>();
//        for循环输入的内容
        for (A1Communication inCom : InComm) {
//            查询输入List的所有内容
            List<A1Communication> InCom = teachingTableMapper.selectCommunicationId(inCom.getComId());
//            判断当前List是否有回复
            if (InCom != null && !InCom.isEmpty()) {
//                如果有则递归查找所有内容
                OutCom.addAll(recursion(InCom));
            }
            OutCom.add(inCom);
        }
//        为所有内容进行排序并返回
        Collections.sort(OutCom);
        return OutCom;
    }

//    警示代码
//public List<A1Communication> recursion(List<A1Communication> InComm) {
//    List<A1Communication> OutCom = null;
//// 遍历回复主贴的内容
//    for (A1Communication inCom:InComm) {
//        List<A1Communication> InCom = null;
//        if(inCom.getComId() != null) {
//            InCom = teachingTableMapper.selectCommunicationId(inCom.getComId());
//        }
//        if (OutCom != null && InCom != null) {
//            OutCom.addAll(recursion(InCom));
//        }else {
//            OutCom = InCom;
//        }
//        if (OutCom != null){
//            System.out.println(Arrays.toString(OutCom.toArray()));
//        }
//    }
//// 将回复放到外部标记
//    if (OutCom !=null) {
//        InComm.addAll(OutCom);
//    }
//    return OutCom;
//}
    /**
     * 查询A1 id查观看记录
     *
     * @param teachingId A1 线上学习学生线上观看记录表主键
     * @return A1 线上学习学生线上观看记录表
     */
    @Override
    public List<TeachingTable> selectTeachingViewedTableByTeachingId()
    {
        return teachingTableMapper.selectTeachingViewedTableByTeachingId();
    }

    @Override
    public List<TeachingUnit> selectTeachingUnitList() {
        return teachingTableMapper.selectTeachingUnitList();
    }


    @Override
    public int insertTeachingViewTable(A1Viewed a1Viewed)
    {
        a1Viewed.setViewedAt(new Date());
        return teachingTableMapper.insertTeachingViewedTable(a1Viewed);
    }

    @Override
    public int updateTeachingViewedTable(A1Viewed a1Viewed)
    {
        a1Viewed.setViewedAt(new Date());
        return teachingTableMapper.updateTeachingViewedTable(a1Viewed);
    }



    @Override
    public int deleteViewedTeachingId(Long id)
    {
        return teachingTableMapper.deleteViewedTeachingId(id);
    }



    @Override
    public int updateStudent() {
        int i = 0;
        List<Student> stuList = teachingTableMapper.selectViewed();
        for (Student a:stuList) {
            if (teachingTableMapper.updateStudent(a) > 0){
                i++;
            }
        }
        return i;
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
        teachingTableMapper.deleteViewedTeachingId(teachingId);
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
