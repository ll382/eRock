package com.ruoyi.controller.practice;

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
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.service.IAExerciseTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 练习、测试任务表Controller
 *
 * @author houq
 * @date 2024-04-09
 */
@Api(tags = {"练习、测试任务表"})
@RestController
@RequestMapping("/practice/task")
public class AExerciseTaskController extends BaseController
{
    @Autowired
    private IAExerciseTaskService aExerciseTaskService;

    /**
     * 查询练习、测试任务表列表
     */
    @ApiOperation("查询练习、测试任务表列表")
    @PreAuthorize("@ss.hasPermi('practice:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(AExerciseTask aExerciseTask)
    {
        startPage();
        List<AExerciseTask> list = aExerciseTaskService.selectAExerciseTaskList(aExerciseTask);
        return getDataTable(list);
    }

    /**
     * 导出练习、测试任务表列表
     */
    @ApiOperation("导出练习、测试任务表列表")
    @PreAuthorize("@ss.hasPermi('practice:task:export')")
    @Log(title = "练习、测试任务表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AExerciseTask aExerciseTask)
    {
        List<AExerciseTask> list = aExerciseTaskService.selectAExerciseTaskList(aExerciseTask);
        ExcelUtil<AExerciseTask> util = new ExcelUtil<AExerciseTask>(AExerciseTask.class);
        util.exportExcel(response, list, "练习、测试任务表数据");
    }

    /**
     * 获取练习、测试任务表详细信息
     */
    @ApiOperation("获取练习、测试任务表详细信息")
    @PreAuthorize("@ss.hasPermi('practice:task:query')")
    @GetMapping(value = "/{etId}")
    public AjaxResult getInfo(@PathVariable("etId") Long etId)
    {
        return success(aExerciseTaskService.selectAExerciseTaskByEtId(etId));
    }

    /**
     * 新增练习、测试任务表
     */
    @ApiOperation("新增练习、测试任务表")
    @PreAuthorize("@ss.hasPermi('practice:task:add')")
    @Log(title = "练习、测试任务表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AExerciseTask aExerciseTask)
    {
        return toAjax(aExerciseTaskService.insertAExerciseTask(aExerciseTask));
    }

    /**
     * 修改练习、测试任务表
     */
    @ApiOperation("修改练习、测试任务表")
    @PreAuthorize("@ss.hasPermi('practice:task:edit')")
    @Log(title = "练习、测试任务表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AExerciseTask aExerciseTask)
    {
        return toAjax(aExerciseTaskService.updateAExerciseTask(aExerciseTask));
    }

    /**
     * 删除练习、测试任务表
     */
    @ApiOperation("删除练习、测试任务表")
    @PreAuthorize("@ss.hasPermi('practice:task:remove')")
    @Log(title = "练习、测试任务表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{etIds}")
    public AjaxResult remove(@PathVariable Long[] etIds)
    {
        return toAjax(aExerciseTaskService.deleteAExerciseTaskByEtIds(etIds));
    }
}
