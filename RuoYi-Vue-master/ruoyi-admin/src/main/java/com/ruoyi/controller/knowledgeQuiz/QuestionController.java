package com.ruoyi.controller.knowledgeQuiz;

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
import com.ruoyi.knowledgeQuiz.domain.Question;
import com.ruoyi.knowledgeQuiz.service.IQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A1 知识测试 问Controller
 *
 * @author ljy
 * @date 2024-03-18
 */
@RestController
@RequestMapping("/knowledgeQuiz/question")
public class QuestionController extends BaseController
{
    @Autowired
    private IQuestionService questionService;

    /**
     * 查询A1 知识测试 问列表
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:question:list')")
    @GetMapping("/list")
    public TableDataInfo list(Question question)
    {
        startPage();
        List<Question> list = questionService.selectQuestionList(question);
        return getDataTable(list);
    }

    /**
     * 查询A1 知识测试 问列表
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:question:list')")
    @GetMapping("/Tasklist/{question}")
    public TableDataInfo Tasklis(@PathVariable("question") Long question)
    {
        List<Question> list = questionService.selectQuestionByQqIdList(question);
        return getDataTable(list);
    }

    /**
     * 导出A1 知识测试 问列表
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:question:export')")
    @Log(title = "A1 知识测试 问", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Question question)
    {
        List<Question> list = questionService.selectQuestionList(question);
        ExcelUtil<Question> util = new ExcelUtil<Question>(Question.class);
        util.exportExcel(response, list, "A1 知识测试 问数据");
    }

    /**
     * 获取A1 知识测试 问详细信息
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:question:query')")
    @GetMapping(value = "/{qqId}")
    public AjaxResult getInfo(@PathVariable("qqId") Long qqId)
    {
        return success(questionService.selectQuestionByQqId(qqId));
    }

    /**
     * 新增A1 知识测试 问
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:question:add')")
    @Log(title = "A1 知识测试 问", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Question question)
    {
        return toAjax(questionService.insertQuestion(question));
    }

    /**
     * 修改A1 知识测试 问
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:question:edit')")
    @Log(title = "A1 知识测试 问", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Question question)
    {
        return toAjax(questionService.updateQuestion(question));
    }

    /**
     * 删除A1 知识测试 问
     */
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:question:remove')")
    @Log(title = "A1 知识测试 问", businessType = BusinessType.DELETE)
	@DeleteMapping("/{qqIds}")
    public AjaxResult remove(@PathVariable Long[] qqIds)
    {
        return toAjax(questionService.deleteQuestionByQqIds(qqIds));
    }
}
