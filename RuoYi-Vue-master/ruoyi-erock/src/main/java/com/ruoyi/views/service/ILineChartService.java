package com.ruoyi.views.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ruoyi.views.domain.LineChart;

/**
 * 查询可视化折线图数据Service接口
 * 
 * @author ljy
 * @date 2024-03-21
 */
public interface ILineChartService 
{
    /**
     * 查询查询可视化折线图数据
     * 
     * @param stuId 查询可视化折线图数据主键
     * @return 查询可视化折线图数据
     */
    public  ArrayList<HashMap<String, List>>  selectLineChartByStuId(Long stuId , Long enumId);

    /**
     * 查询查询可视化折线图数据列表
     * 
     * @param lineChart 查询可视化折线图数据
     * @return 查询可视化折线图数据集合
     */
    public List<LineChart> selectLineChartList(LineChart lineChart);

    /**
     * 新增查询可视化折线图数据
     * 
     * @param lineChart 查询可视化折线图数据
     * @return 结果
     */
    public int insertLineChart(LineChart lineChart);

    /**
     * 修改查询可视化折线图数据
     * 
     * @param lineChart 查询可视化折线图数据
     * @return 结果
     */
    public int updateLineChart(LineChart lineChart);

    /**
     * 批量删除查询可视化折线图数据
     * 
     * @param stuIds 需要删除的查询可视化折线图数据主键集合
     * @return 结果
     */
    public int deleteLineChartByStuIds(Long[] stuIds);

    /**
     * 删除查询可视化折线图数据信息
     * 
     * @param stuId 查询可视化折线图数据主键
     * @return 结果
     */
    public int deleteLineChartByStuId(Long stuId);
}
