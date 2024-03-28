package com.ruoyi.controller.visualization;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.HttpStatus;
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
import com.ruoyi.visualization.domain.StudentCourseGrades;
import com.ruoyi.visualization.service.IStudentCourseGradesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * PC段首页Controller
 *
 * @author ljy
 * @date 2024-03-22
 */
@Api(tags = {"PC段首页"})
@RestController
@RequestMapping("/visualization/grades")
public class StudentCourseGradesController extends BaseController
{
    @Autowired
    private IStudentCourseGradesService studentCourseGradesService;

    /**
     * 查询PC段首页列表
     */
    @ApiOperation("查询PC段首页列表")
    @PreAuthorize("@ss.hasPermi('visualization:grades:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<StudentCourseGrades> list = studentCourseGradesService.selectStudentCourseGradesList();
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(40);
        return rspData;
    }

    /**
     * 导出PC段首页列表
     */
    @ApiOperation("导出PC段首页列表")
    @PreAuthorize("@ss.hasPermi('visualization:grades:export')")
    @Log(title = "PC段首页", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentCourseGrades studentCourseGrades)
    {
        List<StudentCourseGrades> list = studentCourseGradesService.selectStudentCourseGradesList();
        ExcelUtil<StudentCourseGrades> util = new ExcelUtil<StudentCourseGrades>(StudentCourseGrades.class);
        util.exportExcel(response, list, "PC段首页数据");
    }

    /**
     * 获取PC段首页详细信息
     */
    @ApiOperation("获取PC段首页详细信息")
    @PreAuthorize("@ss.hasPermi('visualization:grades:query')")
    @GetMapping(value = "/{stuName}")
    public AjaxResult getInfo(@PathVariable("stuName") String stuName)
    {
        return success(studentCourseGradesService.selectStudentCourseGradesByStuName(stuName));
    }

    /**
     * 新增PC段首页
     */
    @ApiOperation("新增PC段首页")
    @PreAuthorize("@ss.hasPermi('visualization:grades:add')")
    @Log(title = "PC段首页", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentCourseGrades studentCourseGrades)
    {
        return toAjax(studentCourseGradesService.insertStudentCourseGrades(studentCourseGrades));
    }

    /**
     * 修改PC段首页
     */
    @ApiOperation("修改PC段首页")
    @PreAuthorize("@ss.hasPermi('visualization:grades:edit')")
    @Log(title = "PC段首页", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentCourseGrades studentCourseGrades)
    {
        return toAjax(studentCourseGradesService.updateStudentCourseGrades(studentCourseGrades));
    }

    /**
     * 删除PC段首页
     */
    @ApiOperation("删除PC段首页")
    @PreAuthorize("@ss.hasPermi('visualization:grades:remove')")
    @Log(title = "PC段首页", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuNames}")
    public AjaxResult remove(@PathVariable String[] stuNames)
    {
        return toAjax(studentCourseGradesService.deleteStudentCourseGradesByStuNames(stuNames));
    }
}
