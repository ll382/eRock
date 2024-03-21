package com.ruoyi.controller.teachingExchange;

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
import com.ruoyi.teachingExchange.domain.A1Communication;
import com.ruoyi.teachingExchange.service.IA1CommunicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * A1交流Controller
 *
 * @author heye
 * @date 2024-03-21
 */
@Api(tags = {"A1交流"})
@RestController
@RequestMapping("/communication/communication")
public class A1CommunicationController extends BaseController
{
    @Autowired
    private IA1CommunicationService a1CommunicationService;

    /**
     * 查询A1交流列表
     */
    @ApiOperation("查询A1交流列表")
    @PreAuthorize("@ss.hasPermi('communication:communication:list')")
    @GetMapping("/list")
    public AjaxResult list(A1Communication a1Communication)
    {
        List<A1Communication> list = a1CommunicationService.selectA1CommunicationList(a1Communication);
        return success(list);
    }

    /**
     * 导出A1交流列表
     */
    @ApiOperation("导出A1交流列表")
    @PreAuthorize("@ss.hasPermi('communication:communication:export')")
    @Log(title = "A1交流", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A1Communication a1Communication)
    {
        List<A1Communication> list = a1CommunicationService.selectA1CommunicationList(a1Communication);
        ExcelUtil<A1Communication> util = new ExcelUtil<A1Communication>(A1Communication.class);
        util.exportExcel(response, list, "A1交流数据");
    }

    /**
     * 获取A1交流详细信息
     */
    @ApiOperation("获取A1交流详细信息")
    @PreAuthorize("@ss.hasPermi('communication:communication:query')")
    @GetMapping(value = "/{comId}")
    public AjaxResult getInfo(@PathVariable("comId") Long comId)
    {
        return success(a1CommunicationService.selectA1CommunicationByComId(comId));
    }

    /**
     * 新增A1交流
     */
    @ApiOperation("新增A1交流")
    @PreAuthorize("@ss.hasPermi('communication:communication:add')")
    @Log(title = "A1交流", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A1Communication a1Communication)
    {
        return toAjax(a1CommunicationService.insertA1Communication(a1Communication));
    }

    /**
     * 修改A1交流
     */
    @ApiOperation("修改A1交流")
    @PreAuthorize("@ss.hasPermi('communication:communication:edit')")
    @Log(title = "A1交流", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A1Communication a1Communication)
    {
        return toAjax(a1CommunicationService.updateA1Communication(a1Communication));
    }

    /**
     * 删除A1交流
     */
    @ApiOperation("删除A1交流")
    @PreAuthorize("@ss.hasPermi('communication:communication:remove')")
    @Log(title = "A1交流", businessType = BusinessType.DELETE)
	@DeleteMapping("/{comIds}")
    public AjaxResult remove(@PathVariable Long[] comIds)
    {
        return toAjax(a1CommunicationService.deleteA1CommunicationByComIds(comIds));
    }
}
