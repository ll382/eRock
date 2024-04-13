package com.ruoyi.core.service;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.domain.Student;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;

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
//    Controller工具接口
    public HashMap selectGroup(List<T> groupList);
    public HashMap selectUndoneStudent(List<T> stuList);

}
