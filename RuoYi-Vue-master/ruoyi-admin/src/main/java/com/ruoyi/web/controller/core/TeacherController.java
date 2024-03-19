package com.ruoyi.web.controller.core;

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
import com.ruoyi.core.domain.Teacher;
import com.ruoyi.core.service.ITeacherService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 老师Controller
 *
 * @author heye
 * @date 2024-03-17
 */
@Api("核心模块老师")
@RestController
@RequestMapping("/core/teacher")
public class TeacherController extends BaseController
{
    @Autowired
    private ITeacherService teacherService;

    /**
     * 查询老师列表
     */
    @ApiOperation("查询老师列表")
    @PreAuthorize("@ss.hasPermi('core:teacher:list')")
    @GetMapping("/list")
    public TableDataInfo list(Teacher teacher)
    {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return getDataTable(list);
    }

    /**
     * 导出老师列表
     */
    @ApiOperation("导出老师列表")
    @PreAuthorize("@ss.hasPermi('core:teacher:export')")
    @Log(title = "老师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Teacher teacher)
    {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        util.exportExcel(response, list, "老师数据");
    }

    /**
     * 获取老师详细信息
     */
    @ApiOperation("获取老师详细信息")
    @PreAuthorize("@ss.hasPermi('core:teacher:query')")
    @GetMapping(value = "/{teaId}")
    public AjaxResult getInfo(@PathVariable("teaId") Long teaId)
    {
        return success(teacherService.selectTeacherByTeaId(teaId));
    }

    /**
     * 新增老师
     */
    @ApiOperation("新增老师")
    @PreAuthorize("@ss.hasPermi('core:teacher:add')")
    @Log(title = "老师", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.insertTeacher(teacher));
    }

    /**
     * 修改老师
     */
    @ApiOperation("修改老师")
    @PreAuthorize("@ss.hasPermi('core:teacher:edit')")
    @Log(title = "老师", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.updateTeacher(teacher));
    }

    /**
     * 删除老师
     */
    @ApiOperation("删除老师")
    @PreAuthorize("@ss.hasPermi('core:teacher:remove')")
    @Log(title = "老师", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teaIds}")
    public AjaxResult remove(@PathVariable Long[] teaIds)
    {
        return toAjax(teacherService.deleteTeacherByTeaIds(teaIds));
    }
}
