package com.ruoyi.controller.practice;

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
import com.ruoyi.practice.domain.ABallExam;
import com.ruoyi.practice.service.IABallExamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A 运球a_mark_sheetms_idController
 *
 * @author ljy
 * @date 2024-04-11
 */
@Api(tags = {"A 运球a_mark_sheetms_id"})
@RestController
@RequestMapping("/practice/ABallExam")
public class ABallExamController extends BaseController
{
    @Autowired
    private IABallExamService aBallExamService;

    /**
     * PC端三页面折线图数据的雷达图
     * 查询A 运球a_mark_sheetms_id列表
     */
    @ApiOperation("查询A 运球a_mark_sheetms_id列表")
    @PreAuthorize("@ss.hasPermi('practice:ABallExam:list')")
    @GetMapping("/list")
    public TableDataInfo list(ABallExam aBallExam)
    {
        startPage();
        List<ABallExam> list = aBallExamService.selectABallExamList(aBallExam);
        return getDataTable(list);
    }

    /**
     * 导出A 运球a_mark_sheetms_id列表
     */
    @ApiOperation("导出A 运球a_mark_sheetms_id列表")
    @PreAuthorize("@ss.hasPermi('practice:ABallExam:export')")
    @Log(title = "A 运球a_mark_sheetms_id", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ABallExam aBallExam)
    {
        List<ABallExam> list = aBallExamService.selectABallExamList(aBallExam);
        ExcelUtil<ABallExam> util = new ExcelUtil<ABallExam>(ABallExam.class);
        util.exportExcel(response, list, "A 运球a_mark_sheetms_id数据");
    }

    /**
     * 获取A 运球a_mark_sheetms_id详细信息
     */
    @ApiOperation("获取A 运球a_mark_sheetms_id详细信息")
    @PreAuthorize("@ss.hasPermi('practice:ABallExam:query')")
    @GetMapping(value = "/{beId}")
    public AjaxResult getInfo(@PathVariable("beId") Long beId)
    {
        return success(aBallExamService.selectABallExamByBeId(beId));
    }

    /**
     * PC端三页面柱状图数据的雷达图
     * 查询ABallExam详细信息
     */
    @GetMapping(value = "/{enumId}/{stuId}/{msDribble}/{crDate}")
    public AjaxResult getABallExamByEnumIdByStuIdByCrData(@PathVariable("enumId") Integer enumId,@PathVariable("stuId") Long stuId,@PathVariable("msDribble") String msDribble,@PathVariable("crDate") String crDate){
        return success(aBallExamService.selectABallExamByEnumIdByStuIdByCrData(enumId,stuId,msDribble,crDate));
    }

    /**
     * 新增A 运球a_mark_sheetms_id
     */
    @ApiOperation("新增A 运球a_mark_sheetms_id")
    @PreAuthorize("@ss.hasPermi('practice:ABallExam:add')")
    @Log(title = "A 运球a_mark_sheetms_id", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ABallExam aBallExam)
    {
        return toAjax(aBallExamService.insertABallExam(aBallExam));
    }

    /**
     * 修改A 运球a_mark_sheetms_id
     */
    @ApiOperation("修改A 运球a_mark_sheetms_id")
    @PreAuthorize("@ss.hasPermi('practice:ABallExam:edit')")
    @Log(title = "A 运球a_mark_sheetms_id", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ABallExam aBallExam)
    {
        return toAjax(aBallExamService.updateABallExam(aBallExam));
    }

    /**
     * 删除A 运球a_mark_sheetms_id
     */
    @ApiOperation("删除A 运球a_mark_sheetms_id")
    @PreAuthorize("@ss.hasPermi('practice:ABallExam:remove')")
    @Log(title = "A 运球a_mark_sheetms_id", businessType = BusinessType.DELETE)
	@DeleteMapping("/{beIds}")
    public AjaxResult remove(@PathVariable Long[] beIds)
    {
        return toAjax(aBallExamService.deleteABallExamByBeIds(beIds));
    }

}
