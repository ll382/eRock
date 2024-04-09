package com.ruoyi.controller.knowledgeQuiz;

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
import com.ruoyi.knowledgeQuiz.domain.A1Task;
import com.ruoyi.knowledgeQuiz.service.IA1TaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A1 知识测试任务Controller
 *
 * @author heye
 * @date 2024-04-06
 */
@Api(tags = {"A1 知识测试任务"})
@RestController
@RequestMapping("/knowledgeQuiz/task")
public class A1TaskController extends BaseController
{
    @Autowired
    private IA1TaskService a1TaskService;

    /**
     * 查询A1 知识测试任务列表
     */
    @ApiOperation("查询A1 知识测试任务列表")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(A1Task a1Task)
    {
        startPage();
        List<A1Task> list = a1TaskService.selectA1TaskList(a1Task);
        return getDataTable(list);
    }

    /**
     * 导出A1 知识测试任务列表
     */
    @ApiOperation("导出A1 知识测试任务列表")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:task:export')")
    @Log(title = "A1 知识测试任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A1Task a1Task)
    {
        List<A1Task> list = a1TaskService.selectA1TaskList(a1Task);
        ExcelUtil<A1Task> util = new ExcelUtil<A1Task>(A1Task.class);
        util.exportExcel(response, list, "A1 知识测试任务数据");
    }

    /**
     * 获取A1 知识测试任务详细信息
     */
    @ApiOperation("获取A1 知识测试任务详细信息")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(a1TaskService.selectA1TaskByTaskId(taskId));
    }

    /**
     * 新增A1 知识测试任务
     */
    @ApiOperation("新增A1 知识测试任务")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:task:add')")
    @Log(title = "A1 知识测试任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A1Task a1Task)
    {
        return toAjax(a1TaskService.insertA1Task(a1Task));
    }

    /**
     * 修改A1 知识测试任务
     */
    @ApiOperation("修改A1 知识测试任务")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:task:edit')")
    @Log(title = "A1 知识测试任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A1Task a1Task)
    {
        return toAjax(a1TaskService.updateA1Task(a1Task));
    }

    /**
     * 删除A1 知识测试任务
     */
    @ApiOperation("删除A1 知识测试任务")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:task:remove')")
    @Log(title = "A1 知识测试任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(a1TaskService.deleteA1TaskByTaskIds(taskIds));
    }
}
