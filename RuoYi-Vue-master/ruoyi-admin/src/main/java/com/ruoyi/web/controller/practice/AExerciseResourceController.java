package com.ruoyi.web.controller.practice;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.practice.domain.AExerciseResource;
import com.ruoyi.practice.service.IAExerciseResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 练习资源表Controller
 * 
 * @author houq
 * @date 2024-03-19
 */
@RestController
@RequestMapping("/practice/resource")
public class AExerciseResourceController extends BaseController
{
    @Autowired
    private IAExerciseResourceService aExerciseResourceService;

    /**
     * 查询练习资源表列表
     */
    @PreAuthorize("@ss.hasPermi('practice:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(AExerciseResource aExerciseResource)
    {
        startPage();
        List<AExerciseResource> list = aExerciseResourceService.selectAExerciseResourceList(aExerciseResource);
        return getDataTable(list);
    }

    /**
     * 导出练习资源表列表
     */
    @PreAuthorize("@ss.hasPermi('practice:resource:export')")
    @Log(title = "练习资源表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AExerciseResource aExerciseResource)
    {
        List<AExerciseResource> list = aExerciseResourceService.selectAExerciseResourceList(aExerciseResource);
        ExcelUtil<AExerciseResource> util = new ExcelUtil<AExerciseResource>(AExerciseResource.class);
        util.exportExcel(response, list, "练习资源表数据");
    }

    /**
     * 获取练习资源表详细信息
     */
    @PreAuthorize("@ss.hasPermi('practice:resource:query')")
    @GetMapping(value = "/{erId}")
    public AjaxResult getInfo(@PathVariable("erId") Long erId)
    {
        return success(aExerciseResourceService.selectAExerciseResourceByErId(erId));
    }

    /**
     * 新增练习资源表
     */
    @PreAuthorize("@ss.hasPermi('practice:resource:add')")
    @Log(title = "练习资源表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AExerciseResource aExerciseResource)
    {
        return toAjax(aExerciseResourceService.insertAExerciseResource(aExerciseResource));
    }

    /**
     * 修改练习资源表
     */
    @PreAuthorize("@ss.hasPermi('practice:resource:edit')")
    @Log(title = "练习资源表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AExerciseResource aExerciseResource)
    {
        return toAjax(aExerciseResourceService.updateAExerciseResource(aExerciseResource));
    }

    /**
     * 删除练习资源表
     */
    @PreAuthorize("@ss.hasPermi('practice:resource:remove')")
    @Log(title = "练习资源表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{erIds}")
    public AjaxResult remove(@PathVariable Long[] erIds)
    {
        return toAjax(aExerciseResourceService.deleteAExerciseResourceByErIds(erIds));
    }
}
