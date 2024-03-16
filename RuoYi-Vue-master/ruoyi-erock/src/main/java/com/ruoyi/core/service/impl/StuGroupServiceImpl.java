package com.ruoyi.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.mapper.StuGroupMapper;
import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.service.IStuGroupService;

/**
 * 小组Service业务层处理
 * 
 * @author heye
 * @date 2024-03-15
 */
@Service
public class StuGroupServiceImpl implements IStuGroupService 
{
    @Autowired
    private StuGroupMapper stuGroupMapper;

    /**
     * 查询小组
     * 
     * @param ggId 小组主键
     * @return 小组
     */
    @Override
    public StuGroup selectStuGroupByGgId(Long ggId)
    {
        return stuGroupMapper.selectStuGroupByGgId(ggId);
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
        stuGroupMapper.deleteStudentByStuId(stuGroup.getGgId());
        insertStudent(stuGroup);
        return stuGroupMapper.updateStuGroup(stuGroup);
    }

    /**
     * 批量删除小组
     * 
     * @param ggIds 需要删除的小组主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStuGroupByGgIds(Long[] ggIds)
    {
        stuGroupMapper.deleteStudentByStuIds(ggIds);
        return stuGroupMapper.deleteStuGroupByGgIds(ggIds);
    }

    /**
     * 删除小组信息
     * 
     * @param ggId 小组主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStuGroupByGgId(Long ggId)
    {
        stuGroupMapper.deleteStudentByStuId(ggId);
        return stuGroupMapper.deleteStuGroupByGgId(ggId);
    }

    /**
     * 新增学生信息
     * 
     * @param stuGroup 小组对象
     */
    public void insertStudent(StuGroup stuGroup)
    {
        List<Student> studentList = stuGroup.getStudentList();
        Long ggId = stuGroup.getGgId();
        if (StringUtils.isNotNull(studentList))
        {
            List<Student> list = new ArrayList<Student>();
            for (Student student : studentList)
            {
                student.setStuId(ggId);
                list.add(student);
            }
            if (list.size() > 0)
            {
                stuGroupMapper.batchStudent(list);
            }
        }
    }
}
