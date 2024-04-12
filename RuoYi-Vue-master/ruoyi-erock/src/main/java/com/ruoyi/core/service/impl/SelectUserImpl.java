package com.ruoyi.core.service.impl;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.mapper.SelectUserMapper;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
@Service
public class SelectUserImpl<T extends BaseEntity> implements SelectUser<T> {
    @Autowired
    SelectUserMapper selectUserMapper;

    @Override
    public List<T> selectStudent(List<T> stuList, Class<T> clazz) {
        stuList.forEach(student -> {
            T castedStudent = clazz.cast(student); // 进行类型转换
            castedStudent.setStudent(selectUserMapper.selectStudentbyOne(castedStudent.getStuId())); // 将当前 student 设置到对应的 AMarkSheet 中
        });
        return stuList;
    }

    @Override
    public T selectStudent(T student, Class<T> clazz) {
        T castedStudent = clazz.cast(student); // 进行类型转换
        castedStudent.setStudent(selectUserMapper.selectStudentbyOne(castedStudent.getStuId()));
        return castedStudent;
    }

    @Override
    public List<T> selectTeacher(List<T> teaList, Class<T> clazz) {
        teaList.forEach(teacher -> {
            T castedTeacher = clazz.cast(teacher); // 进行类型转换
            castedTeacher.setTeacher(selectUserMapper.selectTeacherbyOne(castedTeacher.getTeaId())); // 将当前 teacher 设置到对应的 AMarkSheet 中
        });
        return teaList;
    }

    @Override
    public T selectTeacher(T teacher, Class<T> clazz) {
        T castedTeacher = clazz.cast(teacher); // 进行类型转换
        castedTeacher.setTeacher(selectUserMapper.selectTeacherbyOne(castedTeacher.getTeaId()));
        return castedTeacher;
    }

//    旧版本代码

    @Override
    public List<T> selectStudent(List<T> stuList){
        stuList.forEach(student -> {
            student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId())); // 将当前 student 设置到对应的 AMarkSheet 中
        });
        return stuList;
    }
    @Override
    public BaseEntity selectStudent(BaseEntity student) {
        student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId()));
        return student;
    }

    @Override
    public List<T> selectTeacher(List<T> teaList) {
        teaList.forEach(teacher -> {
            teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId())); // 将当前 student 设置到对应的 AMarkSheet 中
        });
        return teaList;
    }

    @Override
    public BaseEntity selectTeacher(BaseEntity teacher) {
        teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        return teacher;
    }


}
