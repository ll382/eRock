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
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生Controller
 *
 * @author heye
 * @date 2024-03-17
 */
@Api("核心模块学生")
@RestController
@RequestMapping("/core/student")
public class StudentController extends BaseController
{
    @Autowired
    private IStudentService studentService;

    /**
     * 查询学生列表
     */
    @ApiOperation("查询学生列表")
    @PreAuthorize("@ss.hasPermi('core:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 导出学生列表
     */
    @ApiOperation("导出学生列表")
    @PreAuthorize("@ss.hasPermi('core:student:export')")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Student student)
    {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        util.exportExcel(response, list, "学生数据");
    }

    /**
     * 获取学生详细信息
     */
    @ApiOperation("获取学生详细信息")
    @PreAuthorize("@ss.hasPermi('core:student:query')")
    @GetMapping(value = "/{stuId}")
    public AjaxResult getInfo(@PathVariable("stuId") Long stuId)
    {
        return success(studentService.selectStudentByStuId(stuId));
    }

    /**
     * 新增学生
     */
    @ApiOperation("新增学生")
    @PreAuthorize("@ss.hasPermi('core:student:add')")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Student student)
    {
        return toAjax(studentService.insertStudent(student));
    }

    /**
     * 修改学生
     */
    @ApiOperation("修改学生")
    @PreAuthorize("@ss.hasPermi('core:student:edit')")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Student student)
    {
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生
     */
    @ApiOperation("删除学生")
    @PreAuthorize("@ss.hasPermi('core:student:remove')")
    @Log(title = "学生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuIds}")
    public AjaxResult remove(@PathVariable Long[] stuIds)
    {
        return toAjax(studentService.deleteStudentByStuIds(stuIds));
    }
}
