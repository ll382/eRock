package com.ruoyi.core.service.impl;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.domain.vo.SelectUserVo;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.mapper.SelectUserMapper;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
@Service
public class SelectUserImpl implements SelectUser {
    @Autowired
    SelectUserMapper selectUserMapper;

//    @Override
//    public Student selectUser(Long stuId){
//        return selectUserMapper.selectUser(stuId);
//    }
    @Override
    public List<AMarkSheet> selectStudent(List<AMarkSheet> stuList){
        stuList.forEach(student -> {
                student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId())); // 将当前 student 设置到对应的 AMarkSheet 中
        });
        return stuList;
    }

    @Override
    public AMarkSheet selectStudent(AMarkSheet student) {
        student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId()));
        return student;
    }

    @Override
    public List<AExerciseTask> selectTeacher(List<AExerciseTask> teaList) {
        teaList.forEach(teacher -> {
            teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId())); // 将当前 student 设置到对应的 AMarkSheet 中
        });
        return teaList;
    }

    @Override
    public AExerciseTask selectTeacher(AExerciseTask teacher) {
        teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        return teacher;
    }
}
