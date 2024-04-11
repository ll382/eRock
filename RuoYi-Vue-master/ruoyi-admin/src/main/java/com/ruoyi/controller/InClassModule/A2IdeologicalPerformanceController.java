package com.ruoyi.controller.InClassModule;

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
import com.ruoyi.InClassModule.domain.A2IdeologicalPerformance;
import com.ruoyi.InClassModule.service.IA2IdeologicalPerformanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2  思政教育 思政现Controller
 *
 * @author heye
 * @date 2024-04-11
 */
@Api(tags = {"A2  思政教育 思政现"})
@RestController
@RequestMapping("/Ideological/performance")
public class A2IdeologicalPerformanceController extends BaseController
{
    @Autowired
    private IA2IdeologicalPerformanceService a2IdeologicalPerformanceService;

    /**
     * 查询A2  思政教育 思政现列表
     */
    @ApiOperation("查询A2  思政教育 思政现列表")
    @PreAuthorize("@ss.hasPermi('Ideological:performance:list')")
    @GetMapping("/list")
    public TableDataInfo list(A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        startPage();
        List<A2IdeologicalPerformance> list = a2IdeologicalPerformanceService.selectA2IdeologicalPerformanceList(a2IdeologicalPerformance);
        return getDataTable(list);
    }

    /**
     * 导出A2  思政教育 思政现列表
     */
    @ApiOperation("导出A2  思政教育 思政现列表")
    @PreAuthorize("@ss.hasPermi('Ideological:performance:export')")
    @Log(title = "A2  思政教育 思政现", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        List<A2IdeologicalPerformance> list = a2IdeologicalPerformanceService.selectA2IdeologicalPerformanceList(a2IdeologicalPerformance);
        ExcelUtil<A2IdeologicalPerformance> util = new ExcelUtil<A2IdeologicalPerformance>(A2IdeologicalPerformance.class);
        util.exportExcel(response, list, "A2  思政教育 思政现数据");
    }

    /**
     * 获取A2  思政教育 思政现详细信息
     */
    @ApiOperation("获取A2  思政教育 思政现详细信息")
    @PreAuthorize("@ss.hasPermi('Ideological:performance:query')")
    @GetMapping(value = "/{ipId}")
    public AjaxResult getInfo(@PathVariable("ipId") Long ipId)
    {
        return success(a2IdeologicalPerformanceService.selectA2IdeologicalPerformanceByIpId(ipId));
    }

    /**
     * 新增A2  思政教育 思政现
     */
    @ApiOperation("新增A2  思政教育 思政现")
    @PreAuthorize("@ss.hasPermi('Ideological:performance:add')")
    @Log(title = "A2  思政教育 思政现", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        return toAjax(a2IdeologicalPerformanceService.insertA2IdeologicalPerformance(a2IdeologicalPerformance));
    }

    /**
     * 修改A2  思政教育 思政现
     */
    @ApiOperation("修改A2  思政教育 思政现")
    @PreAuthorize("@ss.hasPermi('Ideological:performance:edit')")
    @Log(title = "A2  思政教育 思政现", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        return toAjax(a2IdeologicalPerformanceService.updateA2IdeologicalPerformance(a2IdeologicalPerformance));
    }

    /**
     * 删除A2  思政教育 思政现
     */
    @ApiOperation("删除A2  思政教育 思政现")
    @PreAuthorize("@ss.hasPermi('Ideological:performance:remove')")
    @Log(title = "A2  思政教育 思政现", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ipIds}")
    public AjaxResult remove(@PathVariable Long[] ipIds)
    {
        return toAjax(a2IdeologicalPerformanceService.deleteA2IdeologicalPerformanceByIpIds(ipIds));
    }
}
