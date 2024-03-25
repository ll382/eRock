package com.ruoyi;


import com.ruoyi.visualization.domain.HistogramLineChart;
import com.ruoyi.visualization.mapper.HistogramLineChartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class RuoYiApplicationTests {

    @Autowired
    private HistogramLineChartMapper histogramLineChartMapper;

    @Test
    public void s() {
        List<HistogramLineChart> s = histogramLineChartMapper.selectHistogramLineChartByStuNameAndEnumId("赵桂兰", 1l);
        System.out.println(s);
    }

}
