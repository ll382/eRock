package com.ruoyi.controller.InClassModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.InClassModule.domain.A2TeachingAssistant;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStuGroupService;
import com.ruoyi.core.service.IStudentService;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.practice.domain.AMarkSheet;
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
import com.ruoyi.InClassModule.domain.A2StudentEvaluate;
import com.ruoyi.InClassModule.service.IA2StudentEvaluateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * A2 合作学习 学生互评Controller
 *
 * @author heye
 * @date 2024-04-11
 */
@Api(tags = {"A2 合作学习 学生互评"})
@RestController
@RequestMapping("/peerRevision/evaluate")
public class A2StudentEvaluateController extends BaseController
{
    @Autowired
    private IA2StudentEvaluateService a2StudentEvaluateService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStuGroupService stuGroupService;

    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 合作学习 学生互评列表
     */
    @ApiOperation("查询A2 合作学习 学生互评列表")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(A2StudentEvaluate a2StudentEvaluate)
    {
        startPage();
        List<A2StudentEvaluate> list = a2StudentEvaluateService.selectA2StudentEvaluateList(a2StudentEvaluate);
        return getDataTable(list);
    }

    /**
     * 查询练习、测试评分表列表未完成
     */
    @ApiOperation("查询小组互评评分表列表")
    @PreAuthorize("@ss.hasPermi('practice:sheet:list')")
    @GetMapping("/list/group")
    public AjaxResult groupList(A2StudentEvaluate a2StudentEvaluate)
    {
        Student student = studentService.selectStudentByStuId(a2StudentEvaluate.getEsId());
//            通过小组查到所有本组学生
        List<Student> students = studentService.selectStudentList(new Student(student.getGgId()));
//            定义一个列表存储下面学生数据
        List<A2StudentEvaluate> list = new ArrayList<>();
//        将本组学生循环
        students.forEach(stu -> {
//            查询每个学生是否有值
            a2StudentEvaluate.setStuId(stu.getStuId());
            List<A2StudentEvaluate> list1 = a2StudentEvaluateService.selectA2StudentEvaluateList(a2StudentEvaluate);
            if (!StringUtils.isEmpty(list1)){
                list.add(list1.get(0));
            }
        });
        A2StudentEvaluate evaluate = new A2StudentEvaluate(a2StudentEvaluate.getEsId());
//        设置默认排除其本人
        list.add((A2StudentEvaluate) selectUser.selectStudent(evaluate));
        HashMap hashMap = selectUser.selectUndoneGroup(list, student.getGgId());
        hashMap.put("group",selectUser.selectInGroupStudent(student).getGroup());

//            发送数据
        return success(hashMap);
    }

    /**
     * 导出A2 合作学习 学生互评列表
     */
    @ApiOperation("导出A2 合作学习 学生互评列表")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:export')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, A2StudentEvaluate a2StudentEvaluate)
    {
        List<A2StudentEvaluate> list = a2StudentEvaluateService.selectA2StudentEvaluateList(a2StudentEvaluate);
        ExcelUtil<A2StudentEvaluate> util = new ExcelUtil<A2StudentEvaluate>(A2StudentEvaluate.class);
        util.exportExcel(response, list, "A2 合作学习 学生互评数据");
    }

    /**
     * 获取A2 合作学习 学生互评详细信息
     */
    @ApiOperation("获取A2 合作学习 学生互评详细信息")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:query')")
    @GetMapping(value = "/{crId}")
    public AjaxResult getInfo(@PathVariable("crId") Long crId)
    {
        startPage();
        return success(selectUser.selectUndoneStudent(a2StudentEvaluateService.selectA2StudentEvaluateBySeId(crId)));
    }

    /**
     * 新增A2 合作学习 学生互评
     */
    @ApiOperation("新增A2 合作学习 学生互评")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:add')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody A2StudentEvaluate a2StudentEvaluate)
    {
        int evaluate = a2StudentEvaluateService.insertA2StudentEvaluate(a2StudentEvaluate);
        if(evaluate == 0){
            return AjaxResult.warn("你已经提交过了，请勿重复提交");
        }
//        不相同直接插入
        return toAjax(evaluate);
    }

    /**
     * 修改A2 合作学习 学生互评
     */
    @ApiOperation("修改A2 合作学习 学生互评")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:edit')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody A2StudentEvaluate a2StudentEvaluate)
    {
        int u = a2StudentEvaluateService.updateA2StudentEvaluate(a2StudentEvaluate);
        if(u == 0){
            return AjaxResult.warn("你已经提交过了，请勿重复提交");
        }
        return toAjax(u);
    }

    /**
     * 删除A2 合作学习 学生互评
     */
    @ApiOperation("删除A2 合作学习 学生互评")
    @PreAuthorize("@ss.hasPermi('peerRevision:evaluate:remove')")
    @Log(title = "A2 合作学习 学生互评", businessType = BusinessType.DELETE)
    @DeleteMapping("/{seIds}")
    public AjaxResult remove(@PathVariable Long[] seIds)
    {
        return toAjax(a2StudentEvaluateService.deleteA2StudentEvaluateBySeIds(seIds));
    }
}
