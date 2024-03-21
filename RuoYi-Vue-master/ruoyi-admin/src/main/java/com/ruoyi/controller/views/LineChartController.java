package com.ruoyi.controller.views;

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
import com.ruoyi.views.domain.LineChart;
import com.ruoyi.views.service.ILineChartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 查询可视化折线图数据Controller
 *
 * @author ljy
 * @date 2024-03-21
 */
@Api(tags = {"查询可视化折线图数据"})
@RestController
@RequestMapping("/view/LineChart")
public class LineChartController extends BaseController
{
    @Autowired
    private ILineChartService lineChartService;

    /**
     * 查询查询可视化折线图数据列表
     */
    @ApiOperation("查询查询可视化折线图数据列表")
    @PreAuthorize("@ss.hasPermi('view:LineChart:list')")
    @GetMapping("/list")
    public TableDataInfo list(LineChart lineChart)
    {
        startPage();
        List<LineChart> list = lineChartService.selectLineChartList(lineChart);
        return getDataTable(list);
    }

    /**
     * 导出查询可视化折线图数据列表
     */
    @ApiOperation("导出查询可视化折线图数据列表")
    @PreAuthorize("@ss.hasPermi('view:LineChart:export')")
    @Log(title = "查询可视化折线图数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LineChart lineChart)
    {
        List<LineChart> list = lineChartService.selectLineChartList(lineChart);
        ExcelUtil<LineChart> util = new ExcelUtil<LineChart>(LineChart.class);
        util.exportExcel(response, list, "查询可视化折线图数据数据");
    }

    /**
     * 获取查询可视化折线图数据详细信息
     */
    @ApiOperation("获取查询可视化折线图数据详细信息")
    @PreAuthorize("@ss.hasPermi('view:LineChart:query')")
    @GetMapping(value = "/{stuId}/{enumId}")
    public AjaxResult getInfo(@PathVariable("stuId") Long stuId, @PathVariable("enumId")  Long enumId)
    {
        return success(lineChartService.selectLineChartByStuId(stuId,enumId));
    }

    /**
     * 新增查询可视化折线图数据
     */
    @ApiOperation("新增查询可视化折线图数据")
    @PreAuthorize("@ss.hasPermi('view:LineChart:add')")
    @Log(title = "查询可视化折线图数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LineChart lineChart)
    {
        return toAjax(lineChartService.insertLineChart(lineChart));
    }

    /**
     * 修改查询可视化折线图数据
     */
    @ApiOperation("修改查询可视化折线图数据")
    @PreAuthorize("@ss.hasPermi('view:LineChart:edit')")
    @Log(title = "查询可视化折线图数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LineChart lineChart)
    {
        return toAjax(lineChartService.updateLineChart(lineChart));
    }

    /**
     * 删除查询可视化折线图数据
     */
    @ApiOperation("删除查询可视化折线图数据")
    @PreAuthorize("@ss.hasPermi('view:LineChart:remove')")
    @Log(title = "查询可视化折线图数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuIds}")
    public AjaxResult remove(@PathVariable Long[] stuIds)
    {
        return toAjax(lineChartService.deleteLineChartByStuIds(stuIds));
    }
}
