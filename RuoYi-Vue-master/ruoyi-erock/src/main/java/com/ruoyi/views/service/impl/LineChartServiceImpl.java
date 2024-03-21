package com.ruoyi.views.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.views.mapper.LineChartMapper;
import com.ruoyi.views.domain.LineChart;
import com.ruoyi.views.service.ILineChartService;

/**
 * 查询可视化折线图数据Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-21
 */
@Service
public class LineChartServiceImpl implements ILineChartService 
{
    @Autowired
    private LineChartMapper lineChartMapper;

    /**
     * 查询查询可视化折线图数据
     * 
     * @param stuId 查询可视化折线图数据主键
     * @return 查询可视化折线图数据
     */
    @Override
    public  ArrayList<HashMap<String, List>>  selectLineChartByStuId(Long stuId , Long enumId)
    {
        ArrayList<LineChart> lineChart = lineChartMapper.selectLineChartByStuId(stuId,enumId);
        ArrayList<HashMap<String,List>> list = new ArrayList<HashMap<String,List>>();
        //自己定义的前后端交互格式
        HashMap<String, List> xAxisHashMap = new HashMap<>();
        //crDateList里面存放日期
        ArrayList<String> crDateList = new ArrayList<>();

        //EROCKDate里面存放EROCK分数
        ArrayList<BigDecimal> EROCKDate = new ArrayList<>();

        //EROCKList里面存放EROCK分数等
        ArrayList<JSONObject> EROCKList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (LineChart LineCharts : lineChart){
            String format = sdf.format(LineCharts.getCrDate());
            crDateList.add(format);
            //添加EROCK分数
            EROCKDate.add(LineCharts.getMsScore());

            JSONObject EROCKJsonObject = new JSONObject();
            EROCKJsonObject.put("value",LineCharts.getMsScore());
            EROCKJsonObject.put("xAxis",format);
            EROCKJsonObject.put("yAxis",LineCharts.getMsScore());
            EROCKList.add(EROCKJsonObject);
        }
        xAxisHashMap.put("xAxis", crDateList);
        xAxisHashMap.put("EROCKScore",EROCKDate);
        xAxisHashMap.put("EROCKList",EROCKList);
        list.add(xAxisHashMap);
        return list;
    }

    /**
     * 查询查询可视化折线图数据列表
     * 
     * @param lineChart 查询可视化折线图数据
     * @return 查询可视化折线图数据
     */
    @Override
    public List<LineChart> selectLineChartList(LineChart lineChart)
    {
        return lineChartMapper.selectLineChartList(lineChart);
    }

    /**
     * 新增查询可视化折线图数据
     * 
     * @param lineChart 查询可视化折线图数据
     * @return 结果
     */
    @Override
    public int insertLineChart(LineChart lineChart)
    {
        return lineChartMapper.insertLineChart(lineChart);
    }

    /**
     * 修改查询可视化折线图数据
     * 
     * @param lineChart 查询可视化折线图数据
     * @return 结果
     */
    @Override
    public int updateLineChart(LineChart lineChart)
    {
        return lineChartMapper.updateLineChart(lineChart);
    }

    /**
     * 批量删除查询可视化折线图数据
     * 
     * @param stuIds 需要删除的查询可视化折线图数据主键
     * @return 结果
     */
    @Override
    public int deleteLineChartByStuIds(Long[] stuIds)
    {
        return lineChartMapper.deleteLineChartByStuIds(stuIds);
    }

    /**
     * 删除查询可视化折线图数据信息
     * 
     * @param stuId 查询可视化折线图数据主键
     * @return 结果
     */
    @Override
    public int deleteLineChartByStuId(Long stuId)
    {
        return lineChartMapper.deleteLineChartByStuId(stuId);
    }
}
