package com.ruoyi.web.controller.views;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.views.domain.StudentCourseGrades;
import com.ruoyi.views.service.IStudentCourseGradesService;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生成绩查询Controller
 * 
 * @author ljy
 * @date 2024-03-14
 */
@RestController
@RequestMapping("/view/grades")
public class StudentCourseGradesController extends BaseController
{
    @Autowired
    private IStudentCourseGradesService studentCourseGradesService;

    /**
     * 查询学生成绩查询列表
     */
    @PreAuthorize("@ss.hasPermi('view:grades:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentCourseGrades studentCourseGrades)
    {
        startPage();
        List<StudentCourseGrades> list = studentCourseGradesService.selectStudentCourseGradesList(studentCourseGrades);
        return getDataTable(list);
    }

    /**
     * 导出学生成绩查询列表
     */
    @PreAuthorize("@ss.hasPermi('view:grades:export')")
    @Log(title = "学生成绩查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentCourseGrades studentCourseGrades)
    {
        List<StudentCourseGrades> list = studentCourseGradesService.selectStudentCourseGradesList(studentCourseGrades);
        ExcelUtil<StudentCourseGrades> util = new ExcelUtil<StudentCourseGrades>(StudentCourseGrades.class);
        util.exportExcel(response, list, "学生成绩查询数据");
    }

    /**
     * 获取学生成绩查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('view:grades:query')")
    @GetMapping(value = "/{stuId}")
    public AjaxResult getInfo(@PathVariable("stuId") Long stuId)
    {
        return success(studentCourseGradesService.selectStudentCourseGradesByStuId(stuId));
    }

    /**
     * 新增学生成绩查询
     */
    @PreAuthorize("@ss.hasPermi('view:grades:add')")
    @Log(title = "学生成绩查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentCourseGrades studentCourseGrades)
    {
        return toAjax(studentCourseGradesService.insertStudentCourseGrades(studentCourseGrades));
    }

    /**
     * 修改学生成绩查询
     */
    @PreAuthorize("@ss.hasPermi('view:grades:edit')")
    @Log(title = "学生成绩查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentCourseGrades studentCourseGrades)
    {
        return toAjax(studentCourseGradesService.updateStudentCourseGrades(studentCourseGrades));
    }

    /**
     * 删除学生成绩查询
     */
    @PreAuthorize("@ss.hasPermi('view:grades:remove')")
    @Log(title = "学生成绩查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuIds}")
    public AjaxResult remove(@PathVariable Long[] stuIds)
    {
        return toAjax(studentCourseGradesService.deleteStudentCourseGradesByStuIds(stuIds));
    }
}
