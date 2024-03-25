package com.ruoyi.controller.visualization;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.visualization.domain.HistogramLineChart;
import com.ruoyi.visualization.service.IHistogramLineChartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * PC段第二页面Controller
 *
 * @author ljy
 * @date 2024-03-25
 */
@Api(tags = {"PC段第二页面"})
@RestController
@RequestMapping("/visualization/HistogramLineChart")
public class HistogramLineChartController extends BaseController
{
    @Autowired
    private IHistogramLineChartService histogramLineChartService;

    /**
     * 查询PC段第二页面列表
     */
    @ApiOperation("查询PC段第二页面列表")
    @PreAuthorize("@ss.hasPermi('visualization:HistogramLineChart:list')")
    @GetMapping("/list")
    public TableDataInfo list(HistogramLineChart histogramLineChart)
    {
        startPage();
        List<HistogramLineChart> list = histogramLineChartService.selectHistogramLineChartList(histogramLineChart);
        return getDataTable(list);
    }

    /**
     * 导出PC段第二页面列表
     */
    @ApiOperation("导出PC段第二页面列表")
    @PreAuthorize("@ss.hasPermi('visualization:HistogramLineChart:export')")
    @Log(title = "PC段第二页面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HistogramLineChart histogramLineChart)
    {
        List<HistogramLineChart> list = histogramLineChartService.selectHistogramLineChartList(histogramLineChart);
        ExcelUtil<HistogramLineChart> util = new ExcelUtil<HistogramLineChart>(HistogramLineChart.class);
        util.exportExcel(response, list, "PC段第二页面数据");
    }

    /**
     * 获取PC段第二页面详细信息
     */
    @ApiOperation("获取PC段第二页面详细信息")
    @PreAuthorize("@ss.hasPermi('visualization:HistogramLineChart:query')")
    @GetMapping(value = "/{stuName}")
    public AjaxResult getInfo(@PathVariable("stuName") String stuName)
    {
        return success(histogramLineChartService.selectHistogramLineChartByStuName(stuName));
    }

    /**
     * 新增PC段第二页面
     */
    @ApiOperation("新增PC段第二页面")
    @PreAuthorize("@ss.hasPermi('visualization:HistogramLineChart:add')")
    @Log(title = "PC段第二页面", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HistogramLineChart histogramLineChart)
    {
        return toAjax(histogramLineChartService.insertHistogramLineChart(histogramLineChart));
    }

    /**
     * 修改PC段第二页面
     */
    @ApiOperation("修改PC段第二页面")
    @PreAuthorize("@ss.hasPermi('visualization:HistogramLineChart:edit')")
    @Log(title = "PC段第二页面", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HistogramLineChart histogramLineChart)
    {
        return toAjax(histogramLineChartService.updateHistogramLineChart(histogramLineChart));
    }

    /**
     * 删除PC段第二页面
     */
    @ApiOperation("删除PC段第二页面")
    @PreAuthorize("@ss.hasPermi('visualization:HistogramLineChart:remove')")
    @Log(title = "PC段第二页面", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuNames}")
    public AjaxResult remove(@PathVariable String[] stuNames)
    {
        return toAjax(histogramLineChartService.deleteHistogramLineChartByStuNames(stuNames));
    }
}
