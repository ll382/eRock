package com.ruoyi.visualization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ruoyi.visualization.domain.HistogramLineChart;

/**
 * PC段第二页面Service接口
 * 
 * @author ljy
 * @date 2024-03-25
 */
public interface IHistogramLineChartService 
{
    /**
     * 查询PC段第二页面
     * 
     * @param stuName PC段第二页面主键
     * @return PC段第二页面
     */
    public ArrayList<HashMap<String,ArrayList>> selectHistogramLineChartByStuName(String stuName);

    /**
     * 查询PC段第二页面列表
     * 
     * @param histogramLineChart PC段第二页面
     * @return PC段第二页面集合
     */
    public List<HistogramLineChart> selectHistogramLineChartList(HistogramLineChart histogramLineChart);

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
     * 批量删除PC段第二页面
     * 
     * @param stuNames 需要删除的PC段第二页面主键集合
     * @return 结果
     */
    public int deleteHistogramLineChartByStuNames(String[] stuNames);

    /**
     * 删除PC段第二页面信息
     * 
     * @param stuName PC段第二页面主键
     * @return 结果
     */
    public int deleteHistogramLineChartByStuName(String stuName);
}
