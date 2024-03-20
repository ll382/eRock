package com.ruoyi.views.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.views.domain.StudentCourseGrades;
import com.ruoyi.views.mapper.StudentCourseGradesMapper;
import com.ruoyi.views.service.IStudentCourseGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生成绩查询Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-14
 */
@Service
public class StudentCourseGradesServiceImpl implements IStudentCourseGradesService
{
    @Autowired
    private StudentCourseGradesMapper studentCourseGradesMapper;

    /**
     * 查询学生成绩查询
     * 
     * @param stuId 学生成绩查询主键
     * @return 学生成绩查询
     */
    @Override
    public ArrayList<HashMap<String, List>> selectStudentCourseGradesByStuId(Long stuId)
    {
        List<StudentCourseGrades> grades = studentCourseGradesMapper.selectStudentCourseGradesByStuId(stuId);
        ArrayList<HashMap<String,List>>  list = new ArrayList<HashMap<String,List>>();
        //自己定义的前后端交互格式
        HashMap<String, List> xAxisHashMap = new HashMap<>();
        //crDateList里面存放日期
        ArrayList<String> crDateList = new ArrayList<>();

        //crDateList里面存放投篮分数
        ArrayList<BigDecimal> msShootingDate = new ArrayList<>();
        //crDateList里面存放运球分数
        ArrayList<BigDecimal> msDribbleDate = new ArrayList<>();

        //msShootingList里面存放投篮分数等
        ArrayList<JSONObject> msShootingList = new ArrayList<>();
        //msDribbleList里面存放运球分数等
        ArrayList<JSONObject> msDribbleList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (StudentCourseGrades courseGrades : grades){
            String format = sdf.format(courseGrades.getCrDate());
            crDateList.add(format);

            msShootingDate.add(courseGrades.getMsShooting());
            JSONObject msShootingJsonObject = new JSONObject();
            msShootingJsonObject.put("value",courseGrades.getMsShooting());
            msShootingJsonObject.put("xAxis",format);
            msShootingJsonObject.put("yAxis",courseGrades.getMsShooting());
            msShootingList.add(msShootingJsonObject);

            msDribbleDate.add(courseGrades.getMsDribble());
            JSONObject msDribbleJsonObject = new JSONObject();
            msDribbleJsonObject.put("value",courseGrades.getMsDribble());
            msDribbleJsonObject.put("xAxis",format);
            msDribbleJsonObject.put("yAxis",courseGrades.getMsDribble());
            msDribbleList.add(msDribbleJsonObject);
        }
        xAxisHashMap.put("xAxis", crDateList);
        xAxisHashMap.put("msShootingScore",msShootingDate);
        xAxisHashMap.put("msDribbleScore",msDribbleDate);
        xAxisHashMap.put("msShooting",msShootingList );
        xAxisHashMap.put("msDribble",msDribbleList);
        list.add(xAxisHashMap);
        return list;
    }

    /**
     * 查询学生成绩查询列表
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 学生成绩查询
     */
    @Override
    public List<StudentCourseGrades> selectStudentCourseGradesList(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.selectStudentCourseGradesList(studentCourseGrades);
    }

    /**
     * 新增学生成绩查询
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 结果
     */
    @Override
    public int insertStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.insertStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 修改学生成绩查询
     * 
     * @param studentCourseGrades 学生成绩查询
     * @return 结果
     */
    @Override
    public int updateStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.updateStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 批量删除学生成绩查询
     * 
     * @param stuIds 需要删除的学生成绩查询主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuIds(Long[] stuIds)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuIds(stuIds);
    }

    /**
     * 删除学生成绩查询信息
     * 
     * @param stuId 学生成绩查询主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuId(Long stuId)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuId(stuId);
    }
}
