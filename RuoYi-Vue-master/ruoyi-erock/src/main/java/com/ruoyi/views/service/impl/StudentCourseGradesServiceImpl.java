package com.ruoyi.views.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.views.mapper.StudentCourseGradesMapper;
import com.ruoyi.views.domain.StudentCourseGrades;
import com.ruoyi.views.service.IStudentCourseGradesService;

/**
 * 学生成绩视图Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-20
 */
@Service
public class StudentCourseGradesServiceImpl implements IStudentCourseGradesService 
{
    @Autowired
    private StudentCourseGradesMapper studentCourseGradesMapper;

    /**
     * 查询学生成绩视图
     * 
     * @param stuId 学生成绩视图主键
     * @return 学生成绩视图
     */
    @Override
    public ArrayList<HashMap<String, List>> selectStudentCourseGradesByStuId(Long stuId)
    {
        List<StudentCourseGrades> grades = studentCourseGradesMapper.selectStudentCourseGradesByStuId(stuId);
        ArrayList<HashMap<String,List>> list = new ArrayList<HashMap<String,List>>();
        //自己定义的前后端交互格式
        HashMap<String, List> xAxisHashMap = new HashMap<>();
        //crDateList里面存放日期
        ArrayList<String> crDateList = new ArrayList<>();

        //EROCKDate里面存放EROCK分数
        ArrayList<BigDecimal> EROCKDate = new ArrayList<>();


        //EROCKList里面存放EROCK分数等
        ArrayList<JSONObject> EROCKList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (StudentCourseGrades courseGrades : grades){
            String format = sdf.format(courseGrades.getCrDate());
            crDateList.add(format);

            EROCKDate.add(courseGrades.getMsScore());

            JSONObject msDribbleJsonObject = new JSONObject();
            msDribbleJsonObject.put("value",courseGrades.getMsScore());
            msDribbleJsonObject.put("xAxis",format);
            msDribbleJsonObject.put("yAxis",courseGrades.getMsScore());
            EROCKList.add(msDribbleJsonObject);
        }
        xAxisHashMap.put("xAxis", crDateList);
        xAxisHashMap.put("EROCKScore",EROCKDate);
        xAxisHashMap.put("EROCKList",EROCKList);
        list.add(xAxisHashMap);
        return list;
    }

    /**
     * 查询学生成绩视图列表
     * 
     * @param studentCourseGrades 学生成绩视图
     * @return 学生成绩视图
     */
    @Override
    public List<StudentCourseGrades> selectStudentCourseGradesList(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.selectStudentCourseGradesList(studentCourseGrades);
    }

    /**
     * 新增学生成绩视图
     * 
     * @param studentCourseGrades 学生成绩视图
     * @return 结果
     */
    @Override
    public int insertStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.insertStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 修改学生成绩视图
     * 
     * @param studentCourseGrades 学生成绩视图
     * @return 结果
     */
    @Override
    public int updateStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.updateStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 批量删除学生成绩视图
     * 
     * @param stuIds 需要删除的学生成绩视图主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuIds(Long[] stuIds)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuIds(stuIds);
    }

    /**
     * 删除学生成绩视图信息
     * 
     * @param stuId 学生成绩视图主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuId(Long stuId)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuId(stuId);
    }
}
