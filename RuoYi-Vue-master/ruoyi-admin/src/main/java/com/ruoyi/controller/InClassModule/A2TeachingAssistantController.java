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
import com.ruoyi.InClassModule.domain.A2TeachingAssistant;
import com.ruoyi.InClassModule.service.IA2TeachingAssistantService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2 合作学习 助教评价Controller
 *
 * @author heye
 * @date 2024-04-11
 */
@Api(tags = {"A2 合作学习 助教评价 "})
@RestController
@RequestMapping("/teaAssistant/assistant")
public class A2TeachingAssistantController extends BaseController
{
    @Autowired
    private IA2TeachingAssistantService a2TeachingAssistantService;



    /**
     * 查询A2 合作学习 助教评价列表
     */
    @ApiOperation("查询A2 合作学习 助教评价 列表")
    @PreAuthorize("@ss.hasPermi('teaAssistant:assistant:list')")
    @GetMapping("/list")
    public TableDataInfo list(A2TeachingAssistant a2TeachingAssistant)
    {
        startPage();
        List<A2TeachingAssistant> list = a2TeachingAssistantService.selectA2TeachingAssistantList(a2TeachingAssistant);
        return getDataTable(list);
    }

    /**
     * 导出A2 合作学习 助教评价列表
     */
    @ApiOperation("导出A2 合作学习 助教评价 列表")
    @PreAuthorize("@ss.hasPermi('teaAssistant:assistant:export')")
    @Log(title = "A2 合作学习 助教评价 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2TeachingAssistant a2TeachingAssistant)
    {
        List<A2TeachingAssistant> list = a2TeachingAssistantService.selectA2TeachingAssistantList(a2TeachingAssistant);
        ExcelUtil<A2TeachingAssistant> util = new ExcelUtil<A2TeachingAssistant>(A2TeachingAssistant.class);
        util.exportExcel(response, list, "A2 合作学习 助教评价 数据");
    }

    /**
     * 获取A2 合作学习 助教评价详细信息
     */
    @ApiOperation("获取A2 合作学习 助教评价 详细信息")
    @PreAuthorize("@ss.hasPermi('teaAssistant:assistant:query')")
    @GetMapping(value = "/{crId}")
    public AjaxResult getInfo(@PathVariable("crId") Long crId)
    {
        startPage();
        return success(a2TeachingAssistantService.selectA2TeachingAssistantByTaId(crId));
    }

    /**
     * 新增A2 合作学习 助教评价
     */
    @ApiOperation("新增A2 合作学习 助教评价 ")
    @PreAuthorize("@ss.hasPermi('teaAssistant:assistant:add')")
    @Log(title = "A2 合作学习 助教评价 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2TeachingAssistant a2TeachingAssistant)
    {
        return toAjax(a2TeachingAssistantService.insertA2TeachingAssistant(a2TeachingAssistant));
    }

    /**
     * 修改A2 合作学习 助教评价
     */
    @ApiOperation("修改A2 合作学习 助教评价 ")
    @PreAuthorize("@ss.hasPermi('teaAssistant:assistant:edit')")
    @Log(title = "A2 合作学习 助教评价 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2TeachingAssistant a2TeachingAssistant)
    {
        return toAjax(a2TeachingAssistantService.updateA2TeachingAssistant(a2TeachingAssistant));
    }

    /**
     * 删除A2 合作学习 助教评价
     */
    @ApiOperation("删除A2 合作学习 助教评价 ")
    @PreAuthorize("@ss.hasPermi('teaAssistant:assistant:remove')")
    @Log(title = "A2 合作学习 助教评价 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taIds}")
    public AjaxResult remove(@PathVariable Long[] taIds)
    {
        return toAjax(a2TeachingAssistantService.deleteA2TeachingAssistantByTaIds(taIds));
    }
}
