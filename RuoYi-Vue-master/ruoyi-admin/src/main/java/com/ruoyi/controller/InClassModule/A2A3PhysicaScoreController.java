package com.ruoyi.controller.InClassModule;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.core.service.SelectUser;
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
import com.ruoyi.InClassModule.domain.A2A3PhysicaScore;
import com.ruoyi.InClassModule.service.IA2A3PhysicaScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2 A3 体能成绩Controller
 *
 * @author heye
 * @date 2024-04-14
 */
@Api(tags = {"A2 A3 体能成绩"})
@RestController
@RequestMapping("/staminaScore/score")
public class A2A3PhysicaScoreController extends BaseController
{
    @Autowired
    private IA2A3PhysicaScoreService a2A3PhysicaScoreService;

    @Autowired
    SelectUser selectUser;

    /**
     * 查询A2 A3 体能成绩列表
     */
    @ApiOperation("查询A2 A3 体能成绩列表")
    @PreAuthorize("@ss.hasPermi('staminaScore:score:list')")
    @GetMapping("/list")
    public AjaxResult list(A2A3PhysicaScore a2A3PhysicaScore)
    {
        startPage();
        List<A2A3PhysicaScore> list = a2A3PhysicaScoreService.selectA2A3PhysicaScoreList(a2A3PhysicaScore);
        return AjaxResult.success(selectUser.selectUndoneStudent(list));
    }

    /**
     * 导出A2 A3 体能成绩列表
     */
    @ApiOperation("导出A2 A3 体能成绩列表")
    @PreAuthorize("@ss.hasPermi('staminaScore:score:export')")
    @Log(title = "A2 A3 体能成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2A3PhysicaScore a2A3PhysicaScore)
    {
        List<A2A3PhysicaScore> list = a2A3PhysicaScoreService.selectA2A3PhysicaScoreList(a2A3PhysicaScore);
        ExcelUtil<A2A3PhysicaScore> util = new ExcelUtil<A2A3PhysicaScore>(A2A3PhysicaScore.class);
        util.exportExcel(response, list, "A2 A3 体能成绩数据");
    }

    /**
     * 获取A2 A3 体能成绩详细信息
     */
    @ApiOperation("获取A2 A3 体能成绩详细信息")
    @PreAuthorize("@ss.hasPermi('staminaScore:score:query')")
    @GetMapping(value = "/{physId}")
    public AjaxResult getInfo(@PathVariable("physId") Long physId)
    {
        return success(a2A3PhysicaScoreService.selectA2A3PhysicaScoreByPhysId(physId));
    }

    /**
     * 新增A2 A3 体能成绩
     */
    @ApiOperation("新增A2 A3 体能成绩")
    @PreAuthorize("@ss.hasPermi('staminaScore:score:add')")
    @Log(title = "A2 A3 体能成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2A3PhysicaScore a2A3PhysicaScore)
    {
        return toAjax(a2A3PhysicaScoreService.insertA2A3PhysicaScore(a2A3PhysicaScore));
    }

    /**
     * 修改A2 A3 体能成绩
     */
    @ApiOperation("修改A2 A3 体能成绩")
    @PreAuthorize("@ss.hasPermi('staminaScore:score:edit')")
    @Log(title = "A2 A3 体能成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2A3PhysicaScore a2A3PhysicaScore)
    {
        return toAjax(a2A3PhysicaScoreService.updateA2A3PhysicaScore(a2A3PhysicaScore));
    }

    /**
     * 删除A2 A3 体能成绩
     */
    @ApiOperation("删除A2 A3 体能成绩")
    @PreAuthorize("@ss.hasPermi('staminaScore:score:remove')")
    @Log(title = "A2 A3 体能成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{physIds}")
    public AjaxResult remove(@PathVariable Long[] physIds)
    {
        return toAjax(a2A3PhysicaScoreService.deleteA2A3PhysicaScoreByPhysIds(physIds));
    }
}
