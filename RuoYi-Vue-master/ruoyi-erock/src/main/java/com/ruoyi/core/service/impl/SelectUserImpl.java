package com.ruoyi.core.service.impl;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SelectUserVo;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.mapper.SelectUserMapper;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<T> selectStudent(List<T> stuList){
        stuList.forEach(student -> {
            student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId())); // 将当前 student 设置到对应的 AMarkSheet 中
        });
        return stuList;
    }
    @Override
    public T selectStudent(T student) {
        student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId()));
        return student;
    }

    @Override
    public List<T> selectTeacher(List<T> teaList) {
        teaList.forEach(teacher -> {
            teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        });
        return teaList;
    }

    @Override
    public T selectTeacher(T teacher) {
        teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        return teacher;
    }

    @Override
    public HashMap<String, Object> selectGroup(List<T> groupList) {

//        controller层数据定义
        HashMap<String, Object> map = new HashMap<>();
//        所有小组id集合
        List<Long> num = new ArrayList<>();
//        未交小组集合
        List<StuGroup> sgroups = new ArrayList<>();

        groupList.forEach(group -> {
            if (group.getGgId() != null)num.add(group.getGgId());
        });
//        不为空则查出本组对应未交小组
        if (!num.isEmpty()) sgroups = selectUserMapper.selectGroup(num);
        map.put("doneGroup",groupList);
        map.put("undoneGroup",sgroups);
        return map;
    }

    @Override
    public HashMap selectUndoneStudent(List<T> stuList) {
        //        controller层数据定义
        HashMap<String, Object> map = new HashMap<>();
//        所有小组id集合
        List<Long> num = new ArrayList<>();
//        未交小组集合
        List<SelectUserVo> undoneStu = new ArrayList<>();

        stuList.forEach(stu -> {
            if (stu.getStuId() != null)num.add(stu.getStuId());
        });
//        不为空则查出本组对应未交小组
        if (!num.isEmpty()) undoneStu = selectUserMapper.selectStudent(num);
        map.put("doneStudents",stuList);
        map.put("undoneStudents",undoneStu);
        return map;
    }


}
