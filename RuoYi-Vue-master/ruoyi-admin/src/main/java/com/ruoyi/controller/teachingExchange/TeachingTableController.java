package com.ruoyi.controller.teachingExchange;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.teachingExchange.domain.A1Communication;
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
import com.ruoyi.teachingExchange.domain.TeachingTable;
import com.ruoyi.teachingExchange.service.ITeachingTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A1 线上学习学生线上观看记录表Controller
 *
 * @author heye
 * @date 2024-03-20
 */
@Api(tags = {"A1 线上学习学生线上观看记录表"})
@RestController
@RequestMapping("/teachingExchange/teachingExchange")
public class TeachingTableController extends BaseController
{
    @Autowired
    private ITeachingTableService teachingTableService;

    /**
     * 查询A1 线上学习学生线上观看记录表列表
     */
    @ApiOperation("查询A1 线上学习学生线上观看记录表列表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachingTable teachingTable)
    {
        startPage();
        List<TeachingTable> list = teachingTableService.selectTeachingTableList(teachingTable);
        return getDataTable(list);
    }

    /**
     * 查询A1 线上学习学生线上观看记录表  评论列表
     */
    @ApiOperation("查询A1 线上学习学生线上观看记录表   评论列表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:list')")
    @GetMapping("/comm/{commId}")
    public AjaxResult A1Communicationlist(@PathVariable("commId")String commId)
    {
//        startPage();
        Long com = Long.parseLong(commId);
        TeachingTable list = teachingTableService.selectCommunicationList(com);
        return success(list);
    }

    /**
     * 导出A1 线上学习学生线上观看记录表列表
     */
    @ApiOperation("导出A1 线上学习学生线上观看记录表列表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:export')")
    @Log(title = "A1 线上学习学生线上观看记录表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachingTable teachingTable)
    {
        List<TeachingTable> list = teachingTableService.selectTeachingTableList(teachingTable);
        ExcelUtil<TeachingTable> util = new ExcelUtil<TeachingTable>(TeachingTable.class);
        util.exportExcel(response, list, "A1 线上学习学生线上观看记录表数据");
    }

    /**
     * 获取A1 线上学习学生线上观看记录表详细信息
     */
    @ApiOperation("获取A1 线上学习学生线上观看记录表详细信息")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:query')")
    @GetMapping(value = "/{teachingId}")
    public AjaxResult getInfo(@PathVariable("teachingId") Long teachingId)
    {
        return success(teachingTableService.selectTeachingTableByTeachingId(teachingId));
    }

    /**
     * 新增A1 线上学习学生线上观看记录表
     */
    @ApiOperation("新增A1 线上学习学生线上观看记录表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:add')")
    @Log(title = "A1 线上学习学生线上观看记录表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachingTable teachingTable)
    {
        return toAjax(teachingTableService.insertTeachingTable(teachingTable));
    }

    /**
     * 修改A1 线上学习学生线上观看记录表
     */
    @ApiOperation("修改A1 线上学习学生线上观看记录表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:edit')")
    @Log(title = "A1 线上学习学生线上观看记录表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachingTable teachingTable)
    {
        return toAjax(teachingTableService.updateTeachingTable(teachingTable));
    }

    /**
     * 删除A1 线上学习学生线上观看记录表
     */
    @ApiOperation("删除A1 线上学习学生线上观看记录表")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:remove')")
    @Log(title = "A1 线上学习学生线上观看记录表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teachingIds}")
    public AjaxResult remove(@PathVariable Long[] teachingIds)
    {
        return toAjax(teachingTableService.deleteTeachingTableByTeachingIds(teachingIds));
    }
}
