package com.ruoyi.core.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.core.util.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.core.domain.StuToTea;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.IStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author heye
 * @date 2024-03-17
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生
     * 
     * @param stuId 学生主键
     * @return 学生
     */
    @Override
    public Student selectStudentByStuId(Long stuId)
    {
        return studentMapper.selectStudentByStuId(stuId);
    }

    /**
     * 查询学生列表
     * 
     * @param student 学生
     * @return 学生
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 根据班级和日期查询所有学生成绩信息等
     * @return 所有学生成绩信息等
     */
    public List<StudentCourseGrades> selectStudentCourseGradesList(String score) {
        List<StudentCourseGrades> gradesList;
        if (score.equals("ms_dribble")){
            gradesList = studentMapper.selectDeduplicationStudentAchievementList("北汽2301班", "2024-03-15 14:00:00");
        }else {
            gradesList = studentMapper.selectDeduplicationStudentAchievementList("北汽2301班", "2024-03-15 08:00:00");
        }
        List<StudentCourseGrades> newStudentCourseGradesList = new ArrayList<>();
        for (StudentCourseGrades studentCourseGrades : gradesList) {
            //这里添加查询时间，将学生信息+成绩信息查出来
            List<Map<String, Object>> students = studentMapper.selectStudentAchievementList(studentCourseGrades.getStuId(), studentCourseGrades.getCrDate(),score);
            Map<String, StudentCourseGrades> temp = new HashMap<>();
            for (Map<String, Object> studentMap : students) {
                Long stu_id = (Long) studentMap.get("stu_id");
                StudentCourseGrades studentCourseGrades1 = temp.get(String.valueOf(stu_id));
                if (studentCourseGrades1 == null) {
                    studentCourseGrades1 = new StudentCourseGrades();
                    List<BigDecimal> scores = new ArrayList<>();
                    scores.add(new BigDecimal(String.valueOf((BigDecimal) studentMap.get(score))));
                    studentCourseGrades1.setScore(scores);
                    //设置学生信息
                    studentCourseGrades1.setStuId(stu_id);
                    studentCourseGrades1.setStuName((String) studentMap.get("stu_name"));
                    studentCourseGrades1.setTeaName((String) studentMap.get("tea_name"));
                    studentCourseGrades1.setClassName((String) studentMap.get("class_name"));
                    studentCourseGrades1.setCrDate(DateUtil.LocalDateTimeConvertDate((LocalDateTime) studentMap.get("cr_date")));
                    if ((int)studentMap.get("enum_id")==15){
                        studentCourseGrades1.setCrMain("投篮");
                    }else {
                        studentCourseGrades1.setCrMain("运球");
                    }

                    //处理时间信息
                    String cr_date = DateUtil.getTime(DateUtil.LocalDateTimeConvertDate((LocalDateTime) studentMap.get("cr_date")));
                    studentCourseGrades1.setWhichClass(cr_date);
                    if (score.equals("ms_dribble")){
                        studentCourseGrades1.setTime("下午");
                    }else {
                        studentCourseGrades1.setTime("上午");
                    }


                    temp.put(String.valueOf(stu_id), studentCourseGrades1);
                    continue;
                }
                studentCourseGrades1.getScore().add((BigDecimal) studentMap.get(score));
            }

            StudentCourseGrades studentCourseGrades1 = temp.get(String.valueOf(studentCourseGrades.getStuId()));
            newStudentCourseGradesList.add(studentCourseGrades1);
        }
        return newStudentCourseGradesList;
    }

    /**
     *  根据学生ID查询当天的所有成绩次数
     * @param stuId 学生ID
     * @param enumId 枚举ID
     * @return
     */
    public List<Map<String,List>> selectDeduplicationCrDateByStuNameAndEnumIdList(Long stuId, Integer enumId, String hour){
        ArrayList<Map<String,List>> mapList = new ArrayList<>();
        Map<String, List> stringListMap = new HashMap<>();
        ArrayList<String> crDateList = new ArrayList<>();
        List<Integer> practiceFrequencyList = new ArrayList<>();
        ArrayList<String> whichClassList = new ArrayList<>();
        ArrayList<Integer> msIdList = new ArrayList<>();
        List<Map<String, Object>> maps = studentMapper.selectDeduplicationCrDateByStuIdAndEnumIdList(stuId, enumId, hour);
        for (Map<String, Object> map : maps){
            List<Map<String, Object>> cr_date = studentMapper.selectCrDateByStuIdAndEnumIdAndCrDateList(stuId, enumId, DateUtil.LocalDateTimeConvertDate((LocalDateTime) map.get("cr_date")));
            msIdList.add((Integer) cr_date.get(0).get("ms_id"));
            crDateList.add(DateUtil.DateConvertString(DateUtil.LocalDateTimeConvertDate((LocalDateTime) map.get("cr_date"))));
            practiceFrequencyList.add(cr_date.size());
            whichClassList.add((String) map.get("type"));
        }
        stringListMap.put("whichClass",whichClassList);
        stringListMap.put("xAxis",crDateList);
        stringListMap.put("series",practiceFrequencyList);
        stringListMap.put("msId",msIdList);
        mapList.add(stringListMap);
        return mapList;
    }

    /**
     * 根据学生ID查询成绩
     * @param stuId 学生ID
     * @return 学生的所有成绩
     */
    public List<List> selectStudentAchievementByStuId(Long stuId, String crDate,String score){
        ArrayList<List> list = new ArrayList<>();
        List<Map<String, Object>> maps = studentMapper.selectStudentAchievementByStuId(stuId,crDate,score);
        ArrayList<HashMap<String, Object>> hashMapArrayList = new ArrayList<>();
        for (Map<String, Object> map : maps){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ms_shooting",map.get(score));
            hashMap.put("ms_id",map.get("ms_id"));
            hashMapArrayList.add(hashMap);
            list.add(hashMapArrayList);
        }
        return list;
    }

    /**
     * 新增学生
     * 
     * @param student 学生
     * @return 结果
     */
    @Transactional
    @Override
    public int insertStudent(Student student)
    {
        int rows = studentMapper.insertStudent(student);
        insertStuToTea(student);
        return rows;
    }

    /**
     * 修改学生
     * 
     * @param student 学生
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStudent(Student student)
    {
        studentMapper.deleteStuToTeaByStuId(student.getStuId());
        insertStuToTea(student);
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生
     * 
     * @param stuIds 需要删除的学生主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStudentByStuIds(Long[] stuIds)
    {
        studentMapper.deleteStuToTeaByStuIds(stuIds);
        return studentMapper.deleteStudentByStuIds(stuIds);
    }

    /**
     * 删除学生信息
     * 
     * @param stuId 学生主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStudentByStuId(Long stuId)
    {
        studentMapper.deleteStuToTeaByStuId(stuId);
        return studentMapper.deleteStudentByStuId(stuId);
    }

    /**
     * 新增多个学生对多个老师信息
     * 
     * @param student 学生对象
     */
    public void insertStuToTea(Student student)
    {
        List<StuToTea> stuToTeaList = student.getStuToTeaList();
        Long stuId = student.getStuId();
        if (StringUtils.isNotNull(stuToTeaList))
        {
            List<StuToTea> list = new ArrayList<StuToTea>();
            for (StuToTea stuToTea : stuToTeaList)
            {
                stuToTea.setStuId(stuId);
                list.add(stuToTea);
            }
            if (list.size() > 0)
            {
                studentMapper.batchStuToTea(list);
            }
        }
    }
}
