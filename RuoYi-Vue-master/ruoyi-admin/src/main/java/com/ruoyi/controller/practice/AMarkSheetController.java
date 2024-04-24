package com.ruoyi.controller.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStuGroupService;
import com.ruoyi.core.service.IStudentService;
import com.ruoyi.framework.web.domain.server.Sys;
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
 * @author ljy
 * @date 2024-04-11
 */
@Api(tags = {"练习、测试评分表"})
@RestController
@RequestMapping("/practice/sheet")
public class AMarkSheetController extends BaseController
{
    @Autowired
    private IAMarkSheetService aMarkSheetService;

    /**
     * 查询练习、测试评分表列表未完成
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
     * 获取未交学生名单
     */
    @ApiOperation("获取练习、测试评分表详细信息")
    @PreAuthorize("@ss.hasPermi('practice:sheet:query')")
    @GetMapping(value = "unsubmitted/{etId}")
    public AjaxResult selectAMarkSheetList(@PathVariable("etId") Long etId)
    {
        return success(aMarkSheetService.selectAMarkSheetList(etId));
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

    @GetMapping("/{msId}/{enumId}")
    public AjaxResult getAMarkSheetByMsIdAndEnumId(@PathVariable("msId") Integer msId,@PathVariable("enumId") Integer enumId)
    {
        return success(aMarkSheetService.selectAMarkSheetByMsIdAndEnumId(msId, enumId));
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
        int i = aMarkSheetService.insertAMarkSheet(aMarkSheet);
        if (i < 0) {
            return warn("本次任务已提交，请不要重复提交内容");
        }else if (i == 0) {
            return warn("本学期已结束，请等下次开学后提交");
        }
        return success(i);
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
