package com.ruoyi.core.service;

import com.ruoyi.core.domain.Student;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;

import java.util.List;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
public interface SelectUser {
    public List<AMarkSheet> selectStudent(List<AMarkSheet> stuList);
    public AMarkSheet selectStudent(AMarkSheet student);
    public List<AExerciseTask> selectTeacher(List<AExerciseTask> teaList);
    public AExerciseTask selectTeacher(AExerciseTask teacher);
//    public List<Object> selectTeacher(List<Object> teaList);
//
//    public Object selectTeacher(Object teacher);
}
