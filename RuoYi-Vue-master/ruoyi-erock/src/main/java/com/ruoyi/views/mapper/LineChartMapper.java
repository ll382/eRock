package com.ruoyi.views.mapper;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.views.domain.LineChart;
import org.apache.ibatis.annotations.Param;

/**
 * 查询可视化折线图数据Mapper接口
 * 
 * @author ljy
 * @date 2024-03-21
 */
public interface LineChartMapper 
{
    /**
     * 查询查询可视化折线图数据
     * 
     * @param stuId 查询可视化折线图数据主键
     * @return 查询可视化折线图数据
     */
    public ArrayList<LineChart> selectLineChartByStuId(@Param("stuId") Long stuId, @Param("enumId") Long enumId);

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
     * 删除查询可视化折线图数据
     * 
     * @param stuId 查询可视化折线图数据主键
     * @return 结果
     */
    public int deleteLineChartByStuId(Long stuId);

    /**
     * 批量删除查询可视化折线图数据
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLineChartByStuIds(Long[] stuIds);
}
