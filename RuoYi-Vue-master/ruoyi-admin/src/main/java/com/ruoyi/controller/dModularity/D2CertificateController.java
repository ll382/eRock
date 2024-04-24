package com.ruoyi.controller.dModularity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.core.service.SelectUser;
import com.ruoyi.dModularity.domain.D2CertificateAuditByStuId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dModularity.domain.D2Certificate;
import com.ruoyi.dModularity.service.ID2CertificateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * D2 证书表Controller
 *
 * @author houq
 * @date 2024-04-16
 */
@Api(tags = {"D2 证书表"})
@RestController
@RequestMapping("/dModularity/certificate")
public class D2CertificateController extends BaseController {
	@Autowired
	private ID2CertificateService d2CertificateService;

	@Autowired
	private SelectUser selectUser;

	/**
	 * 查询D2 证书表列表
	 */
	@ApiOperation("查询D2 证书表列表")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:list')")
	@GetMapping("/list")
	public AjaxResult list(D2Certificate d2Certificate) {
		startPage();
		return success(selectUser.selectUndoneStudent(d2CertificateService.selectD2CertificateList(d2Certificate)));
	}

	/**
	 * 导出D2 证书表列表
	 */
	@ApiOperation("导出D2 证书表列表")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:export')")
	@Log(title = "D2 证书表", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response, D2Certificate d2Certificate) {
		List<D2Certificate> list = d2CertificateService.selectD2CertificateList(d2Certificate);
		ExcelUtil<D2Certificate> util = new ExcelUtil<D2Certificate>(D2Certificate.class);
		util.exportExcel(response, list, "D2 证书表数据");
	}

	/**
	 * 获取D2 证书表详细信息
	 */
	@ApiOperation("获取D2 证书表详细信息")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:query')")
	@GetMapping(value = "/{certificateId}")
	public AjaxResult getInfo(@PathVariable("certificateId") Long certificateId) {
		return success(d2CertificateService.selectD2CertificateByCertificateId(certificateId));
	}

	/**
	 * 新增D2 证书表
	 */
	@ApiOperation("新增D2 证书表")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:add')")
	@Log(title = "D2 证书表", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody D2Certificate d2Certificate) {
		return toAjax(d2CertificateService.insertD2Certificate(d2Certificate));
	}

	/**
	 * 修改D2 证书表
	 */
	@ApiOperation("修改D2 证书表")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:edit')")
	@Log(title = "D2 证书表", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody D2Certificate d2Certificate) {
		return toAjax(d2CertificateService.updateD2Certificate(d2Certificate));
	}

	/**
	 * 删除D2 证书表
	 */
	@ApiOperation("删除D2 证书表")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:remove')")
	@Log(title = "D2 证书表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{certificateIds}")
	public AjaxResult remove(@PathVariable Long[] certificateIds) {
		return toAjax(d2CertificateService.deleteD2CertificateByCertificateIds(certificateIds));
	}

	/**
	 * D2学生名单列表
	 */
	@ApiOperation("D2学生名单列表")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:auditList')")
	@GetMapping("/auditList")
	public AjaxResult auditList(Integer enumId) {
		return success(d2CertificateService.selectD2CertificateAudit(enumId));
	}

	/**
	 * D2查询学生上传资料信息
	 */
	@ApiOperation("D2查询学生上传资料信息")
	@PreAuthorize("@ss.hasPermi('dModularity:certificate:auditByStuId')")
	@GetMapping("/auditByStuId")
	public AjaxResult auditByStuId(@RequestParam HashMap<String, Object> map) {
		return success(d2CertificateService.selectD2CertificateAuditByStuId(map));
	}
}
