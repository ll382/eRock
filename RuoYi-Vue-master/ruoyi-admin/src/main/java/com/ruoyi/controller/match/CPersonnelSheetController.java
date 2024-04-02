package com.ruoyi.controller.match;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.service.ICPersonnelSheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * C 球队内人员Controller
 *
 * @author heye
 * @date 2024-03-19
 */
@Api("C模块比赛记录")

@RestController
@RequestMapping("/match/sheet")
public class CPersonnelSheetController extends BaseController {
	@Autowired
	private ICPersonnelSheetService cPersonnelSheetService;
	
	/**
	 * 查询C 球队内人员列表
	 */
	@ApiOperation("查询C 球队内人员列表")
	@PreAuthorize("@ss.hasPermi('match:sheet:list')")
	@GetMapping("/list")
	public TableDataInfo list(CPersonnelSheet cPersonnelSheet) {
		startPage();
		List<CPersonnelSheet> list = cPersonnelSheetService.selectCPersonnelSheetList(cPersonnelSheet);
		return getDataTable(list);
	}
	
	/**
	 * 导出C 球队内人员列表
	 */
	@ApiOperation("导出C 球队内人员列表")
	@PreAuthorize("@ss.hasPermi('match:sheet:export')")
	@Log(title = "C 球队内人员", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, CPersonnelSheet cPersonnelSheet) {
		List<CPersonnelSheet> list = cPersonnelSheetService.selectCPersonnelSheetList(cPersonnelSheet);
		ExcelUtil<CPersonnelSheet> util = new ExcelUtil<CPersonnelSheet>(CPersonnelSheet.class);
		util.exportExcel(response, list, "C 球队内人员数据");
	}
	
	/**
	 * 获取C 球队内人员详细信息
	 */
	@ApiOperation("获取C 球队内人员详细信息")
	
	@PreAuthorize("@ss.hasPermi('match:sheet:query')")
	@GetMapping(value = "/{psId}")
	public AjaxResult getInfo(@PathVariable("psId") Long psId) {
		return success(cPersonnelSheetService.selectCPersonnelSheetByPsId(psId));
	}
	
	/**
	 * 新增C 球队内人员
	 */
	@ApiOperation("新增C 球队内人员")
	
	@PreAuthorize("@ss.hasPermi('match:sheet:add')")
	@Log(title = "C 球队内人员", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody CPersonnelSheet cPersonnelSheet) {
		return toAjax(cPersonnelSheetService.insertCPersonnelSheet(cPersonnelSheet));
	}
	
	/**
	 * 修改C 球队内人员
	 */
	@ApiOperation("修改C 球队内人员")
	
	@PreAuthorize("@ss.hasPermi('match:sheet:edit')")
	@Log(title = "C 球队内人员", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody CPersonnelSheet cPersonnelSheet) {
		return toAjax(cPersonnelSheetService.updateCPersonnelSheet(cPersonnelSheet));
	}
	
	/**
	 * 删除C 球队内人员
	 */
	@ApiOperation("删除C 球队内人员")
	
	@PreAuthorize("@ss.hasPermi('match:sheet:remove')")
	@Log(title = "C 球队内人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{psIds}")
	public AjaxResult remove(@PathVariable Long[] psIds) {
		return toAjax(cPersonnelSheetService.deleteCPersonnelSheetByPsIds(psIds));
	}
	
	/**
	 * 根据球队id查询C 球队内人员列表
	 */
	@ApiOperation("根据球队id查询C 球队内人员列表")
	@PreAuthorize("@ss.hasPermi('match:sheet:personnelList')")
	@GetMapping("/personnelList")
	public TableDataInfo findPersonnelShellByBalId(@RequestParam(required = false) Long balId) {
		// startPage();
		List<CPersonnelSheet> list = cPersonnelSheetService.findPersonnelShellByBalId(balId);
		return getDataTable(list);
	}
}
