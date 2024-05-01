package com.ruoyi.InClassModule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2AttendanceMapper;
import com.ruoyi.InClassModule.domain.A2Attendance;
import com.ruoyi.InClassModule.service.IA2AttendanceService;

/**
 * A2 课堂表现classRegistercr         Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2AttendanceServiceImpl implements IA2AttendanceService
{
    @Autowired
    private A2AttendanceMapper a2AttendanceMapper;
    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 课堂表现classRegistercr
     *
     * @param crId A2 课堂表现classRegistercr         主键
     * @return A2 课堂表现classRegistercr
     */
    @Override
    public List<A2Attendance> selectA2AttendanceByAaId(Long crId, Long enumId)
    {
        return selectUser.selectStudent(a2AttendanceMapper.selectA2AttendanceByAaId(crId, enumId));
    }

    /**
     * 查询A2 课堂表现classRegistercr         列表
     *
     * @param a2Attendance A2 课堂表现classRegistercr
     * @return A2 课堂表现classRegistercr
     */
    @Override
    public List<A2Attendance> selectA2AttendanceList(A2Attendance a2Attendance)
    {
        return selectUser.selectStudent(a2AttendanceMapper.selectA2AttendanceList(a2Attendance));
    }

    /**
     * 新增A2 课堂表现classRegistercr
     *
     * @param a2Attendance A2 课堂表现classRegistercr
     * @return 结果
     */
    @Override
    public int insertA2Attendance(A2Attendance a2Attendance)
    {
//        判断是否已经存在该学生的考勤记录
        A2Attendance attendance = new A2Attendance();
        attendance.setStuId(a2Attendance.getStuId());
        attendance.setCrId(a2Attendance.getCrId());
        List<A2Attendance> a2Attendances = a2AttendanceMapper.selectA2AttendanceList(a2Attendance);

        if(StringUtils.isNotEmpty(a2Attendances)){
            return 0;
        }else {
            int inserted = a2AttendanceMapper.insertA2Attendance(a2Attendance);
            if (inserted > 0) {
                AModuleScore score = calculateScore(a2Attendance);
                int i = selectUser.updateStudentAScore(score, a2Attendance.getStuId());
                System.out.println(i);
                return inserted;
            }
            return inserted;
        }
    }

    /**
     * 修改A2 课堂表现classRegistercr
     *
     * @param a2Attendance A2 课堂表现classRegistercr
     * @return 结果
     */
    @Override
    public int updateA2Attendance(A2Attendance a2Attendance)
    {
        int updated = a2AttendanceMapper.updateA2Attendance(a2Attendance);
        if(updated == 0){
            return 0;
        }
//        分数计算
        AModuleScore score = calculateScore(a2Attendance);
//        更新学生A模块成绩
        int i = selectUser.updateStudentAScore(score, a2Attendance.getStuId());

        System.out.println(i);

        return updated;
    }

    /**
     * 批量删除A2 课堂表现classRegistercr
     *
     * @param aaIds 需要删除的A2 课堂表现classRegistercr         主键
     * @return 结果
     */
    @Override
    public int deleteA2AttendanceByAaIds(Long[] aaIds)
    {
        return a2AttendanceMapper.deleteA2AttendanceByAaIds(aaIds);
    }

    /**
     * 删除A2 课堂表现classRegistercr         信息
     *
     * @param aaId A2 课堂表现classRegistercr         主键
     * @return 结果
     */
    @Override
    public int deleteA2AttendanceByAaId(Long aaId)
    {
        return a2AttendanceMapper.deleteA2AttendanceByAaId(aaId);
    }

//      计算课堂模块总分
    public AModuleScore calculateScore(A2Attendance a2Attendance){
//        TODO: 插入A2考勤成绩表
//        查找优秀考勤记录
        A2Attendance attendanceScore = new A2Attendance();
        attendanceScore.setStuId(a2Attendance.getStuId());
//        20代表课堂表现记录
        attendanceScore.setAaType("20");
//        22代表考勤迟到
        attendanceScore.setEvaluationType2("22");
        double late  = a2AttendanceMapper.selectA2AttendanceList(attendanceScore).size() * 1;
//        23代表考勤公假
        attendanceScore.setEvaluationType2("23");
        double sabbaticals = a2AttendanceMapper.selectA2AttendanceList(attendanceScore).size() * 0;
//        24代表考勤事假
        attendanceScore.setEvaluationType2("24");
        double casualLeave = a2AttendanceMapper.selectA2AttendanceList(attendanceScore).size() * 0.5;
//        25代表考勤缺勤
        attendanceScore.setEvaluationType2("25");
        double absenteeism = a2AttendanceMapper.selectA2AttendanceList(attendanceScore).size() * 3;
//        计算考勤总分
        double addScore = late + sabbaticals + casualLeave + absenteeism;
//        TODO: 插入A2表现成绩表
//        查找优秀表现记录
        A2Attendance attendanceExpression = new A2Attendance();
        attendanceExpression.setStuId(a2Attendance.getStuId());
//        21代表课堂表现记录
        attendanceExpression.setAaType("21");
//        26代表表现优秀
        attendanceExpression.setEvaluationType2("26");
        int performWell = a2AttendanceMapper.selectA2AttendanceList(attendanceExpression).size();
//        查找差劲表现记录
//        28代表表现差劲
        attendanceExpression.setEvaluationType2("28");
        int poorPerformance = a2AttendanceMapper.selectA2AttendanceList(attendanceExpression).size();
//        计算表现总分
        Double ratingScore = selectUser.A2calculationTimes(performWell,poorPerformance);
//        插入总分
        AModuleScore aModuleScore = new AModuleScore();
        double v = ratingScore - addScore;

//        最高2分
        v = v > 2 ? 2 : v;
//        最低0分
        v = v < 0 ? 0 : v;

        aModuleScore.setClassroomPerformance(BigDecimal.valueOf(v));

        return aModuleScore;
    }
}
