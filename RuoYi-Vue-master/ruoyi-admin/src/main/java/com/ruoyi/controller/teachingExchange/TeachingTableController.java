package com.ruoyi.controller.teachingExchange;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.teachingExchange.domain.TeachingTable;
import com.ruoyi.teachingExchange.service.ITeachingTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.commons.io.file.FilesUncheck.list;

/**
 * A1 线上学习 教学
 Controller
 *
 * @author heye
 * @date 2024-03-20
 */
@Api(tags = {"A1 线上学习 教学 "})
@RestController
@RequestMapping("/teachingExchange/table")
public class TeachingTableController extends BaseController
{
    @Autowired
    private ITeachingTableService teachingTableService;

    /**
     * 查询A1 线上学习 教学
 列表
     */
    @ApiOperation("查询A1 线上学习 教学列表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:table:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachingTable teachingTable)
    {
        startPage();
        List<TeachingTable> list = teachingTableService.selectTeachingTableList(teachingTable);
        return getDataTable(list);
    }

    /**
     * 导出A1 线上学习 教学
 列表
     */
    @ApiOperation("导出A1 线上学习 教学列表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:table:export')")
    @Log(title = "A1 线上学习 教学 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachingTable teachingTable)
    {
        List<TeachingTable> list = teachingTableService.selectTeachingTableList(teachingTable);
        ExcelUtil<TeachingTable> util = new ExcelUtil<TeachingTable>(TeachingTable.class);
        util.exportExcel(response, list, "A1 线上学习 教学数据");
    }

    /**
     * 获取A1 线上学习 教学
 详细信息
     */
    @ApiOperation("获取A1 线上学习 教学详细信息")
    @PreAuthorize("@ss.hasPermi('teachingExchange:table:query')")
    @GetMapping(value = "/{teachingId}")
    public AjaxResult getInfo(@PathVariable("teachingId") Long teachingId)
    {
        return success(teachingTableService.selectTeachingTableByTeachingId(teachingId));
    }

    /**
     * 新增A1 线上学习 教学

     */
    @ApiOperation("新增A1 线上学习 教学")
    @PreAuthorize("@ss.hasPermi('teachingExchange:table:add')")
    @Log(title = "A1 线上学习 教学", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachingTable teachingTable)
    {
        return toAjax(teachingTableService.insertTeachingTable(teachingTable));
    }

    /**
     * 修改A1 线上学习 教学

     */
    @ApiOperation("修改A1 线上学习 教学")
    @PreAuthorize("@ss.hasPermi('teachingExchange:table:edit')")
    @Log(title = "A1 线上学习 教学", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachingTable teachingTable)
    {
        return toAjax(teachingTableService.updateTeachingTable(teachingTable));
    }

    /**
     * 删除A1 线上学习 教学

     */
    @ApiOperation("删除A1 线上学习 教学")
    @PreAuthorize("@ss.hasPermi('teachingExchange:table:remove')")
    @Log(title = "A1 线上学习 教学", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teachingIds}")
    public AjaxResult remove(@PathVariable Long[] teachingIds)
    {
        return toAjax(teachingTableService.deleteTeachingTableByTeachingIds(teachingIds));
    }
}
