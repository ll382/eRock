package com.ruoyi.controller.core;

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
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.service.IAModuleScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 *  A 模块成绩Controller
 *
 * @author heye
 * @date 2024-04-28
 */
@Api(tags = {" A 模块成绩"})
@RestController
@RequestMapping("/amodel/score")
public class AModuleScoreController extends BaseController
{
    @Autowired
    private IAModuleScoreService aModuleScoreService;

    /**
     * 查询 A 模块成绩列表
     */
    @ApiOperation("查询 A 模块成绩列表")
    @PreAuthorize("@ss.hasPermi('amodel:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(AModuleScore aModuleScore)
    {
        startPage();
        List<AModuleScore> list = aModuleScoreService.selectAModuleScoreList(aModuleScore);
        return getDataTable(list);
    }

    /**
     * 导出 A 模块成绩列表
     */
    @ApiOperation("导出 A 模块成绩列表")
    @PreAuthorize("@ss.hasPermi('amodel:score:export')")
    @Log(title = " A 模块成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AModuleScore aModuleScore)
    {
        List<AModuleScore> list = aModuleScoreService.selectAModuleScoreList(aModuleScore);
        ExcelUtil<AModuleScore> util = new ExcelUtil<AModuleScore>(AModuleScore.class);
        util.exportExcel(response, list, " A 模块成绩数据");
    }

    /**
     * 获取 A 模块成绩详细信息
     */
    @ApiOperation("获取 A 模块成绩详细信息")
    @PreAuthorize("@ss.hasPermi('amodel:score:query')")
    @GetMapping(value = "/{modscoId}")
    public AjaxResult getInfo(@PathVariable("modscoId") Long modscoId)
    {
        return success(aModuleScoreService.selectAModuleScoreByModscoId(modscoId));
    }

    /**
     * 新增 A 模块成绩
     */
    @ApiOperation("新增 A 模块成绩")
    @PreAuthorize("@ss.hasPermi('amodel:score:add')")
    @Log(title = " A 模块成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AModuleScore aModuleScore)
    {
        return toAjax(aModuleScoreService.insertAModuleScore(aModuleScore));
    }

    /**
     * 修改 A 模块成绩
     */
    @ApiOperation("修改 A 模块成绩")
    @PreAuthorize("@ss.hasPermi('amodel:score:edit')")
    @Log(title = " A 模块成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AModuleScore aModuleScore)
    {
        return toAjax(aModuleScoreService.updateAModuleScore(aModuleScore));
    }

    /**
     * 删除 A 模块成绩
     */
    @ApiOperation("删除 A 模块成绩")
    @PreAuthorize("@ss.hasPermi('amodel:score:remove')")
    @Log(title = " A 模块成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{modscoIds}")
    public AjaxResult remove(@PathVariable Long[] modscoIds)
    {
        return toAjax(aModuleScoreService.deleteAModuleScoreByModscoIds(modscoIds));
    }
}
