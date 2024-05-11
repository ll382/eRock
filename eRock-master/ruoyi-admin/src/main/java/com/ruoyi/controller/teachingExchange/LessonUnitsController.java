package com.ruoyi.controller.teachingExchange;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.teachingExchange.domain.TeachingTable;
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
import com.ruoyi.teachingExchange.domain.LessonUnits;
import com.ruoyi.teachingExchange.service.ILessonUnitsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课时单元Controller
 *
 * @author heye
 * @date 2024-04-01
 */
@Api(tags = {"课时单元"})
@RestController
@RequestMapping("/unit/units")
public class LessonUnitsController extends BaseController
{
    @Autowired
    private ILessonUnitsService lessonUnitsService;

    /**
     * 查询课时单元列表
     */
    @ApiOperation("查询课时单元列表")
    @PreAuthorize("@ss.hasPermi('unit:units:list')")
    @GetMapping("/list")
    public TableDataInfo list(LessonUnits lessonUnits)
    {
//        startPage();
        List<LessonUnits> list = lessonUnitsService.selectLessonUnitsList(lessonUnits);
        return getDataTable(list);
    }

    /**
     * 导出课时单元列表
     */
    @ApiOperation("导出课时单元列表")
    @PreAuthorize("@ss.hasPermi('unit:units:export')")
    @Log(title = "课时单元", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LessonUnits lessonUnits)
    {
        List<LessonUnits> list = lessonUnitsService.selectLessonUnitsList(lessonUnits);
        ExcelUtil<LessonUnits> util = new ExcelUtil<LessonUnits>(LessonUnits.class);
        util.exportExcel(response, list, "课时单元数据");
    }

    /**
     * 获取课时单元详细信息
     */
    @ApiOperation("获取课时单元详细信息")
    @PreAuthorize("@ss.hasPermi('unit:units:query')")
    @GetMapping(value = "/{lesId}")
    public AjaxResult getInfo(@PathVariable("lesId") Long lesId)
    {
        return success(lessonUnitsService.selectLessonUnitsByLesId(lesId));
    }

    /**
     * 新增课时单元
     */
    @ApiOperation("新增课时单元")
    @PreAuthorize("@ss.hasPermi('unit:units:add')")
    @Log(title = "课时单元", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LessonUnits lessonUnits)
    {
        return toAjax(lessonUnitsService.insertLessonUnits(lessonUnits));
    }

    /**
     * 新增课时
     */
    @ApiOperation("新增课时")
    @PreAuthorize("@ss.hasPermi('unit:units:add')")
    @Log(title = "课时单元", businessType = BusinessType.INSERT)
    @PostMapping("/iunit")
    public AjaxResult add(@RequestBody TeachingTable teachingTable)
    {
        return toAjax(lessonUnitsService.insertUnitsTable(teachingTable));
    }

    /**
     * 修改课时单元
     */
    @ApiOperation("修改课时单元")
    @PreAuthorize("@ss.hasPermi('unit:units:edit')")
    @Log(title = "课时单元", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LessonUnits lessonUnits)
    {
        return toAjax(lessonUnitsService.updateLessonUnits(lessonUnits));
    }

    /**
     * 删除课时单元
     */
    @ApiOperation("删除课时单元")
    @PreAuthorize("@ss.hasPermi('unit:units:remove')")
    @Log(title = "课时单元", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lesIds}")
    public AjaxResult remove(@PathVariable Long[] lesIds)
    {
        return toAjax(lessonUnitsService.deleteLessonUnitsByLesIds(lesIds));
    }
}
