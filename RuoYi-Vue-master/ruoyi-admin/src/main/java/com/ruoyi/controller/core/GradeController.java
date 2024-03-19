package com.ruoyi.controller.core;

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
import com.ruoyi.core.domain.Grade;
import com.ruoyi.core.service.IGradeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级Controller
 *
 * @author heye
 * @date 2024-03-15
 */
@Api("核心模块班级")

@RestController
@RequestMapping("/core/grade")
public class GradeController extends BaseController
{
    @Autowired
    private IGradeService gradeService;

    /**
     * 查询班级列表
     */
    @ApiOperation("查询班级列表")
    @PreAuthorize("@ss.hasPermi('core:grade:list')")
    @GetMapping("/list")
    public TableDataInfo list(Grade grade)
    {
        startPage();
        List<Grade> list = gradeService.selectGradeList(grade);
        return getDataTable(list);
    }

    /**
     * 导出班级列表
     */
    @ApiOperation("导出班级列表")
    @PreAuthorize("@ss.hasPermi('core:grade:export')")
    @Log(title = "班级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Grade grade)
    {
        List<Grade> list = gradeService.selectGradeList(grade);
        ExcelUtil<Grade> util = new ExcelUtil<Grade>(Grade.class);
        util.exportExcel(response, list, "班级数据");
    }

    /**
     * 获取班级详细信息
     */
    @ApiOperation("获取班级详细信息")
    @PreAuthorize("@ss.hasPermi('core:grade:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId)
    {
        return success(gradeService.selectGradeByClassId(classId));
    }

    /**
     * 新增班级
     */
    @ApiOperation("新增班级")
    @PreAuthorize("@ss.hasPermi('core:grade:add')")
    @Log(title = "班级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Grade grade)
    {
        return toAjax(gradeService.insertGrade(grade));
    }

    /**
     * 修改班级
     */
    @ApiOperation("修改班级")
    @PreAuthorize("@ss.hasPermi('core:grade:edit')")
    @Log(title = "班级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Grade grade)
    {
        return toAjax(gradeService.updateGrade(grade));
    }

    /**
     * 删除班级
     */
    @ApiOperation("删除班级")
    @PreAuthorize("@ss.hasPermi('core:grade:remove')")
    @Log(title = "班级", businessType = BusinessType.DELETE)
    @DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds)
    {
        return toAjax(gradeService.deleteGradeByClassIds(classIds));
    }
}
