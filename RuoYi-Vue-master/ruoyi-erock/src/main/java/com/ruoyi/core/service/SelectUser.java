package com.ruoyi.core.service;

import com.ruoyi.common.core.domain.BaseEntity;

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
    public T selectStudent(T student);
    public List<T> selectTeacher(List<T> teaList);
    public T selectTeacher(T teacher);
    public T selectInGroupStudent(T GroupStudent);
    public List<T> selectInGroupStudent(List<T> GroupStudent);

    public Long selectStudentStuId(Long userId);

    public Long selectTeacherTeaId(Long userId);


    //    必须得要在学生类被赋予之前使用本接口，否则会有索引异常


//    Controller工具接口
    public HashMap<String, Object> selectFrequency(List<T> groupStudent,Long rtId);
    //    区别上面，下面是不分任务的查看学生提交资源次数
    public HashMap<String, Object> selectFrequency(List<T> groupStudent);
    public HashMap selectGroup(List<T> groupList);
    public HashMap selectUndoneStudent(List<T> stuList);

}
