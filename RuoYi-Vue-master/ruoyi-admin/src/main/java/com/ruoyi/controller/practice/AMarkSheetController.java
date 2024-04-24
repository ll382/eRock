package com.ruoyi.controller.practice;

import java.util.List;
import java.util.Map;
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
 *  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算Controller
 *
 * @author ljy
 * @date 2024-04-11
 */
@Api(tags = {" A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算"})
@RestController
@RequestMapping("/practice/AMarkSheet")
public class AMarkSheetController extends BaseController
{
    @Autowired
    private IAMarkSheetService aMarkSheetService;

    /**
     * 查询 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算列表
     */
    @ApiOperation("查询 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算列表")
    @PreAuthorize("@ss.hasPermi('practice:AMarkSheet:list')")
    @GetMapping("/list")
    public TableDataInfo list(AMarkSheet aMarkSheet)
    {
        startPage();
        List<AMarkSheet> list = aMarkSheetService.selectAMarkSheetList(aMarkSheet);
        return getDataTable(list);
    }

    /**
     * 导出 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算列表
     */
    @ApiOperation("导出 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算列表")
    @PreAuthorize("@ss.hasPermi('practice:AMarkSheet:export')")
    @Log(title = " A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AMarkSheet aMarkSheet)
    {
        List<AMarkSheet> list = aMarkSheetService.selectAMarkSheetList(aMarkSheet);
        ExcelUtil<AMarkSheet> util = new ExcelUtil<AMarkSheet>(AMarkSheet.class);
        util.exportExcel(response, list, " A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算数据");
    }

    /**
     * 获取 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算详细信息
     */
    @ApiOperation("获取 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算详细信息")
    @PreAuthorize("@ss.hasPermi('practice:AMarkSheet:query')")
    @GetMapping(value = "/{msId}")
    public AjaxResult getInfo(@PathVariable("msId") Long msId)
    {
        return success(aMarkSheetService.selectAMarkSheetByMsId(msId));
    }

    @GetMapping("/{msId}/{enumId}")
    public AjaxResult getAMarkSheetByMsIdAndEnumId(@PathVariable("msId") Integer msId,@PathVariable("enumId") Integer enumId)
    {
        return success(aMarkSheetService.selectAMarkSheetByMsIdAndEnumId(msId, enumId));
    }

    /**
     * 新增 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     */
    @ApiOperation("新增 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算")
    @PreAuthorize("@ss.hasPermi('practice:AMarkSheet:add')")
    @Log(title = " A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AMarkSheet aMarkSheet)
    {
        return toAjax(aMarkSheetService.insertAMarkSheet(aMarkSheet));
    }

    /**
     * 修改 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     */
    @ApiOperation("修改 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算")
    @PreAuthorize("@ss.hasPermi('practice:AMarkSheet:edit')")
    @Log(title = " A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AMarkSheet aMarkSheet)
    {
        return toAjax(aMarkSheetService.updateAMarkSheet(aMarkSheet));
    }

    /**
     * 删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     */
    @ApiOperation("删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算")
    @PreAuthorize("@ss.hasPermi('practice:AMarkSheet:remove')")
    @Log(title = " A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算", businessType = BusinessType.DELETE)
	@DeleteMapping("/{msIds}")
    public AjaxResult remove(@PathVariable Long[] msIds)
    {
        return toAjax(aMarkSheetService.deleteAMarkSheetByMsIds(msIds));
    }
}
