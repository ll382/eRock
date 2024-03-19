package com.ruoyi.web.controller.match;

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
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.service.ICBallteamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 球队参赛Controller
 *
 * @author heye
 * @date 2024-03-19
 */
@Api("C模块球队参赛")
@RestController
@RequestMapping("/match/ballteam")
public class CBallteamController extends BaseController
{
    @Autowired
    private ICBallteamService cBallteamService;

    /**
     * 查询球队参赛列表
     */
    @ApiOperation("查询球队参赛列表")
    @PreAuthorize("@ss.hasPermi('match:ballteam:list')")
    @GetMapping("/list")
    public TableDataInfo list(CBallteam cBallteam)
    {
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
    public void export(HttpServletResponse response, CBallteam cBallteam)
    {
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
    public AjaxResult getInfo(@PathVariable("balId") Long balId)
    {
        return success(cBallteamService.selectCBallteamByBalId(balId));
    }

    /**
     * 新增球队参赛
     */
    @ApiOperation("新增球队参赛")
    @PreAuthorize("@ss.hasPermi('match:ballteam:add')")
    @Log(title = "球队参赛", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CBallteam cBallteam)
    {
        return toAjax(cBallteamService.insertCBallteam(cBallteam));
    }

    /**
     * 修改球队参赛
     */
    @ApiOperation("修改球队参赛")
    @PreAuthorize("@ss.hasPermi('match:ballteam:edit')")
    @Log(title = "球队参赛", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CBallteam cBallteam)
    {
        return toAjax(cBallteamService.updateCBallteam(cBallteam));
    }

    /**
     * 删除球队参赛
     */
    @ApiOperation("删除球队参赛")
    @PreAuthorize("@ss.hasPermi('match:ballteam:remove')")
    @Log(title = "球队参赛", businessType = BusinessType.DELETE)
	@DeleteMapping("/{balIds}")
    public AjaxResult remove(@PathVariable Long[] balIds)
    {
        return toAjax(cBallteamService.deleteCBallteamByBalIds(balIds));
    }
}
