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
import com.ruoyi.score.domain.DModelScore;
import com.ruoyi.score.service.IDModelScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * D 模块成绩Controller
 *
 * @author heye
 * @date 2024-04-25
 */
@Api(tags = {"D 模块成绩"})
@RestController
@RequestMapping("/score/dScore")
public class DModelScoreController extends BaseController
{
    @Autowired
    private IDModelScoreService dModelScoreService;

    /**
     * 查询D 模块成绩列表
     */
    @ApiOperation("查询D 模块成绩列表")
    @PreAuthorize("@ss.hasPermi('score:dScore:list')")
    @GetMapping("/list")
    public TableDataInfo list(DModelScore dModelScore)
    {
        startPage();
        List<DModelScore> list = dModelScoreService.selectDModelScoreList(dModelScore);
        return getDataTable(list);
    }

    /**
     * 导出D 模块成绩列表
     */
    @ApiOperation("导出D 模块成绩列表")
    @PreAuthorize("@ss.hasPermi('score:dScore:export')")
    @Log(title = "D 模块成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DModelScore dModelScore)
    {
        List<DModelScore> list = dModelScoreService.selectDModelScoreList(dModelScore);
        ExcelUtil<DModelScore> util = new ExcelUtil<DModelScore>(DModelScore.class);
        util.exportExcel(response, list, "D 模块成绩数据");
    }

    /**
     * 获取D 模块成绩详细信息
     */
    @ApiOperation("获取D 模块成绩详细信息")
    @PreAuthorize("@ss.hasPermi('score:dScore:query')")
    @GetMapping(value = "/{modscId}")
    public AjaxResult getInfo(@PathVariable("modscId") Long modscId)
    {
        return success(dModelScoreService.selectDModelScoreByModscId(modscId));
    }

    /**
     * 新增D 模块成绩
     */
    @ApiOperation("新增D 模块成绩")
    @PreAuthorize("@ss.hasPermi('score:dScore:add')")
    @Log(title = "D 模块成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DModelScore dModelScore)
    {
        return toAjax(dModelScoreService.insertDModelScore(dModelScore));
    }

    /**
     * 修改D 模块成绩
     */
    @ApiOperation("修改D 模块成绩")
    @PreAuthorize("@ss.hasPermi('score:dScore:edit')")
    @Log(title = "D 模块成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DModelScore dModelScore)
    {
        return toAjax(dModelScoreService.updateDModelScore(dModelScore));
    }

    /**
     * 删除D 模块成绩
     */
    @ApiOperation("删除D 模块成绩")
    @PreAuthorize("@ss.hasPermi('score:dScore:remove')")
    @Log(title = "D 模块成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{modscIds}")
    public AjaxResult remove(@PathVariable Long[] modscIds)
    {
        return toAjax(dModelScoreService.deleteDModelScoreByModscIds(modscIds));
    }
}
