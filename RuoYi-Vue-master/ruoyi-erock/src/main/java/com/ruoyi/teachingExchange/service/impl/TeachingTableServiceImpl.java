package com.ruoyi.teachingExchange.service.impl;

import java.util.*;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.service.impl.SelectUserImpl;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.teachingExchange.domain.A1Viewed;
import com.ruoyi.teachingExchange.domain.TeachingUnit;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

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
            for (A1Communication com : comm.getA1CommunicationList()) {
//                如果主贴ID为空则跳过本次循环
                if (com.getComId() == null) {
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

//    评论递归方法
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
    public String updateStudent() {
        int i = 0;
        int j = 0;
        List<Student> stuList = studentMapper.selectStudentList(null);
        SysUser user = new SysUser();
        user.setAvatar("https://cdn1.d5v.cc/CDN/Project/eRock/tx/2.jpg");
        user.setStatus("0");
        user.setRoleId(101L);
        for (Student a:stuList) {

            List<SysUser> sysUser = sysUserMapper.selectUserByUserNickName(a.getStuName());
            Student student = new Student();
            if (sysUser.size() != 0){
                System.out.println(sysUser.size());
                student.setStuName(sysUser.get(0).getNickName());
                student.setStuId(sysUser.get(0).getUserId());
                teachingTableMapper.updateStudent(student);
                i++;
            }else {
//                设置用户信息
                user.setUserId(null);
                user.setNickName(a.getStuName());
                user.setUserName(SelectUserImpl.toPinyin(a.getStuName()));
                user.setPassword("654321");
                SysUserRole sur = new SysUserRole();
                sysUserMapper.insertUser(user);
//                设置用户权限
                sur.setUserId(user.getUserId());
                sur.setRoleId(user.getRoleId());
                sysUserRoleMapper.batchUserOneRole(sur);
//                更新学生表
                student.setStuName(user.getNickName());
                student.setStuId(user.getUserId());
                teachingTableMapper.updateStudent(student);
                j++;
            }
        }
        return "操作完成，已更新：" + i + "人，已插入" + j;
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
//        不需要更新评论等底下内容,直接注释
//        teachingTableMapper.deleteA1CommunicationByTeachingId(teachingTable.getTeachingId());
//        insertA1Communication(teachingTable);
//        返回更新内容
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
