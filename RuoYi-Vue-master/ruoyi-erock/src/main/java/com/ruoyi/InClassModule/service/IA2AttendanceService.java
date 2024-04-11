package com.ruoyi.InClassModule.service;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2Attendance;

/**
 * A2 课堂表现classRegistercr         Service接口
 * 
 * @author heye
 * @date 2024-04-11
 */
public interface IA2AttendanceService 
{
    /**
     * 查询A2 课堂表现classRegistercr         
     * 
     * @param aaId A2 课堂表现classRegistercr         主键
     * @return A2 课堂表现classRegistercr         
     */
    public A2Attendance selectA2AttendanceByAaId(Long aaId);

    /**
     * 查询A2 课堂表现classRegistercr         列表
     * 
     * @param a2Attendance A2 课堂表现classRegistercr         
     * @return A2 课堂表现classRegistercr         集合
     */
    public List<A2Attendance> selectA2AttendanceList(A2Attendance a2Attendance);

    /**
     * 新增A2 课堂表现classRegistercr         
     * 
     * @param a2Attendance A2 课堂表现classRegistercr         
     * @return 结果
     */
    public int insertA2Attendance(A2Attendance a2Attendance);

    /**
     * 修改A2 课堂表现classRegistercr         
     * 
     * @param a2Attendance A2 课堂表现classRegistercr         
     * @return 结果
     */
    public int updateA2Attendance(A2Attendance a2Attendance);

    /**
     * 批量删除A2 课堂表现classRegistercr         
     * 
     * @param aaIds 需要删除的A2 课堂表现classRegistercr         主键集合
     * @return 结果
     */
    public int deleteA2AttendanceByAaIds(Long[] aaIds);

    /**
     * 删除A2 课堂表现classRegistercr         信息
     * 
     * @param aaId A2 课堂表现classRegistercr         主键
     * @return 结果
     */
    public int deleteA2AttendanceByAaId(Long aaId);
}
