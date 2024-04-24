package com.ruoyi.controller.afterClassModel;

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
import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;
import com.ruoyi.afterClassModel.service.IA3PhysicalTrainingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A3 体能训练周Controller
 *
 * @author heye
 * @date 2024-04-15
 */
@Api(tags = {"A3 体能训练周"})
@RestController
@RequestMapping("/orientationWeek/trainingWeek")
public class A3PhysicalTrainingController extends BaseController
{
    @Autowired
    private IA3PhysicalTrainingService a3PhysicalTrainingService;

    /**
     * 查询A3 体能训练周列表
     */
    @ApiOperation("查询A3 体能训练周列表")
    @PreAuthorize("@ss.hasPermi('orientationWeek:trainingWeek:list')")
    @GetMapping("/list")
    public TableDataInfo list(A3PhysicalTraining a3PhysicalTraining)
    {
        startPage();
        List<A3PhysicalTraining> list = a3PhysicalTrainingService.selectA3PhysicalTrainingList(a3PhysicalTraining);
        return getDataTable(list);
    }

    /**
     * 导出A3 体能训练周列表
     */
    @ApiOperation("导出A3 体能训练周列表")
    @PreAuthorize("@ss.hasPermi('orientationWeek:trainingWeek:export')")
    @Log(title = "A3 体能训练周", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A3PhysicalTraining a3PhysicalTraining)
    {
        List<A3PhysicalTraining> list = a3PhysicalTrainingService.selectA3PhysicalTrainingList(a3PhysicalTraining);
        ExcelUtil<A3PhysicalTraining> util = new ExcelUtil<A3PhysicalTraining>(A3PhysicalTraining.class);
        util.exportExcel(response, list, "A3 体能训练周数据");
    }

    /**
     * 获取A3 体能训练周详细信息
     */
    @ApiOperation("获取A3 体能训练周详细信息")
    @PreAuthorize("@ss.hasPermi('orientationWeek:trainingWeek:query')")
    @GetMapping(value = "/{phtrId}")
    public AjaxResult getInfo(@PathVariable("phtrId") Long phtrId)
    {
        return success(a3PhysicalTrainingService.selectA3PhysicalTrainingByPhtrId(phtrId));
    }

    /**
     * 新增A3 体能训练周
     */
    @ApiOperation("新增A3 体能训练周")
    @PreAuthorize("@ss.hasPermi('orientationWeek:trainingWeek:add')")
    @Log(title = "A3 体能训练周", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A3PhysicalTraining a3PhysicalTraining)
    {
        return toAjax(a3PhysicalTrainingService.insertA3PhysicalTraining(a3PhysicalTraining));
    }

    /**
     * 修改A3 体能训练周
     */
    @ApiOperation("修改A3 体能训练周")
    @PreAuthorize("@ss.hasPermi('orientationWeek:trainingWeek:edit')")
    @Log(title = "A3 体能训练周", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A3PhysicalTraining a3PhysicalTraining)
    {
        return toAjax(a3PhysicalTrainingService.updateA3PhysicalTraining(a3PhysicalTraining));
    }

    /**
     * 删除A3 体能训练周
     */
    @ApiOperation("删除A3 体能训练周")
    @PreAuthorize("@ss.hasPermi('orientationWeek:trainingWeek:remove')")
    @Log(title = "A3 体能训练周", businessType = BusinessType.DELETE)
	@DeleteMapping("/{phtrIds}")
    public AjaxResult remove(@PathVariable Long[] phtrIds)
    {
        return toAjax(a3PhysicalTrainingService.deleteA3PhysicalTrainingByPhtrIds(phtrIds));
    }
}
