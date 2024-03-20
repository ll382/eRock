package com.ruoyi.bModularity.controller;

import com.ruoyi.bModularity.domain.B1FranchiseClub;
import com.ruoyi.bModularity.service.IB1FranchiseClubService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 球队、社团训练Controller
 *
 * @author houq
 * @date 2024-03-20
 */
@Api(tags = {"球队、社团训练"})
@RestController
@RequestMapping("/bModularity/club")
public class B1FranchiseClubController extends BaseController
{
    @Autowired
    private IB1FranchiseClubService b1FranchiseClubService;
    
    /**
     * 查询球队、社团训练列表
     */
    @ApiOperation("查询球队、社团训练列表")
    @PreAuthorize("@ss.hasPermi('bModularity:club:list')")
    @GetMapping("/list")
    public TableDataInfo list(B1FranchiseClub b1FranchiseClub)
    {
        startPage();
        List<B1FranchiseClub> list = b1FranchiseClubService.selectB1FranchiseClubList(b1FranchiseClub);
        return getDataTable(list);
    }
    
    /**
     * 导出球队、社团训练列表
     */
    @ApiOperation("导出球队、社团训练列表")
    @PreAuthorize("@ss.hasPermi('bModularity:club:export')")
    @Log(title = "球队、社团训练", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B1FranchiseClub b1FranchiseClub)
    {
        List<B1FranchiseClub> list = b1FranchiseClubService.selectB1FranchiseClubList(b1FranchiseClub);
        ExcelUtil<B1FranchiseClub> util = new ExcelUtil<B1FranchiseClub>(B1FranchiseClub.class);
        util.exportExcel(response, list, "球队、社团训练数据");
    }
    
    /**
     * 获取球队、社团训练详细信息
     */
    @ApiOperation("获取球队、社团训练详细信息")
    @PreAuthorize("@ss.hasPermi('bModularity:club:query')")
    @GetMapping(value = "/{tcId}")
    public AjaxResult getInfo(@PathVariable("tcId") Long tcId)
    {
        return success(b1FranchiseClubService.selectB1FranchiseClubByTcId(tcId));
    }
    
    /**
     * 新增球队、社团训练
     */
    @ApiOperation("新增球队、社团训练")
    @PreAuthorize("@ss.hasPermi('bModularity:club:add')")
    @Log(title = "球队、社团训练", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B1FranchiseClub b1FranchiseClub)
    {
        return toAjax(b1FranchiseClubService.insertB1FranchiseClub(b1FranchiseClub));
    }
    
    /**
     * 修改球队、社团训练
     */
    @ApiOperation("修改球队、社团训练")
    @PreAuthorize("@ss.hasPermi('bModularity:club:edit')")
    @Log(title = "球队、社团训练", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B1FranchiseClub b1FranchiseClub)
    {
        return toAjax(b1FranchiseClubService.updateB1FranchiseClub(b1FranchiseClub));
    }
    
    /**
     * 删除球队、社团训练
     */
    @ApiOperation("删除球队、社团训练")
    @PreAuthorize("@ss.hasPermi('bModularity:club:remove')")
    @Log(title = "球队、社团训练", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tcIds}")
    public AjaxResult remove(@PathVariable Long[] tcIds)
    {
        return toAjax(b1FranchiseClubService.deleteB1FranchiseClubByTcIds(tcIds));
    }
}
