package com.ruoyi.core.service.impl;

import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.mapper.SelectUserMapper;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//    }

//    @Override
//    public List<Object> selectTeacher(List<Object> teaList) {
//        teaList.forEach(teacher -> {
//            try {
//                Method[] methods = teacher.getClass().getMethods();
//                for (Method method : methods) {
//                    if (method.getName().equals("getTeaId")) {
//                        Object teaId = method.invoke(teacher);
//                        Object teacherObject = selectUserMapper.selectTeacherbyOne((Long) teaId); // 假设返回的是 String 类型的教师ID
//                        for (Method setMethod : methods) {
//                            if (setMethod.getName().equals("setTeacher")) {
//                                setMethod.invoke(teacher, teacherObject);
//                                break;
//                            }
//                        }
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        return teaList;
//    }

//    public Object selectTeacher(Object object) {
//        try {
//            Method[] methods = object.getClass().getMethods();
//            for (Method method : methods) {
//                if (method.getName().equals("getTeaId")) {
//                    Object teaId = method.invoke(object);
//                    Object teacherObject = selectUserMapper.selectTeacherbyOne((Long) teaId); // 假设返回的是 String 类型的教师ID
//                    for (Method setMethod : methods) {
//                        if (setMethod.getName().equals("setTeacher")) {
//                            setMethod.invoke(object, teacherObject);
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return object;
    }


    @Override
    public AExerciseTask selectTeacher(AExerciseTask teacher) {
        teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        return teacher;
    }
}
