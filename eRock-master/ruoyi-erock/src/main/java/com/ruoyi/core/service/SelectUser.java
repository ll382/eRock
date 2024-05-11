package com.ruoyi.core.service;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.domain.Semester;
import com.ruoyi.knowledgeQuiz.domain.A1Task;
import com.ruoyi.score.domain.DModelScore;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
public interface SelectUser<T extends BaseEntity>  {
//    Service工具接口
    public List<T> selectStudent(List<T> stuList);
    public A1Task calculateScore(A1Task stuList);
    public T selectStudent(T student);
    public List<T> selectTeacher(List<T> teaList);
    public T selectTeacher(T teacher);
    public T selectInGroupStudent(T GroupStudent);
    public List<T> selectInGroupStudent(List<T> GroupStudent);

    public Long selectStudentStuId(Long userId);

    public Long selectTeacherTeaId(Long userId);

    public Semester selectDate(Date date);
//      插入D模块内容
    public Long judgeDModel(DModelScore dModelScore);
//      D模块分数计算
    public BigDecimal countDModel(Long modscId);
    public Long judgeInformation(TotalScore totalScore);

    public ModuleScore judgeModuleScore(ModuleScore moduleScore);

//    TODO: 用于增加或修改学生的A模块成绩

//    A1学习次数业务接口
    public Double A1calculationTimes(int number, int stuNumber);
//    A2学习评价业务接口
    public Double A2calculationTimes(int excellent, int ordinary);



    //    A模块通用处理方法      本接口用于修改学生A成绩
    public int updateStudentAScore(AModuleScore number, Long stuId);

    //    必须得要在学生类被赋予之前使用本接口，否则会有索引异常


//    Controller工具接口
    public HashMap<String, Object> selectFrequency(List<T> groupStudent,Long rtId);
    //    区别上面，下面是不分任务的查看学生提交资源次数
    public HashMap<String, Object> selectFrequency(List<T> groupStudent);
    public HashMap selectGroup(List<T> groupList);
    public HashMap selectUndoneStudent(List<T> stuList);
    public HashMap<String,Object> selectUndoneGroup(List<T> stuList,Long ggId);

}
