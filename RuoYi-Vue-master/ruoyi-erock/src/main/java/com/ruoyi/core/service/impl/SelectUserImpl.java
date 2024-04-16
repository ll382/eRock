package com.ruoyi.core.service.impl;

import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SelectUserVo;
import com.ruoyi.common.core.domain.entity.Group;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.mapper.SelectUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
@Service
public class SelectUserImpl<T extends BaseEntity> implements SelectUser<T> {
    @Autowired
    SelectUserMapper    selectUserMapper;

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
    public List<T> selectTeacher(List<T> teachers) {
        teachers.forEach(teacher -> {
            teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        });
        return teachers;
    }

    @Override
    public T selectTeacher(T teacher) {
            teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        return teacher;
    }

    @Override
    public T selectInGroupStudent(T GroupStudent) {
        // 查询组内成员，并判断是否是组长
        this.setGroup(GroupStudent);

        return GroupStudent;
    }

    @Override
    public List<T> selectInGroupStudent(List<T> GroupStudent ) {
        GroupStudent.forEach(group -> {
            // 查询已交小组名称
            group.setGroup(selectUserMapper.selectGroupbyOne(group.getGgId()));
            // 查询组内成员，并判断是否是组长
            this.setGroup(group);
        });
        return GroupStudent;
    }

//    查清已做未
    @Override
    public HashMap<String, Object> selectFrequency(List<T> users , Long phtrId){
//        返回类型
        HashMap<String, Object> map = new HashMap<>();
//        查学生id
        List<Long> vo = new ArrayList<>();
        users.forEach(user -> {
            vo.add(user.getStuId());
        });
//        通过学生id获取对应的没做的名单  users数据为空的后果就是全查
//        为空不查
        List<SelectUserVo> doneStudents = selectUserMapper.selectStudents(vo);
//        为空全查
        List<SelectUserVo> undoneStudents = selectUserMapper.selectUndoneStudents(vo);
//        通过名单查出所有做了和没做的学生列表
        List<SelectUserVo> doneStudentslist = selectUserMapper.selectUserFrequencyByRtId(doneStudents,phtrId);
        List<SelectUserVo> undoneStudentslist = selectUserMapper.selectUserFrequencyByRtId(undoneStudents,phtrId);
//        放到返回类型中
        A3PhysicalTraining a3PhysicalTraining = selectUserMapper.selectStudentFrequency(phtrId);
        a3PhysicalTraining.setTeacher(selectUserMapper.selectTeacherbyOne(a3PhysicalTraining.getTeaId()));

        map.put("teaTask",a3PhysicalTraining);
        map.put("phtrId",String.valueOf(phtrId));
        map.put("doneStudents",doneStudentslist);
        map.put("undoneStudents",undoneStudentslist);
        return map;
    }
    @Override
    public HashMap<String, Object> selectFrequency(List<T> users){
//        返回类型
        HashMap<String, Object> map = new HashMap<>();
//        查学生id
        List<Long> vo = new ArrayList<>();
        users.forEach(user -> {
            vo.add(user.getStuId());
        });
//        通过学生id获取对应的做了和没做的名单
        List<SelectUserVo> undoneStudents = selectUserMapper.selectUndoneStudents(vo);
        List<SelectUserVo> doneStudents = selectUserMapper.selectStudents(vo);
//        通过对应做了和没做的名单引申出它的次数
        List<SelectUserVo> undoneStudentslist = undoneStudents;
        List<SelectUserVo> doneStudentslist = selectUserMapper.selectUserFrequency(doneStudents);
        if (undoneStudents.size() > 0) {
            undoneStudentslist = selectUserMapper.selectUserFrequency(undoneStudents);
        }
//        放到返回类型中
        map.put("doneStudents",doneStudentslist);
        map.put("undoneStudents",undoneStudentslist);

        return map;
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
//        相信后人的智慧
        sgroups.forEach(gstu -> {
            gstu.setStudentList(selectUserMapper.selectInGroupStudent(gstu.getGgId()));
            gstu.getStudentList().forEach(gro -> {
                if (gro.getId() != null)
                    if (gro.getId().equals(gstu.getStuGroupLeader())) {
                        gro.setRole("组长");
                        gstu.setStuGroupLeaderName(gro.getName());
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
        List<SelectUserVo> undoneStu;

        stuList.forEach(stu -> {
            if (stu.getStuId() != null)num.add(stu.getStuId());
        });
//        不为空则查出本组对应未交小组
        undoneStu = selectUserMapper.selectUndoneStudents(num);
        map.put("doneStudents",stuList);
        map.put("undoneStudents",undoneStu);
        return map;
    }

//    小组学生查询工具方法 要求，有ggid的Entity继承类
    private void setGroup(BaseEntity GroupStudent) {
        List<SelectUserVo> list = selectUserMapper.selectInGroupStudent(GroupStudent.getGgId());
//        遍历小组成员
        list.forEach(gro -> {
//            如果学生id不为空的话，判断小组id是否是组长id
            if (gro.getId() != null)
                if (gro.getId().equals(GroupStudent.getGroup().getStuGroupLeader())) {
//                    是则给其赋值组长
                    gro.setRole("组长");
//                    给最外层赋值组长名
                    GroupStudent.getGroup().setStuGroupLeaderName(gro.getName());
                } else {
//                    否则给其赋值组员
                    gro.setRole("组员");
                }
        });
//        将组员组长以及外层的小组赋值给传入数据
        GroupStudent.getGroup().setStudentList(list);
    }




}
