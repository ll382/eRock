package com.ruoyi.visualization.mapper;

import java.util.List;
import com.ruoyi.visualization.domain.HistogramLineChart;
import org.apache.ibatis.annotations.Param;

/**
 * PC段第二页面Mapper接口
 * 
 * @author ljy
 * @date 2024-03-25
 */
public interface HistogramLineChartMapper 
{
    /**
     * 查询PC段第二页面
     * 
     * @param stuName PC段第二页面主键
     * @return PC段第二页面
     */
    public HistogramLineChart selectHistogramLineChartByStuName(String stuName);

    /**
     * 查询PC段第二页面列表
     * 
     * @param histogramLineChart PC段第二页面
     * @return PC段第二页面集合
     */
    public List<HistogramLineChart> selectHistogramLineChartList(HistogramLineChart histogramLineChart);

    /**
     * 根据学生姓名和枚举ID去重日期
     *
     * @param stuName 学生姓名
     * @param enumId  枚举ID
     * @return
     */
    public List<HistogramLineChart> selectHistogramLineChartByStuNameAndEnumId(@Param("stuName") String stuName,@Param("enumId")  Long enumId);

    /**
     * 新增PC段第二页面
     * 
     * @param histogramLineChart PC段第二页面
     * @return 结果
     */
    public int insertHistogramLineChart(HistogramLineChart histogramLineChart);

    /**
     * 修改PC段第二页面
     * 
     * @param histogramLineChart PC段第二页面
     * @return 结果
     */
    public int updateHistogramLineChart(HistogramLineChart histogramLineChart);

    /**
     * 删除PC段第二页面
     * 
     * @param stuName PC段第二页面主键
     * @return 结果
     */
    public int deleteHistogramLineChartByStuName(String stuName);

    /**
     * 批量删除PC段第二页面
     * 
     * @param stuNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHistogramLineChartByStuNames(String[] stuNames);
}
