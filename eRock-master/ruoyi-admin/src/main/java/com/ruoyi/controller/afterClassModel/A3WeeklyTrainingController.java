package com.ruoyi.controller.afterClassModel;

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
import com.ruoyi.afterClassModel.domain.A3WeeklyTraining;
import com.ruoyi.afterClassModel.service.IA3WeeklyTrainingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A3 学生周训资源Controller
 *
 * @author heye
 * @date 2024-04-15
 */
@Api(tags = {"A3 学生周训资源"})
@RestController
@RequestMapping("/resource/training")
public class A3WeeklyTrainingController extends BaseController
{
    @Autowired
    private IA3WeeklyTrainingService a3WeeklyTrainingService;

    @Autowired
    SelectUser selectUser;
    /**
     * 查询A3 学生周训资源列表
     */
    @ApiOperation("查询A3 学生周训资源列表")
    @PreAuthorize("@ss.hasPermi('resource:training:list')")
    @GetMapping("/list")
    public TableDataInfo list(A3WeeklyTraining a3WeeklyTraining)
    {
        startPage();
        List<A3WeeklyTraining> list = a3WeeklyTrainingService.selectA3WeeklyTrainingList(a3WeeklyTraining);
        return getDataTable(list);
    }

    /**
     * 导出A3 学生周训资源列表
     */
    @ApiOperation("导出A3 学生周训资源列表")
    @PreAuthorize("@ss.hasPermi('resource:training:export')")
    @Log(title = "A3 学生周训资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A3WeeklyTraining a3WeeklyTraining)
    {
        List<A3WeeklyTraining> list = a3WeeklyTrainingService.selectA3WeeklyTrainingList(a3WeeklyTraining);
        ExcelUtil<A3WeeklyTraining> util = new ExcelUtil<A3WeeklyTraining>(A3WeeklyTraining.class);
        util.exportExcel(response, list, "A3 学生周训资源数据");
    }

    /**
     * 获取A3 学生周训资源详细信息
     */
    @ApiOperation("获取A3 学生周训资源详细信息")
    @PreAuthorize("@ss.hasPermi('resource:training:query')")
    @GetMapping(value = "/{phtrId}")
    public AjaxResult getInfo(@PathVariable("phtrId") Long phtrId)
    {
        return success(selectUser.selectFrequency(a3WeeklyTrainingService.selectA3WeeklyTrainingByWetrId(phtrId),phtrId));
    }

    /**
     * 新增A3 学生周训资源
     */
    @ApiOperation("新增A3 学生周训资源")
    @PreAuthorize("@ss.hasPermi('resource:training:add')")
    @Log(title = "A3 学生周训资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A3WeeklyTraining a3WeeklyTraining)
    {
        int i = a3WeeklyTrainingService.insertA3WeeklyTraining(a3WeeklyTraining);
        if (i == -10) {
            return warn("本周打卡成功，请下周再试");
        } else if (i == 0) {
            return warn("打卡间隔不足一天，请明天再试");
        } else if (i == -1) {
            return  warn("打卡时间不在训练时间内，请在训练时间内打卡");
        } else if (i == -2) {
            return warn("任务不存在，请联系管理员");
        }
        return toAjax(i);
    }

    /**
     * 修改A3 学生周训资源
     */
    @ApiOperation("修改A3 学生周训资源")
    @PreAuthorize("@ss.hasPermi('resource:training:edit')")
    @Log(title = "A3 学生周训资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A3WeeklyTraining a3WeeklyTraining)
    {
        return toAjax(a3WeeklyTrainingService.updateA3WeeklyTraining(a3WeeklyTraining));
    }

    /**
     * 删除A3 学生周训资源
     */
    @ApiOperation("删除A3 学生周训资源")
    @PreAuthorize("@ss.hasPermi('resource:training:remove')")
    @Log(title = "A3 学生周训资源", businessType = BusinessType.DELETE)
    @DeleteMapping("/{wetrIds}")
    public AjaxResult remove(@PathVariable Long[] wetrIds)
    {
        return toAjax(a3WeeklyTrainingService.deleteA3WeeklyTrainingByWetrIds(wetrIds));
    }
}
