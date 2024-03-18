package com.ruoyi.web.controller.knowledgeQuiz;

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
import com.ruoyi.knowledgeQuiz.domain.Result;
import com.ruoyi.knowledgeQuiz.service.IResultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A1 知识测试 答Controller
 * 
 * @author ljy
 * @date 2024-03-18
 */
@RestController
@RequestMapping("/knowledgeQuiz/answer")
public class ResultController extends BaseController
{
    @Autowired
    private IResultService resultService;

    /**
     * 查询A1 知识测试 答列表
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:answer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Result result)
    {
        startPage();
        List<Result> list = resultService.selectResultList(result);
        return getDataTable(list);
    }

    /**
     * 导出A1 知识测试 答列表
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:answer:export')")
    @Log(title = "A1 知识测试 答", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Result result)
    {
        List<Result> list = resultService.selectResultList(result);
        ExcelUtil<Result> util = new ExcelUtil<Result>(Result.class);
        util.exportExcel(response, list, "A1 知识测试 答数据");
    }

    /**
     * 获取A1 知识测试 答详细信息
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:answer:query')")
    @GetMapping(value = "/{ooId}")
    public AjaxResult getInfo(@PathVariable("ooId") Long ooId)
    {
        return success(resultService.selectResultByOoId(ooId));
    }

    /**
     * 新增A1 知识测试 答
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:answer:add')")
    @Log(title = "A1 知识测试 答", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Result result)
    {
        return toAjax(resultService.insertResult(result));
    }

    /**
     * 修改A1 知识测试 答
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:answer:edit')")
    @Log(title = "A1 知识测试 答", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Result result)
    {
        return toAjax(resultService.updateResult(result));
    }

    /**
     * 删除A1 知识测试 答
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:answer:remove')")
    @Log(title = "A1 知识测试 答", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ooIds}")
    public AjaxResult remove(@PathVariable Long[] ooIds)
    {
        return toAjax(resultService.deleteResultByOoIds(ooIds));
    }
}
