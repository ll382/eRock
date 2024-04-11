package com.ruoyi.controller.InClassModule;

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
import com.ruoyi.InClassModule.domain.A2TeachingAssistantEvaluation;
import com.ruoyi.InClassModule.service.IA2TeachingAssistantEvaluationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2 合作学习 教师评价Controller
 *
 * @author heye
 * @date 2024-04-11
 */
@Api(tags = {"A2 合作学习 教师评价"})
@RestController
@RequestMapping("/evaluate/evaluation")
public class A2TeachingAssistantEvaluationController extends BaseController
{
    @Autowired
    private IA2TeachingAssistantEvaluationService a2TeachingAssistantEvaluationService;

    /**
     * 查询A2 合作学习 教师评价列表
     */
    @ApiOperation("查询A2 合作学习 教师评价列表")
    @PreAuthorize("@ss.hasPermi('evaluate:evaluation:list')")
    @GetMapping("/list")
    public TableDataInfo list(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        startPage();
        List<A2TeachingAssistantEvaluation> list = a2TeachingAssistantEvaluationService.selectA2TeachingAssistantEvaluationList(a2TeachingAssistantEvaluation);
        return getDataTable(list);
    }

    /**
     * 导出A2 合作学习 教师评价列表
     */
    @ApiOperation("导出A2 合作学习 教师评价列表")
    @PreAuthorize("@ss.hasPermi('evaluate:evaluation:export')")
    @Log(title = "A2 合作学习 教师评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        List<A2TeachingAssistantEvaluation> list = a2TeachingAssistantEvaluationService.selectA2TeachingAssistantEvaluationList(a2TeachingAssistantEvaluation);
        ExcelUtil<A2TeachingAssistantEvaluation> util = new ExcelUtil<A2TeachingAssistantEvaluation>(A2TeachingAssistantEvaluation.class);
        util.exportExcel(response, list, "A2 合作学习 教师评价数据");
    }

    /**
     * 获取A2 合作学习 教师评价详细信息
     */
    @ApiOperation("获取A2 合作学习 教师评价详细信息")
    @PreAuthorize("@ss.hasPermi('evaluate:evaluation:query')")
    @GetMapping(value = "/{taeId}")
    public AjaxResult getInfo(@PathVariable("taeId") Long taeId)
    {
        return success(a2TeachingAssistantEvaluationService.selectA2TeachingAssistantEvaluationByTaeId(taeId));
    }

    /**
     * 新增A2 合作学习 教师评价
     */
    @ApiOperation("新增A2 合作学习 教师评价")
    @PreAuthorize("@ss.hasPermi('evaluate:evaluation:add')")
    @Log(title = "A2 合作学习 教师评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        return toAjax(a2TeachingAssistantEvaluationService.insertA2TeachingAssistantEvaluation(a2TeachingAssistantEvaluation));
    }

    /**
     * 修改A2 合作学习 教师评价
     */
    @ApiOperation("修改A2 合作学习 教师评价")
    @PreAuthorize("@ss.hasPermi('evaluate:evaluation:edit')")
    @Log(title = "A2 合作学习 教师评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        return toAjax(a2TeachingAssistantEvaluationService.updateA2TeachingAssistantEvaluation(a2TeachingAssistantEvaluation));
    }

    /**
     * 删除A2 合作学习 教师评价
     */
    @ApiOperation("删除A2 合作学习 教师评价")
    @PreAuthorize("@ss.hasPermi('evaluate:evaluation:remove')")
    @Log(title = "A2 合作学习 教师评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taeIds}")
    public AjaxResult remove(@PathVariable Long[] taeIds)
    {
        return toAjax(a2TeachingAssistantEvaluationService.deleteA2TeachingAssistantEvaluationByTaeIds(taeIds));
    }
}
