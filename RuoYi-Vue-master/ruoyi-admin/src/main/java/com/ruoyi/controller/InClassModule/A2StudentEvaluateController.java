package com.ruoyi.controller.InClassModule;

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
import com.ruoyi.InClassModule.domain.A2StudentEvaluate;
import com.ruoyi.InClassModule.service.IA2StudentEvaluateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2 合作学习 学生互评Controller
 *
 * @author heye
 * @date 2024-04-11
 */
@Api(tags = {"A2 合作学习 学生互评"})
@RestController
@RequestMapping("/peerRevision/evaluate")
public class A2StudentEvaluateController extends BaseController
{
    @Autowired
    private IA2StudentEvaluateService a2StudentEvaluateService;

    /**
     * 查询A2 合作学习 学生互评列表
     */
    @ApiOperation("查询A2 合作学习 学生互评列表")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(A2StudentEvaluate a2StudentEvaluate)
    {
        startPage();
        List<A2StudentEvaluate> list = a2StudentEvaluateService.selectA2StudentEvaluateList(a2StudentEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出A2 合作学习 学生互评列表
     */
    @ApiOperation("导出A2 合作学习 学生互评列表")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:export')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2StudentEvaluate a2StudentEvaluate)
    {
        List<A2StudentEvaluate> list = a2StudentEvaluateService.selectA2StudentEvaluateList(a2StudentEvaluate);
        ExcelUtil<A2StudentEvaluate> util = new ExcelUtil<A2StudentEvaluate>(A2StudentEvaluate.class);
        util.exportExcel(response, list, "A2 合作学习 学生互评数据");
    }

    /**
     * 获取A2 合作学习 学生互评详细信息
     */
    @ApiOperation("获取A2 合作学习 学生互评详细信息")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:query')")
    @GetMapping(value = "/{seId}")
    public AjaxResult getInfo(@PathVariable("seId") Long seId)
    {
        return success(a2StudentEvaluateService.selectA2StudentEvaluateBySeId(seId));
    }

    /**
     * 新增A2 合作学习 学生互评
     */
    @ApiOperation("新增A2 合作学习 学生互评")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:add')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2StudentEvaluate a2StudentEvaluate)
    {
        return toAjax(a2StudentEvaluateService.insertA2StudentEvaluate(a2StudentEvaluate));
    }

    /**
     * 修改A2 合作学习 学生互评
     */
    @ApiOperation("修改A2 合作学习 学生互评")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:edit')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2StudentEvaluate a2StudentEvaluate)
    {
        return toAjax(a2StudentEvaluateService.updateA2StudentEvaluate(a2StudentEvaluate));
    }

    /**
     * 删除A2 合作学习 学生互评
     */
    @ApiOperation("删除A2 合作学习 学生互评")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:remove')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.DELETE)
	@DeleteMapping("/{seIds}")
    public AjaxResult remove(@PathVariable Long[] seIds)
    {
        return toAjax(a2StudentEvaluateService.deleteA2StudentEvaluateBySeIds(seIds));
    }
}
