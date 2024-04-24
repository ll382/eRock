package com.ruoyi.controller.match;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.match.domain.CompetitionRecord;
import com.ruoyi.match.service.ICompetitionRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * C 比赛记录Controller
 *
 * @author houq
 * @date 2024-04-08
 */
@Api(tags = {"C 比赛记录"})
@RestController
@RequestMapping("/match/record")
public class CompetitionRecordController extends BaseController {
	@Autowired
	private ICompetitionRecordService competitionRecordService;

	/**
	 * 查询C 比赛记录列表
	 */
	@ApiOperation("查询C 比赛记录列表")
	@PreAuthorize("@ss.hasPermi('match:record:list')")
	@GetMapping("/list")
	public TableDataInfo list(CompetitionRecord competitionRecord) {
		startPage();
		List<CompetitionRecord> list = competitionRecordService.selectCompetitionRecordList(competitionRecord);
		return getDataTable(list);
	}

	/**
	 * 导出C 比赛记录列表
	 */
	@ApiOperation("导出C 比赛记录列表")
	@PreAuthorize("@ss.hasPermi('match:record:export')")
	@Log(title = "C 比赛记录", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, CompetitionRecord competitionRecord) {
		List<CompetitionRecord> list = competitionRecordService.selectCompetitionRecordList(competitionRecord);
		ExcelUtil<CompetitionRecord> util = new ExcelUtil<CompetitionRecord>(CompetitionRecord.class);
		util.exportExcel(response, list, "C 比赛记录数据");
	}

	/**
	 * 获取C 比赛记录详细信息
	 */
	@ApiOperation("获取C 比赛记录详细信息")
	@PreAuthorize("@ss.hasPermi('match:record:query')")
	@GetMapping(value = "/{ccRId}")
	public AjaxResult getInfo(@PathVariable("ccRId") Long ccRId) {
		return success(competitionRecordService.selectCompetitionRecordByCcRId(ccRId));
	}

	/**
	 * 新增C 比赛记录
	 */
	@ApiOperation("新增C 比赛记录")
	@PreAuthorize("@ss.hasPermi('match:record:add')")
	@Log(title = "C 比赛记录", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody CompetitionRecord competitionRecord) {
		return toAjax(competitionRecordService.insertCompetitionRecord(competitionRecord));
	}

	/**
	 * 修改C 比赛记录
	 */
	@ApiOperation("修改C 比赛记录")
	@PreAuthorize("@ss.hasPermi('match:record:edit')")
	@Log(title = "C 比赛记录", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody CompetitionRecord competitionRecord) {
		return toAjax(competitionRecordService.updateCompetitionRecord(competitionRecord));
	}

	/**
	 * 删除C 比赛记录
	 */
	@ApiOperation("删除C 比赛记录")
	@PreAuthorize("@ss.hasPermi('match:record:remove')")
	@Log(title = "C 比赛记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ccRIds}")
	public AjaxResult remove(@PathVariable Long[] ccRIds) {
		return toAjax(competitionRecordService.deleteCompetitionRecordByCcRIds(ccRIds));
	}

	/**
	 * 获取比赛记录记录
	 */
	@ApiOperation("获取比赛记录记录")
	@PreAuthorize("@ss.hasPermi('match:record:game')")
	@GetMapping("/game")
	public AjaxResult selectStudentKn(@RequestParam(required = false) String speci) {
		return success(competitionRecordService.selectGameRecord(speci));
	}

	/**
	 * 获取课外赛学生信息
	 */
	@ApiOperation("获取课外赛学生信息")
	@PreAuthorize("@ss.hasPermi('match:record:selectKw')")
	@GetMapping("/selectKw")
	public AjaxResult selectKw() {
		return success(competitionRecordService.findCBallteamKw());
	}

	/**
	 * 获取课外赛信息
	 */
	@ApiOperation("获取课外赛信息")
	@PreAuthorize("@ss.hasPermi('match:record:findRecordKw')")
	@GetMapping("/findRecordKw")
	public AjaxResult findRecordKw(@RequestParam(required = false) Long stuId) {
		return success(competitionRecordService.findRecordKwByStuId(stuId));
	}

	/**
	 * 根据学号和比赛id获取学生信息
	 */
	@ApiOperation("根据学号和比赛id获取学生信息")
	@PreAuthorize("@ss.hasPermi('match:record:findRecordKwUrl')")
	@GetMapping("/findRecordKwUrl")
	public AjaxResult findRecordKwUrl(@RequestParam(required = false) HashMap<String, String> map) {
		return success(competitionRecordService.findRecordKwByStuIdAndCcrId(map));
	}

	@ApiOperation("审核学生上传的资料")
	@PreAuthorize("@ss.hasPermi('match:record:updateAudit')")
	@PutMapping("/updateAudit")
	public AjaxResult updateAudit(@RequestBody HashMap<String, String> map) {
		return success(competitionRecordService.updateAudit(map));
	}
}
