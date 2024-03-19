package com.ruoyi.web.controller.core;

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
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.service.IStuGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小组Controller
 *
 * @author heye
 * @date 2024-03-15
 */
@Api("核心模块小组")
@RestController
@RequestMapping("/core/group")
public class StuGroupController extends BaseController
{
    @Autowired
    private IStuGroupService stuGroupService;

    /**
     * 查询小组列表
     */
    @ApiOperation("查询小组列表")
    @PreAuthorize("@ss.hasPermi('core:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(StuGroup stuGroup)
    {
        startPage();
        List<StuGroup> list = stuGroupService.selectStuGroupList(stuGroup);
        return getDataTable(list);
    }

    /**
     * 导出小组列表
     */
    @ApiOperation("导出小组列表")
    @PreAuthorize("@ss.hasPermi('core:group:export')")
    @Log(title = "小组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StuGroup stuGroup)
    {
        List<StuGroup> list = stuGroupService.selectStuGroupList(stuGroup);
        ExcelUtil<StuGroup> util = new ExcelUtil<StuGroup>(StuGroup.class);
        util.exportExcel(response, list, "小组数据");
    }

    /**
     * 获取小组详细信息
     */
    @ApiOperation("获取小组详细信息")
    @PreAuthorize("@ss.hasPermi('core:group:query')")
    @GetMapping(value = "/{ggId}")
    public AjaxResult getInfo(@PathVariable("ggId") Long ggId)
    {
        return success(stuGroupService.selectStuGroupByGgId(ggId));
    }

    /**
     * 新增小组
     */
    @ApiOperation("新增小组")
    @PreAuthorize("@ss.hasPermi('core:group:add')")
    @Log(title = "小组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StuGroup stuGroup)
    {
        return toAjax(stuGroupService.insertStuGroup(stuGroup));
    }

    /**
     * 修改小组
     */
    @ApiOperation("修改小组")
    @PreAuthorize("@ss.hasPermi('core:group:edit')")
    @Log(title = "小组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StuGroup stuGroup)
    {
        return toAjax(stuGroupService.updateStuGroup(stuGroup));
    }

    /**
     * 删除小组
     */
    @ApiOperation("删除小组")
    @PreAuthorize("@ss.hasPermi('core:group:remove')")
    @Log(title = "小组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ggIds}")
    public AjaxResult remove(@PathVariable Long[] ggIds)
    {
        return toAjax(stuGroupService.deleteStuGroupByGgIds(ggIds));
    }
}
