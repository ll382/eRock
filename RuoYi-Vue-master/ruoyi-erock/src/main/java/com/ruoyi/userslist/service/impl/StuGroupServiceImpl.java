package com.ruoyi.userslist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.userslist.domain.Student;
import com.ruoyi.userslist.mapper.StuGroupMapper;
import com.ruoyi.userslist.domain.StuGroup;
import com.ruoyi.userslist.service.IStuGroupService;

/**
 * 小组Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-13
 */
@Service
public class StuGroupServiceImpl implements IStuGroupService 
{
    @Autowired
    private StuGroupMapper stuGroupMapper;

    /**
     * 查询小组
     * 
     * @param gId 小组主键
     * @return 小组
     */
    @Override
    public StuGroup selectStuGroupByGId(Long gId)
    {
        return stuGroupMapper.selectStuGroupByGId(gId);
    }

    /**
     * 查询小组列表
     * 
     * @param stuGroup 小组
     * @return 小组
     */
    @Override
    public List<StuGroup> selectStuGroupList(StuGroup stuGroup)
    {
        return stuGroupMapper.selectStuGroupList(stuGroup);
    }

    /**
     * 新增小组
     * 
     * @param stuGroup 小组
     * @return 结果
     */
    @Transactional
    @Override
    public int insertStuGroup(StuGroup stuGroup)
    {
        int rows = stuGroupMapper.insertStuGroup(stuGroup);
        insertStudent(stuGroup);
        return rows;
    }

    /**
     * 修改小组
     * 
     * @param stuGroup 小组
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStuGroup(StuGroup stuGroup)
    {
        stuGroupMapper.deleteStudentByGId(stuGroup.getGId());
        insertStudent(stuGroup);
        return stuGroupMapper.updateStuGroup(stuGroup);
    }

    /**
     * 批量删除小组
     * 
     * @param gIds 需要删除的小组主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStuGroupByGIds(Long[] gIds)
    {
        stuGroupMapper.deleteStudentByGIds(gIds);
        return stuGroupMapper.deleteStuGroupByGIds(gIds);
    }

    /**
     * 删除小组信息
     * 
     * @param gId 小组主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStuGroupByGId(Long gId)
    {
        stuGroupMapper.deleteStudentByGId(gId);
        return stuGroupMapper.deleteStuGroupByGId(gId);
    }

    /**
     * 新增学生信息
     * 
     * @param stuGroup 小组对象
     */
    public void insertStudent(StuGroup stuGroup)
    {
        List<Student> studentList = stuGroup.getStudentList();
        Long gId = stuGroup.getGId();
        if (StringUtils.isNotNull(studentList))
        {
            List<Student> list = new ArrayList<Student>();
            for (Student student : studentList)
            {
                student.setGId(gId);
                list.add(student);
            }
            if (list.size() > 0)
            {
                stuGroupMapper.batchStudent(list);
            }
        }
    }
}
