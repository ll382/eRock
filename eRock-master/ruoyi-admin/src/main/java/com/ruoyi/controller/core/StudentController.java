package com.ruoyi.controller.core;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.core.domain.bo.StudentModuleTotalBo;
import com.ruoyi.core.domain.dto.StudentDTO;
import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.core.domain.vo.StudentModuleScore;
import com.ruoyi.core.domain.vo.StudentModuleTotalScoreAndRankingVo;
import com.ruoyi.core.domain.vo.StudentModuleTotalScoreVo;
import com.ruoyi.teachingExchange.domain.TeachingTable;
import com.ruoyi.teachingExchange.service.ITeachingTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生Controller
 *
 * @author heye
 * @date 2024-03-17
 */
@Api("核心模块学生")
@RestController
@RequestMapping("/core/student")
public class StudentController extends BaseController
{
    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeachingTableService teachingTableService;

    /**
     * 查询A1 线上学习学生线上观看记录表列表
     */
    @ApiOperation("插入学生表内容")
    @PreAuthorize("@ss.hasPermi('teachingExchange:teachingExchange:list')")
    @GetMapping("/chageId")
    public AjaxResult list()
    {
        return AjaxResult.success(teachingTableService.updateStudent());
    }


    /**
     * 查询学生列表
     */
    @ApiOperation("查询学生列表")
    @PreAuthorize("@ss.hasPermi('core:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 查询classId班所有学生进步分
     * @param classId 班级ID
     * @return
     */
    @GetMapping("/getStudentProgressScoreList/{classId}")
    public TableDataInfo getStudentProgressScoreList(@PathVariable("classId") Integer classId){
        List<StudentModuleScore> list = studentService.selectStudentProgressScoreList(classId);
        return getDataTable(list);
    }

    /**
     * 查询所有学生各模块期末成绩
     * @param studentDTO
     * @return
     */
    @GetMapping("/getStudentFinalGrade")
    public TableDataInfo getStudentFinalGrade(StudentDTO studentDTO){
        List<StudentModuleTotalScoreAndRankingVo> list =studentService.selectStudentFinalGrade(studentDTO);
        return getDataTable(list);

    }

    /**
     * PC端首页
     * 根据班级和日期查询所有学生成绩信息等
     */
    @ApiOperation("根据班级和日期查询所有学生成绩信息等")
    @GetMapping("/getStudentCourseGradeslist/{score}")
    public TableDataInfo getStudentCourseGradesList(@PathVariable("score") String score)
    {
        startPage();
        List<StudentCourseGrades> studentCourseGradesList = studentService.selectStudentCourseGradesList(score);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(studentCourseGradesList);
        rspData.setTotal(40);
        return rspData;
    }

    /**
     * PC端二页面柱状图
     * 根据学生ID查询当天的所有成绩次数
     */
    @ApiOperation("根据班级和日期查询所有学生成绩信息等")
    @GetMapping("/getDeduplicationCrDateList/{stuId}/{enumId}/{hour}")
    public TableDataInfo getDeduplicationCrDateList(@PathVariable("stuId") Long stuId, @PathVariable("enumId") Integer enumId,@PathVariable("hour") String hour){
        startPage();
        List<Map<String, List>> maps = studentService.selectDeduplicationCrDateByStuNameAndEnumIdList(stuId, enumId, hour);
        return getDataTable(maps);
    }

    /**
     * PC端二页面折线图
     * @param stuId 学生ID
     * @param crDate 上课日期
     * @param score 投篮或运球字段
     * @return
     */
    @ApiOperation("根据学生ID查询成绩")
    @GetMapping("/getStudentAchievementByStuId/{stuId}/{crDate}/{score}")
    public TableDataInfo getStudentAchievementByStuId(@PathVariable("stuId") Long stuId,@PathVariable("crDate")String crDate, @PathVariable("score")String score){
        List<List> list = studentService.selectStudentAchievementByStuId(stuId,crDate,score);
        return getDataTable(list);
    }

    /**
     * 导出学生列表
     */
    @ApiOperation("导出学生列表")
    @PreAuthorize("@ss.hasPermi('core:student:export')")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Student student)
    {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        util.exportExcel(response, list, "学生数据");
    }

    /**
     * 获取学生详细信息
     */
    @ApiOperation("获取学生详细信息")
    @PreAuthorize("@ss.hasPermi('core:student:query')")
    @GetMapping(value = "/{stuId}")
    public AjaxResult getInfo(@PathVariable("stuId") Long stuId)
    {
        return success(studentService.selectStudentByStuId(stuId));
    }

    /**
     * 新增学生
     */
    @ApiOperation("新增学生")
    @PreAuthorize("@ss.hasPermi('core:student:add')")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Student student)
    {
        return toAjax(studentService.insertStudent(student));
    }

    /**
     * 修改学生
     */
    @ApiOperation("修改学生")
    @PreAuthorize("@ss.hasPermi('core:student:edit')")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Student student)
    {
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生
     */
    @ApiOperation("删除学生")
    @PreAuthorize("@ss.hasPermi('core:student:remove')")
    @Log(title = "学生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuIds}")
    public AjaxResult remove(@PathVariable Long[] stuIds)
    {
        return toAjax(studentService.deleteStudentByStuIds(stuIds));
    }
}