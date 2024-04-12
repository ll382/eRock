package com.ruoyi.InClassModule.service.impl;

import java.util.List;

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
     * @param aaId A2 课堂表现classRegistercr         主键
     * @return A2 课堂表现classRegistercr
     */
    @Override
    public A2Attendance selectA2AttendanceByAaId(Long aaId)
    {
        return a2AttendanceMapper.selectA2AttendanceByAaId(aaId);
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
        return a2AttendanceMapper.insertA2Attendance(a2Attendance);
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
        return a2AttendanceMapper.updateA2Attendance(a2Attendance);
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
}
