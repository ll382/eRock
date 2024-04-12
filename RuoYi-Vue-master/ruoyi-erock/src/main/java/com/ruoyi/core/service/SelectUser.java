package com.ruoyi.core.service;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.core.domain.Student;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;

import java.util.List;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
public interface SelectUser<T extends BaseEntity>  {
//    public List<AMarkSheet> selectStudent(List<AMarkSheet> stuList);
//    public AMarkSheet selectStudent(AMarkSheet student);
//    public List<AExerciseTask> selectTeacher(List<AExerciseTask> teaList);
//    public AExerciseTask selectTeacher(AExerciseTask teacher);

    public List<T> selectStudent(List<T> stuList);
    public T selectStudent(T student);
    public List<T> selectTeacher(List<T> teaList);
    public T selectTeacher(T teacher);

    public List<T> selectStudent(List<T> stuList , Class<T> clazz);
    public T selectStudent(T student , Class<T> clazz);
    public List<T> selectTeacher(List<T> teaList , Class<T> clazz);
    //
    public T selectTeacher(T teacher , Class<T> clazz);

}
