package com.ruoyi.controller.match;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.service.ICBallteamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 球队参赛Controller
 *
 * @author heye
 * @date 2024-03-19
 */
@Api("C模块球队参赛")
@RestController
@RequestMapping("/match/ballteam")
public class CBallteamController extends BaseController {
	@Autowired
	private ICBallteamService cBallteamService;
	
	/**
	 * 查询球队参赛列表
	 */
	@ApiOperation("查询球队参赛列表")
	@PreAuthorize("@ss.hasPermi('match:ballteam:list')")
	@GetMapping("/list")
	public TableDataInfo list(CBallteam cBallteam) {
		startPage();
		List<CBallteam> list = cBallteamService.selectCBallteamList(cBallteam);
		return getDataTable(list);
	}
	
	/**
	 * 导出球队参赛列表
	 */
	@ApiOperation("导出球队参赛列表")
	@PreAuthorize("@ss.hasPermi('match:ballteam:export')")
	@Log(title = "球队参赛", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, CBallteam cBallteam) {
		List<CBallteam> list = cBallteamService.selectCBallteamList(cBallteam);
		ExcelUtil<CBallteam> util = new ExcelUtil<CBallteam>(CBallteam.class);
		util.exportExcel(response, list, "球队参赛数据");
	}
	
	/**
	 * 获取球队参赛详细信息
	 */
	@ApiOperation("获取球队参赛详细信息")
	@PreAuthorize("@ss.hasPermi('match:ballteam:query')")
	@GetMapping(value = "/{balId}")
	public AjaxResult getInfo(@PathVariable("balId") Long balId) {
		return success(cBallteamService.selectCBallteamByBalId(balId));
	}
	
	/**
	 * 新增球队参赛
	 */
	@ApiOperation("新增球队参赛")
	@PreAuthorize("@ss.hasPermi('match:ballteam:add')")
	@Log(title = "球队参赛", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody CBallteam cBallteam) {
		return toAjax(cBallteamService.insertCBallteam(cBallteam));
	}
	
	/**
	 * 修改球队参赛
	 */
	@ApiOperation("修改球队参赛")
	@PreAuthorize("@ss.hasPermi('match:ballteam:edit')")
	@Log(title = "球队参赛", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody CBallteam cBallteam) {
		return toAjax(cBallteamService.updateCBallteam(cBallteam));
	}
	
	/**
	 * 删除球队参赛
	 */
	@ApiOperation("删除球队参赛")
	@PreAuthorize("@ss.hasPermi('match:ballteam:remove')")
	@Log(title = "球队参赛", businessType = BusinessType.DELETE)
	@DeleteMapping("/{balIds}")
	public AjaxResult remove(@PathVariable Long[] balIds) {
		return toAjax(cBallteamService.deleteCBallteamByBalIds(balIds));
	}
	
	
	/**
	 * 获取比赛小组信息
	 */
	@ApiOperation("获取比赛小组信息")
	@PreAuthorize("@ss.hasPermi('match:ballteam:ccR')")
	@GetMapping("/ccR")
	public TableDataInfo list(@RequestParam(required = false) Long ccRId) {
		// startPage();
		List<CBallteam> list = cBallteamService.selectCompetitionRecordByCcRName(ccRId);
		return getDataTable(list);
	}

	/**
	 * 新增参赛小组
	 */
	@ApiOperation("新增参赛小组")
	@PreAuthorize("@ss.hasPermi('match:ballteam:addStuGroup')")
	@Log(title = "小组", businessType = BusinessType.INSERT)
	@PostMapping("/addStuGroup")
	public AjaxResult addStuGroup(@RequestBody HashMap<String, Object> map) {
		return toAjax(cBallteamService.insertStuGroup(map));
	}

	/**
	 * 批量修改球队参赛
	 */
	@ApiOperation("修改球队参赛")
	@PreAuthorize("@ss.hasPermi('match:ballteam:edits')")
	@Log(title = "球队参赛", businessType = BusinessType.UPDATE)
	@PutMapping("/edits")
	public AjaxResult edits(@RequestBody List<CBallteam> cBallteamList) {
		return toAjax(cBallteamService.updateCBallteams(cBallteamList));
	}
}