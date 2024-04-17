package com.ruoyi.InClassModule.mapper;

import java.util.List;
import com.ruoyi.InClassModule.domain.A2Attendance;
import org.apache.ibatis.annotations.Param;

/**
 * A2 课堂表现classRegistercr         Mapper接口
 *
 * @author heye
 * @date 2024-04-11
 */
public interface A2AttendanceMapper
{
    /**
     * 查询A2 课堂表现classRegistercr
     *
     * @param crId A2 课堂表现classRegistercr         主键
     * @return A2 课堂表现classRegistercr
     */
    public List<A2Attendance> selectA2AttendanceByAaId(@Param("crId") Long crId,@Param("enumId") Long enumId);

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
     * 删除A2 课堂表现classRegistercr
     *
     * @param aaId A2 课堂表现classRegistercr         主键
     * @return 结果
     */
    public int deleteA2AttendanceByAaId(Long aaId);

    /**
     * 批量删除A2 课堂表现classRegistercr
     *
     * @param aaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA2AttendanceByAaIds(Long[] aaIds);
}
