package com.ruoyi.controller.dModularity;

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
import com.ruoyi.dModularity.domain.D2Resource;
import com.ruoyi.dModularity.service.ID2ResourceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * D2 资源Controller
 *
 * @author houq
 * @date 2024-04-16
 */
@Api(tags = {"D2 资源"})
@RestController
@RequestMapping("/dModularity/resource")
public class D2ResourceController extends BaseController
{
    @Autowired
    private ID2ResourceService d2ResourceService;

    /**
     * 查询D2 资源列表
     */
    @ApiOperation("查询D2 资源列表")
    @PreAuthorize("@ss.hasPermi('dModularity:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(D2Resource d2Resource)
    {
        startPage();
        List<D2Resource> list = d2ResourceService.selectD2ResourceList(d2Resource);
        return getDataTable(list);
    }

    /**
     * 导出D2 资源列表
     */
    @ApiOperation("导出D2 资源列表")
    @PreAuthorize("@ss.hasPermi('dModularity:resource:export')")
    @Log(title = "D2 资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, D2Resource d2Resource)
    {
        List<D2Resource> list = d2ResourceService.selectD2ResourceList(d2Resource);
        ExcelUtil<D2Resource> util = new ExcelUtil<D2Resource>(D2Resource.class);
        util.exportExcel(response, list, "D2 资源数据");
    }

    /**
     * 获取D2 资源详细信息
     */
    @ApiOperation("获取D2 资源详细信息")
    @PreAuthorize("@ss.hasPermi('dModularity:resource:query')")
    @GetMapping(value = "/{resourceId}")
    public AjaxResult getInfo(@PathVariable("resourceId") Long resourceId)
    {
        return success(d2ResourceService.selectD2ResourceByResourceId(resourceId));
    }

    /**
     * 新增D2 资源
     */
    @ApiOperation("新增D2 资源")
    @PreAuthorize("@ss.hasPermi('dModularity:resource:add')")
    @Log(title = "D2 资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody D2Resource d2Resource)
    {
        return toAjax(d2ResourceService.insertD2Resource(d2Resource));
    }

    /**
     * 修改D2 资源
     */
    @ApiOperation("修改D2 资源")
    @PreAuthorize("@ss.hasPermi('dModularity:resource:edit')")
    @Log(title = "D2 资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody D2Resource d2Resource)
    {
        return toAjax(d2ResourceService.updateD2Resource(d2Resource));
    }

    /**
     * 删除D2 资源
     */
    @ApiOperation("删除D2 资源")
    @PreAuthorize("@ss.hasPermi('dModularity:resource:remove')")
    @Log(title = "D2 资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resourceIds}")
    public AjaxResult remove(@PathVariable Long[] resourceIds)
    {
        return toAjax(d2ResourceService.deleteD2ResourceByResourceIds(resourceIds));
    }
}
