package com.ruoyi.controller.match;

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
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.service.ICompetitionRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 比赛记录Controller
 *
 * @author heye
 * @date 2024-03-18
 */
@Api("C比赛记录")
@RestController
@RequestMapping("/match/record")
public class CompetitionRecordController extends BaseController
{
    @Autowired
    private ICompetitionRecordService competitionRecordService;

    /**
     * 查询比赛记录列表
     */
    @ApiOperation("查询比赛记录列表")
    @PreAuthorize("@ss.hasPermi('match:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionRecord competitionRecord)
    {
        startPage();
        List<CompetitionRecord> list = competitionRecordService.selectCompetitionRecordList(competitionRecord);
        return getDataTable(list);
    }

    /**
     * 导出比赛记录列表
     */
    @ApiOperation("导出比赛记录列表")
    @PreAuthorize("@ss.hasPermi('match:record:export')")
    @Log(title = "比赛记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionRecord competitionRecord)
    {
        List<CompetitionRecord> list = competitionRecordService.selectCompetitionRecordList(competitionRecord);
        ExcelUtil<CompetitionRecord> util = new ExcelUtil<CompetitionRecord>(CompetitionRecord.class);
        util.exportExcel(response, list, "比赛记录数据");
    }

    /**
     * 获取比赛记录详细信息
     */
    @ApiOperation("获取比赛记录详细信息")
    @PreAuthorize("@ss.hasPermi('match:record:query')")
    @GetMapping(value = "/{ccRId}")
    public AjaxResult getInfo(@PathVariable("ccRId") Long ccRId)
    {
        return success(competitionRecordService.selectCompetitionRecordByCcRId(ccRId));
    }

    /**
     * 新增比赛记录
     */
    @ApiOperation("新增比赛记录")
    @PreAuthorize("@ss.hasPermi('match:record:add')")
    @Log(title = "比赛记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionRecord competitionRecord)
    {
        return toAjax(competitionRecordService.insertCompetitionRecord(competitionRecord));
    }

    /**
     * 修改比赛记录
     */
    @ApiOperation("修改比赛记录")
    @PreAuthorize("@ss.hasPermi('match:record:edit')")
    @Log(title = "比赛记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionRecord competitionRecord)
    {
        return toAjax(competitionRecordService.updateCompetitionRecord(competitionRecord));
    }

    /**
     * 删除比赛记录
     */
    @ApiOperation("删除比赛记录")
    @PreAuthorize("@ss.hasPermi('match:record:remove')")
    @Log(title = "比赛记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ccRIds}")
    public AjaxResult remove(@PathVariable Long[] ccRIds)
    {
        return toAjax(competitionRecordService.deleteCompetitionRecordByCcRIds(ccRIds));
    }
}
