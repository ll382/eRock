package com.ruoyi.controller.score;

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
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.service.ITotalScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 总分Controller
 *
 * @author houq
 * @date 2024-04-22
 */
@Api(tags = {"总分"})
@RestController
@RequestMapping("/score/sumScore")
public class TotalScoreController extends BaseController
{
    @Autowired
    private ITotalScoreService totalScoreService;

    /**
     * 查询总分列表
     */
    @ApiOperation("查询总分列表")
    @PreAuthorize("@ss.hasPermi('score:sumScore:list')")
    @GetMapping("/list")
    public TableDataInfo list(TotalScore totalScore)
    {
        startPage();
        List<TotalScore> list = totalScoreService.selectTotalScoreList(totalScore);
        return getDataTable(list);
    }

    /**
     * 导出总分列表
     */
    @ApiOperation("导出总分列表")
    @PreAuthorize("@ss.hasPermi('score:sumScore:export')")
    @Log(title = "总分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TotalScore totalScore)
    {
        List<TotalScore> list = totalScoreService.selectTotalScoreList(totalScore);
        ExcelUtil<TotalScore> util = new ExcelUtil<TotalScore>(TotalScore.class);
        util.exportExcel(response, list, "总分数据");
    }

    /**
     * 获取总分详细信息
     */
    @ApiOperation("获取总分详细信息")
    @PreAuthorize("@ss.hasPermi('score:sumScore:query')")
    @GetMapping(value = "/{tsId}")
    public AjaxResult getInfo(@PathVariable("tsId") Long tsId)
    {
        return success(totalScoreService.selectTotalScoreByTsId(tsId));
    }

    /**
     * 新增总分
     */
    @ApiOperation("新增总分")
    @PreAuthorize("@ss.hasPermi('score:sumScore:add')")
    @Log(title = "总分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TotalScore totalScore)
    {
        return toAjax(totalScoreService.insertTotalScore(totalScore));
    }

    /**
     * 修改总分
     */
    @ApiOperation("修改总分")
    @PreAuthorize("@ss.hasPermi('score:sumScore:edit')")
    @Log(title = "总分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TotalScore totalScore)
    {
        return toAjax(totalScoreService.updateTotalScore(totalScore));
    }

    /**
     * 删除总分
     */
    @ApiOperation("删除总分")
    @PreAuthorize("@ss.hasPermi('score:sumScore:remove')")
    @Log(title = "总分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tsIds}")
    public AjaxResult remove(@PathVariable Long[] tsIds)
    {
        return toAjax(totalScoreService.deleteTotalScoreByTsIds(tsIds));
    }
}
