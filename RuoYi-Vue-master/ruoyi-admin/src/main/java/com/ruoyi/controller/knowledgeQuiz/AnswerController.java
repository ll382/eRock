package com.ruoyi.controller.knowledgeQuiz;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
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
import com.ruoyi.teachingExchange.domain.Answer;
import com.ruoyi.knowledgeQuiz.service.IAnswerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A1 知识测试 学生成绩Controller
 *
 * @author ljy
 * @date 2024-03-20
 */
@Api(tags = {"A1 知识测试 学生成绩"})
@RestController
@RequestMapping("/knowledgeQuiz/achievement")
public class AnswerController extends BaseController
{
    @Autowired
    private IAnswerService answerService;

    /**
     * 查询A1 知识测试 学生成绩列表
     */
    @ApiOperation("查询A1 知识测试 学生成绩列表")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:achievement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Answer answer)
    {

        startPage();
        List<Answer> list = answerService.selectAnswerList(answer);
        return getDataTable(list);
    }

    /**
     * 导出A1 知识测试 学生成绩列表
     */
    @ApiOperation("导出A1 知识测试 学生成绩列表")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:achievement:export')")
    @Log(title = "A1 知识测试 学生成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Answer answer)
    {
        List<Answer> list = answerService.selectAnswerList(answer);
        ExcelUtil<Answer> util = new ExcelUtil<Answer>(Answer.class);
        util.exportExcel(response, list, "A1 知识测试 学生成绩数据");
    }

    /**
     * 获取A1 知识测试 学生成绩详细信息
     */
    @ApiOperation("获取A1 知识测试 学生成绩详细信息")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:achievement:query')")
    @GetMapping(value = "/{ansId}")
    public AjaxResult getInfo(@PathVariable("ansId") Long ansId)
    {
        return success(answerService.selectAnswerByAnsId(ansId));
    }

    /**
     * 新增A1 知识测试 学生成绩
     */
    @ApiOperation("新增A1 知识测试 学生成绩")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:achievement:add')")
    @Log(title = "A1 知识测试 学生成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Answer answer)
    {
        return toAjax(answerService.insertAnswer(answer));
    }

    /**
     * 修改A1 知识测试 学生成绩
     */
    @ApiOperation("修改A1 知识测试 学生成绩")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:achievement:edit')")
    @Log(title = "A1 知识测试 学生成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Answer answer)
    {
        return toAjax(answerService.updateAnswer(answer));
    }

    /**
     * 删除A1 知识测试 学生成绩
     */
    @ApiOperation("删除A1 知识测试 学生成绩")
    @PreAuthorize("@ss.hasPermi('knowledgeQuiz:achievement:remove')")
    @Log(title = "A1 知识测试 学生成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ansIds}")
    public AjaxResult remove(@PathVariable Long[] ansIds)
    {
        return toAjax(answerService.deleteAnswerByAnsIds(ansIds));
    }
}
