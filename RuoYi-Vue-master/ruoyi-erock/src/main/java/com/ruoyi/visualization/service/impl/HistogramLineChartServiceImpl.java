package com.ruoyi.visualization.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.visualization.mapper.HistogramLineChartMapper;
import com.ruoyi.visualization.domain.HistogramLineChart;
import com.ruoyi.visualization.service.IHistogramLineChartService;

/**
 * PC段第二页面Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-25
 */
@Service
public class HistogramLineChartServiceImpl implements IHistogramLineChartService 
{
    @Autowired
    private HistogramLineChartMapper histogramLineChartMapper;

    /**
     * 查询PC段第二页面
     * 
     * @param stuName PC段第二页面主键
     * @return PC段第二页面
     */
    @Override
    public ArrayList<HashMap<String,ArrayList>> selectHistogramLineChartByStuName(String stuName)
    {
        ArrayList<HashMap<String,ArrayList>> hashMaps = new ArrayList<>();
        //自己定义的前后端交互格式
        HashMap<String, ArrayList> stringListHashMap = new HashMap<>();
        //存放日期
        ArrayList<String> dateList = new ArrayList<String>();
        //存放次数
        ArrayList<Integer> ratingFrequencyList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<HistogramLineChart> histogramLineCharts = histogramLineChartMapper.selectHistogramLineChartByStuNameAndEnumId(stuName, 1l);
        for (HistogramLineChart histogramLineChart :histogramLineCharts){
            String format = sdf.format(histogramLineChart.getCrDate());
            dateList.add(format);
        }

        HistogramLineChart histogramLineChart = new HistogramLineChart();
        for (String date :dateList){
            histogramLineChart.setStuName(stuName);
            histogramLineChart.setEnumId(1l);
            try {
                histogramLineChart.setCrDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<HistogramLineChart> histogramLineCharts1 = histogramLineChartMapper.selectHistogramLineChartList(histogramLineChart);
            ratingFrequencyList.add(histogramLineCharts1.size());
        }


        stringListHashMap.put("xAxis",dateList);
        stringListHashMap.put("series",ratingFrequencyList);
        hashMaps.add(stringListHashMap);



        return hashMaps;
    }

    /**
     * 查询PC段第二页面列表
     * 
     * @param histogramLineChart PC段第二页面
     * @return PC段第二页面
     */
    @Override
    public List<HistogramLineChart> selectHistogramLineChartList(HistogramLineChart histogramLineChart)
    {
        return histogramLineChartMapper.selectHistogramLineChartList(histogramLineChart);
    }

    /**
     * 新增PC段第二页面
     * 
     * @param histogramLineChart PC段第二页面
     * @return 结果
     */
    @Override
    public int insertHistogramLineChart(HistogramLineChart histogramLineChart)
    {
        return histogramLineChartMapper.insertHistogramLineChart(histogramLineChart);
    }

    /**
     * 修改PC段第二页面
     * 
     * @param histogramLineChart PC段第二页面
     * @return 结果
     */
    @Override
    public int updateHistogramLineChart(HistogramLineChart histogramLineChart)
    {
        return histogramLineChartMapper.updateHistogramLineChart(histogramLineChart);
    }

    /**
     * 批量删除PC段第二页面
     * 
     * @param stuNames 需要删除的PC段第二页面主键
     * @return 结果
     */
    @Override
    public int deleteHistogramLineChartByStuNames(String[] stuNames)
    {
        return histogramLineChartMapper.deleteHistogramLineChartByStuNames(stuNames);
    }

    /**
     * 删除PC段第二页面信息
     * 
     * @param stuName PC段第二页面主键
     * @return 结果
     */
    @Override
    public int deleteHistogramLineChartByStuName(String stuName)
    {
        return histogramLineChartMapper.deleteHistogramLineChartByStuName(stuName);
    }
}
