package com.ruoyi.core.service.impl;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SelectUserVo;
import com.ruoyi.common.core.domain.entity.Group;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.mapper.SelectUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
@Service
public class SelectUserImpl<T extends BaseEntity> implements SelectUser<T> {
    @Autowired
    SelectUserMapper selectUserMapper;

    @Autowired
    SelectUser selectUser;

    @Override
    public List<T> selectStudent(List<T> stuList){
        stuList.forEach(student -> {
            student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId()));
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
    public T selectInGroupStudent(T GroupStudent) {
        List<SelectUserVo> list = selectUserMapper.selectInGroupStudent(GroupStudent.getGgId());
        list.forEach(gro -> {
            if (gro.getId() != null)
                if (gro.getId().equals(GroupStudent.getGroup().getStuGroupLeader())) {
                    gro.setRole("组长");
                } else {
                    gro.setRole("组员");
                }
        });
        GroupStudent.getGroup().setStudentList(list);
        return GroupStudent;
    }

    @Override
    public List<T> selectInGroupStudent(List<T> GroupStudent) {
        GroupStudent.forEach(group -> {
            // 查询已交小组名称
            group.setGroup(selectUserMapper.selectGroupbyOne(group.getGgId()));
            // 查询小组内学生
            List<SelectUserVo> list = selectUserMapper.selectInGroupStudent(group.getGgId());
//            循环查到组长
            list.forEach(gro -> {
                if (gro.getId() != null)
                    if (gro.getId().equals(group.getGroup().getStuGroupLeader())) {
                        gro.setRole("组长");
                    } else {
                        gro.setRole("组员");
                    }
            });
            group.getGroup().setStudentList(list);
        });
        return GroupStudent;
    }



    @Override
    public HashMap<String, Object> selectGroup(List<T> groupList) {

//        controller层数据定义
        HashMap<String, Object> map = new HashMap<>();
//        所有小组id集合
        List<Long> num = new ArrayList<>();
//        未交小组集合
        List<Group> sgroups;
//      查询已交小组ID
        groupList.forEach(group -> {
            if (group.getGgId() != null)num.add(group.getGgId());
        });
//        不为空则查出本组对应未交小组
        sgroups = selectUserMapper.selectGroup(num);
        sgroups.forEach(gstu -> {
            gstu.setStudentList(selectUserMapper.selectInGroupStudent(gstu.getGgId()));
            gstu.getStudentList().forEach(gro -> {
                if (gro.getId() != null)
                    if (gro.getId().equals(gstu.getStuGroupLeader())) {
                        gro.setRole("组长");
                    } else {
                        gro.setRole("组员");
                    }
            });
        });
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
        undoneStu = selectUserMapper.selectStudent(num);
        map.put("doneStudents",stuList);
        map.put("undoneStudents",undoneStu);
        return map;
    }


}
