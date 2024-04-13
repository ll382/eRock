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
import com.ruoyi.InClassModule.domain.A2Attendance;
import com.ruoyi.InClassModule.service.IA2AttendanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2 课堂表现classRegistercr         Controller
 *
 * @author heye
 * @date 2024-04-11
 */
@Api(tags = {"A2 课堂表现classRegistercr         "})
@RestController
@RequestMapping("/performance/attendance")
public class A2AttendanceController extends BaseController
{
    @Autowired
    private IA2AttendanceService a2AttendanceService;

    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 课堂表现classRegistercr         列表
     */
    @ApiOperation("查询A2 课堂表现classRegistercr         列表")
    @PreAuthorize("@ss.hasPermi('performance:attendance:list')")
    @GetMapping("/list")
    public TableDataInfo list(A2Attendance a2Attendance)
    {
        startPage();
        List<A2Attendance> list = a2AttendanceService.selectA2AttendanceList(a2Attendance);
        return getDataTable(list);
    }

    /**
     * 导出A2 课堂表现classRegistercr         列表
     */
    @ApiOperation("导出A2 课堂表现classRegistercr         列表")
    @PreAuthorize("@ss.hasPermi('performance:attendance:export')")
    @Log(title = "A2 课堂表现classRegistercr         ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2Attendance a2Attendance)
    {
        List<A2Attendance> list = a2AttendanceService.selectA2AttendanceList(a2Attendance);
        ExcelUtil<A2Attendance> util = new ExcelUtil<A2Attendance>(A2Attendance.class);
        util.exportExcel(response, list, "A2 课堂表现classRegistercr         数据");
    }

    /**
     * 获取A2 课堂表现classRegistercr         详细信息
     */
    @ApiOperation("获取A2 课堂表现classRegistercr         详细信息")
    @PreAuthorize("@ss.hasPermi('performance:attendance:query')")
    @GetMapping(value = "/{crId}")
    public AjaxResult getInfo(@PathVariable("crId") Long crId)
    {
        startPage();
        return success(selectUser.selectUndoneStudent(a2AttendanceService.selectA2AttendanceByAaId(crId)));
    }

    /**
     * 新增A2 课堂表现classRegistercr
     */
    @ApiOperation("新增A2 课堂表现classRegistercr         ")
    @PreAuthorize("@ss.hasPermi('performance:attendance:add')")
    @Log(title = "A2 课堂表现classRegistercr         ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2Attendance a2Attendance)
    {
        return toAjax(a2AttendanceService.insertA2Attendance(a2Attendance));
    }

    /**
     * 修改A2 课堂表现classRegistercr
     */
    @ApiOperation("修改A2 课堂表现classRegistercr         ")
    @PreAuthorize("@ss.hasPermi('performance:attendance:edit')")
    @Log(title = "A2 课堂表现classRegistercr         ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2Attendance a2Attendance)
    {
        return toAjax(a2AttendanceService.updateA2Attendance(a2Attendance));
    }

    /**
     * 删除A2 课堂表现classRegistercr
     */
    @ApiOperation("删除A2 课堂表现classRegistercr         ")
    @PreAuthorize("@ss.hasPermi('performance:attendance:remove')")
    @Log(title = "A2 课堂表现classRegistercr         ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{aaIds}")
    public AjaxResult remove(@PathVariable Long[] aaIds)
    {
        return toAjax(a2AttendanceService.deleteA2AttendanceByAaIds(aaIds));
    }
}
