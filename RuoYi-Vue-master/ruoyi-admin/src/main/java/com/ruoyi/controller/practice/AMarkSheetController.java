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
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.service.IAMarkSheetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 练习、测试评分表Controller
 *
 * @author houq
 * @date 2024-04-09
 */
@Api(tags = {"练习、测试评分表"})
@RestController
@RequestMapping("/practice/sheet")
public class AMarkSheetController extends BaseController
{
    @Autowired
    private IAMarkSheetService aMarkSheetService;

    /**
     * 查询练习、测试评分表列表
     */
    @ApiOperation("查询练习、测试评分表列表")
    @PreAuthorize("@ss.hasPermi('practice:sheet:list')")
    @GetMapping("/list")
    public TableDataInfo list(AMarkSheet aMarkSheet)
    {
        startPage();
        List<AMarkSheet> list = aMarkSheetService.selectAMarkSheetList(aMarkSheet);
        return getDataTable(list);
    }

    /**
     * 导出练习、测试评分表列表
     */
    @ApiOperation("导出练习、测试评分表列表")
    @PreAuthorize("@ss.hasPermi('practice:sheet:export')")
    @Log(title = "练习、测试评分表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AMarkSheet aMarkSheet)
    {
        List<AMarkSheet> list = aMarkSheetService.selectAMarkSheetList(aMarkSheet);
        ExcelUtil<AMarkSheet> util = new ExcelUtil<AMarkSheet>(AMarkSheet.class);
        util.exportExcel(response, list, "练习、测试评分表数据");
    }

    /**
     * 获取练习、测试评分表详细信息
     */
    @ApiOperation("获取练习、测试评分表详细信息")
    @PreAuthorize("@ss.hasPermi('practice:sheet:query')")
    @GetMapping(value = "/{msId}")
    public AjaxResult getInfo(@PathVariable("msId") Long msId)
    {
        return success(aMarkSheetService.selectAMarkSheetByMsId(msId));
    }

    /**
     * 新增练习、测试评分表
     */
    @ApiOperation("新增练习、测试评分表")
    @PreAuthorize("@ss.hasPermi('practice:sheet:add')")
    @Log(title = "练习、测试评分表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AMarkSheet aMarkSheet)
    {
        return toAjax(aMarkSheetService.insertAMarkSheet(aMarkSheet));
    }

    /**
     * 修改练习、测试评分表
     */
    @ApiOperation("修改练习、测试评分表")
    @PreAuthorize("@ss.hasPermi('practice:sheet:edit')")
    @Log(title = "练习、测试评分表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AMarkSheet aMarkSheet)
    {
        return toAjax(aMarkSheetService.updateAMarkSheet(aMarkSheet));
    }

    /**
     * 删除练习、测试评分表
     */
    @ApiOperation("删除练习、测试评分表")
    @PreAuthorize("@ss.hasPermi('practice:sheet:remove')")
    @Log(title = "练习、测试评分表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{msIds}")
    public AjaxResult remove(@PathVariable Long[] msIds)
    {
        return toAjax(aMarkSheetService.deleteAMarkSheetByMsIds(msIds));
    }
}
