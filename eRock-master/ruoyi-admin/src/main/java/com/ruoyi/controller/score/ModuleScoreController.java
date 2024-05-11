package com.ruoyi.controller.score;

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
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.service.IModuleScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模块分数Controller
 *
 * @author houq
 * @date 2024-04-22
 */
@Api(tags = {"模块分数"})
@RestController
@RequestMapping("/score/score")
public class ModuleScoreController extends BaseController
{
    @Autowired
    private IModuleScoreService moduleScoreService;

    /**
     * 查询模块分数列表
     */
    @ApiOperation("查询模块分数列表")
    @PreAuthorize("@ss.hasPermi('score:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(ModuleScore moduleScore)
    {
        startPage();
        List<ModuleScore> list = moduleScoreService.selectModuleScoreList(moduleScore);
        return getDataTable(list);
    }

    /**
     * 导出模块分数列表
     */
    @ApiOperation("导出模块分数列表")
    @PreAuthorize("@ss.hasPermi('score:score:export')")
    @Log(title = "模块分数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ModuleScore moduleScore)
    {
        List<ModuleScore> list = moduleScoreService.selectModuleScoreList(moduleScore);
        ExcelUtil<ModuleScore> util = new ExcelUtil<ModuleScore>(ModuleScore.class);
        util.exportExcel(response, list, "模块分数数据");
    }

    /**
     * 获取模块分数详细信息
     */
    @ApiOperation("获取模块分数详细信息")
    @PreAuthorize("@ss.hasPermi('score:score:query')")
    @GetMapping(value = "/{modId}")
    public AjaxResult getInfo(@PathVariable("modId") Long modId)
    {
        return success(moduleScoreService.selectModuleScoreByModId(modId));
    }

    /**
     * 新增模块分数
     */
    @ApiOperation("新增模块分数")
    @PreAuthorize("@ss.hasPermi('score:score:add')")
    @Log(title = "模块分数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ModuleScore moduleScore)
    {
        return toAjax(moduleScoreService.insertModuleScore(moduleScore));
    }

    /**
     * 修改模块分数
     */
    @ApiOperation("修改模块分数")
    @PreAuthorize("@ss.hasPermi('score:score:edit')")
    @Log(title = "模块分数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ModuleScore moduleScore)
    {
        return toAjax(moduleScoreService.updateModuleScore(moduleScore));
    }

    /**
     * 删除模块分数
     */
    @ApiOperation("删除模块分数")
    @PreAuthorize("@ss.hasPermi('score:score:remove')")
    @Log(title = "模块分数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{modIds}")
    public AjaxResult remove(@PathVariable Long[] modIds)
    {
        return toAjax(moduleScoreService.deleteModuleScoreByModIds(modIds));
    }
}
