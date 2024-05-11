package com.ruoyi.controller.match;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
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
import com.ruoyi.match.domain.ClassRegister;
import com.ruoyi.match.service.IClassRegisterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课堂记录Controller
 *
 * @author houq
 * @date 2024-04-08
 */
@Api(tags = {"课堂记录"})
@RestController
@RequestMapping("/match/register")
public class ClassRegisterController extends BaseController {
	@Autowired
	private IClassRegisterService classRegisterService;

	/**
	 * 查询课堂记录列表
	 */
	@ApiOperation("查询课堂记录列表")
	@PreAuthorize("@ss.hasPermi('match:register:list')")
	@GetMapping("/unitA/{crId}")
	public AjaxResult AmodeClassRegister(@PathVariable("crId") Long crId) {
		startPage();
		ClassRegister list = classRegisterService.selectAmodeClassRegisterByCrId(crId);
		return AjaxResult.success(list);
	}

	/**
	 * 删除课堂记录
	 */
	@ApiOperation("删除课堂以及A子表记录")
	@PreAuthorize("@ss.hasPermi('match:register:remove')")
	@Log(title = "课堂记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/unitA/{crIds}")
	public AjaxResult deleteAmodeClassRegister(@PathVariable Long crIds) {
		return toAjax(classRegisterService.deleteAmodeClassRegister(crIds));
	}


	//   --------------------分界线-----------------------

	/**
	 * 查询课堂记录列表
	 */
	@ApiOperation("查询课堂记录列表")
	@PreAuthorize("@ss.hasPermi('match:register:list')")
	@GetMapping("/list")
	public TableDataInfo list(ClassRegister classRegister) {
		startPage();
		List<ClassRegister> list = classRegisterService.selectClassRegisterList(classRegister);
		return getDataTable(list);
	}

	/**
	 * 查询课堂记录列表
	 */
	@GetMapping("/getDateList")
	public TableDataInfo getDateList(ClassRegister classRegister) {
		List<ClassRegister> list = classRegisterService.selectClassRegisterList(classRegister);
		return getDataTable(list);
	}

	/**
	 * 导出课堂记录列表
	 */
	@ApiOperation("导出课堂记录列表")
	@PreAuthorize("@ss.hasPermi('match:register:export')")
	@Log(title = "课堂记录", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, ClassRegister classRegister) {
		List<ClassRegister> list = classRegisterService.selectClassRegisterList(classRegister);
		ExcelUtil<ClassRegister> util = new ExcelUtil<ClassRegister>(ClassRegister.class);
		util.exportExcel(response, list, "课堂记录数据");
	}

	/**
	 * 获取课堂记录详细信息
	 */
	@ApiOperation("获取课堂记录详细信息")
	@PreAuthorize("@ss.hasPermi('match:register:query')")
	@GetMapping(value = "/{crId}")
	public AjaxResult getInfo(@PathVariable("crId") Long crId) {
		return success(classRegisterService.selectClassRegisterByCrId(crId));
	}

	/**
	 * 新增课堂记录
	 */
	@ApiOperation("新增课堂记录")
	@PreAuthorize("@ss.hasPermi('match:register:add')")
	@Log(title = "课堂记录", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody ClassRegister classRegister) {
		return toAjax(classRegisterService.insertClassRegister(classRegister));
	}

	/**
	 * 修改课堂记录
	 */
	@ApiOperation("修改课堂记录")
	@PreAuthorize("@ss.hasPermi('match:register:edit')")
	@Log(title = "课堂记录", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody ClassRegister classRegister) {
		return toAjax(classRegisterService.updateClassRegister(classRegister));
	}

	/**
	 * 删除课堂记录
	 */
	@ApiOperation("删除课堂记录")
	@PreAuthorize("@ss.hasPermi('match:register:remove')")
	@Log(title = "课堂记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{crIds}")
	public AjaxResult remove(@PathVariable Long[] crIds) {
		return toAjax(classRegisterService.deleteClassRegisterByCrIds(crIds));
	}


	/**
	 * 新增课内赛课堂记录
	 */
	@ApiOperation("新增课堂记录")
	@PreAuthorize("@ss.hasPermi('match:register:addKn')")
	@Log(title = "课内赛课堂记录", businessType = BusinessType.INSERT)
	@PostMapping("/addKn")
	public AjaxResult addKn(@RequestBody HashMap<String, Object> map) {
		return toAjax(classRegisterService.insertClassRegisterKn(map));
	}
}
