package com.ruoyi.visualization.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.visualization.mapper.StudentCourseGradesMapper;
import com.ruoyi.visualization.domain.StudentCourseGrades;
import com.ruoyi.visualization.service.IStudentCourseGradesService;

/**
 * PC段首页Service业务层处理
 * 
 * @author ljy
 * @date 2024-03-22
 */
@Service
public class StudentCourseGradesServiceImpl implements IStudentCourseGradesService 
{
    @Autowired
    private StudentCourseGradesMapper studentCourseGradesMapper;

    //学生Mapper接口
    @Autowired
    private StudentMapper studentMapper;
    /**
     * 查询PC段首页
     * 
     * @param stuName PC段首页主键
     * @return PC段首页
     */
    @Override
    public StudentCourseGrades selectStudentCourseGradesByStuName(String stuName)
    {
        return studentCourseGradesMapper.selectStudentCourseGradesByStuName(stuName);
    }

    /**
     * 查询PC段首页列表
     * 
     * @param studentCourseGrades PC段首页
     * @return PC段首页
     */
    @Override
    public List<StudentCourseGrades> selectStudentCourseGradesList()
    {
        List<StudentCourseGrades> newGradesList = new ArrayList<>();
        //存放日期+时间
        List<String> crDate = new ArrayList<>();
        crDate.add("2024-03-15 08:00:00");
        crDate.add("2024-03-15 10:00:00");
        crDate.add("2024-03-15 14:00:00");
        crDate.add("2024-03-15 16:00:00");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StudentCourseGrades studentCourseGrades = new StudentCourseGrades();

        //根据班级查询学生
        List<String> students = studentMapper.selectClassIdList("北汽2301班");
        for (String student :students) {
            for (String crDates : crDate) {
                //存放一个学生一节课的分数
                List<BigDecimal> Score = new ArrayList<>();
                studentCourseGrades.setStuName(student);
                try {
                    studentCourseGrades.setCrDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(crDates));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<StudentCourseGrades> gradesList = studentCourseGradesMapper.selectStudentCourseGradesList(studentCourseGrades);
                for (StudentCourseGrades gradesLists : gradesList) {
                    Score.add(gradesLists.getMsShooting());
                }
                StudentCourseGrades studentCourseGrades1 = gradesList.get(0);
                String format = sdf.format(studentCourseGrades1.getCrDate());

                if (format.equals("2024-03-15 08:00:00")) {
                    studentCourseGrades1.setWhichClass("一到二节课");
                    studentCourseGrades1.setTime("上午");
                    studentCourseGrades1.setScore(Score);
                    newGradesList.add(studentCourseGrades1);}
//                } else if (format.equals("2024-03-15 10:00:00")) {
//                    studentCourseGrades1.setWhichClass("三到四节课");
//                    studentCourseGrades1.setTime("上午");
//                    studentCourseGrades1.setScore(Score);
//                    newGradesList.add(studentCourseGrades1);
//                } else if (format.equals("2024-03-15 14:00:00")) {
//                    studentCourseGrades1.setWhichClass("五到六节课");
//                    studentCourseGrades1.setTime("下午");
//                    studentCourseGrades1.setScore(Score);
//                    newGradesList.add(studentCourseGrades1);
//                } else {
//                    studentCourseGrades1.setWhichClass("七到八节课");
//                    studentCourseGrades1.setTime("下午");
//                    studentCourseGrades1.setScore(Score);
//                    newGradesList.add(studentCourseGrades1);
//                }
            }
        }

        return newGradesList;
    }

    /**
     * 新增PC段首页
     * 
     * @param studentCourseGrades PC段首页
     * @return 结果
     */
    @Override
    public int insertStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.insertStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 修改PC段首页
     * 
     * @param studentCourseGrades PC段首页
     * @return 结果
     */
    @Override
    public int updateStudentCourseGrades(StudentCourseGrades studentCourseGrades)
    {
        return studentCourseGradesMapper.updateStudentCourseGrades(studentCourseGrades);
    }

    /**
     * 批量删除PC段首页
     * 
     * @param stuNames 需要删除的PC段首页主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuNames(String[] stuNames)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuNames(stuNames);
    }

    /**
     * 删除PC段首页信息
     * 
     * @param stuName PC段首页主键
     * @return 结果
     */
    @Override
    public int deleteStudentCourseGradesByStuName(String stuName)
    {
        return studentCourseGradesMapper.deleteStudentCourseGradesByStuName(stuName);
    }
}
