package com.ruoyi.controller.InClassModule;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.core.service.SelectUser;
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
import com.ruoyi.InClassModule.domain.A2A3PhysicalTest;
import com.ruoyi.InClassModule.service.IA2A3PhysicalTestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2 A3体能测试Controller
 *
 * @author heye
 * @date 2024-04-14
 */
@Api(tags = {"A2 A3体能测试"})
@RestController
@RequestMapping("/staminaTask/test")
public class A2A3PhysicalTestController extends BaseController
{
    @Autowired
    private IA2A3PhysicalTestService a2A3PhysicalTestService;

    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 A3体能测试列表
     */
    @ApiOperation("查询A2 A3体能测试列表")
    @PreAuthorize("@ss.hasPermi('staminaTask:test:list')")
    @GetMapping("/list")
    public TableDataInfo list(A2A3PhysicalTest a2A3PhysicalTest)
    {
        startPage();
        List<A2A3PhysicalTest> list = a2A3PhysicalTestService.selectA2A3PhysicalTestList(a2A3PhysicalTest);
        return getDataTable(list);
    }

    /**
     * 导出A2 A3体能测试列表
     */
    @ApiOperation("导出A2 A3体能测试列表")
    @PreAuthorize("@ss.hasPermi('staminaTask:test:export')")
    @Log(title = "A2 A3体能测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2A3PhysicalTest a2A3PhysicalTest)
    {
        List<A2A3PhysicalTest> list = a2A3PhysicalTestService.selectA2A3PhysicalTestList(a2A3PhysicalTest);
        ExcelUtil<A2A3PhysicalTest> util = new ExcelUtil<A2A3PhysicalTest>(A2A3PhysicalTest.class);
        util.exportExcel(response, list, "A2 A3体能测试数据");
    }

    /**
     * 获取A2 A3体能测试详细信息
     */
    @ApiOperation("获取A2 A3体能测试详细信息")
    @PreAuthorize("@ss.hasPermi('staminaTask:test:query')")
    @GetMapping(value = "/{phyId}")
    public AjaxResult getInfo(@PathVariable("phyId") Long phyId)
    {
        startPage();
        return success(a2A3PhysicalTestService.selectA2A3PhysicalTestByPhyId(phyId));
    }

    /**
     * 新增A2 A3体能测试
     */
    @ApiOperation("新增A2 A3体能测试")
    @PreAuthorize("@ss.hasPermi('staminaTask:test:add')")
    @Log(title = "A2 A3体能测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2A3PhysicalTest a2A3PhysicalTest)
    {
        return toAjax(a2A3PhysicalTestService.insertA2A3PhysicalTest(a2A3PhysicalTest));
    }

    /**
     * 修改A2 A3体能测试
     */
    @ApiOperation("修改A2 A3体能测试")
    @PreAuthorize("@ss.hasPermi('staminaTask:test:edit')")
    @Log(title = "A2 A3体能测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2A3PhysicalTest a2A3PhysicalTest)
    {
        return toAjax(a2A3PhysicalTestService.updateA2A3PhysicalTest(a2A3PhysicalTest));
    }

    /**
     * 删除A2 A3体能测试
     */
    @ApiOperation("删除A2 A3体能测试")
    @PreAuthorize("@ss.hasPermi('staminaTask:test:remove')")
    @Log(title = "A2 A3体能测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{phyIds}")
    public AjaxResult remove(@PathVariable Long[] phyIds)
    {
        return toAjax(a2A3PhysicalTestService.deleteA2A3PhysicalTestByPhyIds(phyIds));
    }
}
