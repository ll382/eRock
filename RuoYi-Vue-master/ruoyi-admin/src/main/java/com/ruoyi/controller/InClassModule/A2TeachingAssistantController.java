package com.ruoyi.controller.InClassModule;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.InClassModule.domain.A2StudentEvaluate;
import com.ruoyi.InClassModule.domain.A2TeachingAssistantEvaluation;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStuGroupService;
import com.ruoyi.core.service.IStudentService;
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

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStuGroupService stuGroupService;

    @Autowired
    private SelectUser selectUser;

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
     * 合作学习 助教评价小组列表
     */
    @ApiOperation("合作学习 助教评价小组列表")
    @PreAuthorize("@ss.hasPermi('practice:sheet:list')")
    @GetMapping("/list/group")
    public AjaxResult groupList(A2TeachingAssistant a2TeachingAssistant)
    {
        Student student = studentService.selectStudentByStuId(a2TeachingAssistant.getStuId());
        StuGroup stuGroup = stuGroupService.selectStuGroupByGgId(student.getGgId());


//        判断是否是组长
        if (student.getStuId().equals(stuGroup.getStuGroupLeader())){
//            通过小组查到所有本组学生
            List<Student> students = studentService.selectStudentList(new Student(student.getGgId()));
//            定义一个列表存储下面学生数据
            List<A2TeachingAssistant> list = new ArrayList<>();
            students.forEach(stu -> {
//                将传入的所有数据放到此处
                A2TeachingAssistant aMarkSheet1 = a2TeachingAssistant;
//                修改一下本组内的学生id
                aMarkSheet1.setStuId(stu.getStuId());
//                查询数据库是否有内容
                List<A2TeachingAssistant> assistants = a2TeachingAssistantService.selectA2TeachingAssistantList(aMarkSheet1);
//                有内容则加入数据列表中
                if (StringUtils.isNotEmpty(assistants)){
                    list.add(assistants.get(0));
                }
            });
//            发送数据
            return success(selectUser.selectUndoneGroup(list,student.getGgId()));
        }
        List<A2TeachingAssistant> list = a2TeachingAssistantService.selectA2TeachingAssistantList(a2TeachingAssistant);
        return success(list);
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
        return success(selectUser.selectUndoneStudent(a2TeachingAssistantService.selectA2TeachingAssistantByTaId(crId)));
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
        A2TeachingAssistant as = new A2TeachingAssistant();
//        判断评价学生以及评价学生者和评价课程是否相同
        as.setStuId(a2TeachingAssistant.getStuId());
        as.setTaStu(a2TeachingAssistant.getTaStu());
        as.setCrId(a2TeachingAssistant.getCrId());
//        相同直接返回警告
        if (StringUtils.isNotEmpty(a2TeachingAssistantService.selectA2TeachingAssistantList(as))){
            return AjaxResult.warn("你已经提交过了，请勿重复提交");
        }
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
