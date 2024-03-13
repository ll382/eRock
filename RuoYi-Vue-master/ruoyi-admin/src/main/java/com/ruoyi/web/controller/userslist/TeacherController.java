package com.ruoyi.web.controller.userslist;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.userslist.domain.Teacher;
import com.ruoyi.userslist.service.ITeacherService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 老师Controller
 *
 * @author ruoyi
 * @date 2024-03-13
 */
@RestController
@RequestMapping("/userslist/teacher")
public class TeacherController extends BaseController
{
    @Autowired
    private ITeacherService teacherService;

    /**
     * 查询老师列表
     */
    @PreAuthorize("@ss.hasPermi('userslist:teacher:list')")
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
    @PreAuthorize("@ss.hasPermi('userslist:teacher:export')")
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
    @PreAuthorize("@ss.hasPermi('userslist:teacher:query')")
    @GetMapping(value = "/{teaId}")
    public AjaxResult getInfo(@PathVariable("teaId") String teaId)
    {
        return success(teacherService.selectTeacherByTeaId(teaId));
    }

    /**
     * 新增老师
     */
    @PreAuthorize("@ss.hasPermi('userslist:teacher:add')")
    @Log(title = "老师", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.insertTeacher(teacher));
    }

    /**
     * 修改老师
     */
    @PreAuthorize("@ss.hasPermi('userslist:teacher:edit')")
    @Log(title = "老师", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.updateTeacher(teacher));
    }

    /**
     * 删除老师
     */
    @PreAuthorize("@ss.hasPermi('userslist:teacher:remove')")
    @Log(title = "老师", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teaIds}")
    public AjaxResult remove(@PathVariable String[] teaIds)
    {
        return toAjax(teacherService.deleteTeacherByTeaIds(teaIds));
    }
}
