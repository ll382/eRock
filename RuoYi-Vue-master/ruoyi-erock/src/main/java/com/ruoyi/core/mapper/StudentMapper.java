package com.ruoyi.core.mapper;

import java.util.List;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.domain.StuToTea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 学生Mapper接口
 * 
 * @author heye
 * @date 2024-03-17
 */
@Mapper
public interface StudentMapper 
{
    /**
     * 查询学生
     * 
     * @param stuId 学生主键
     * @return 学生
     */
    public Student selectStudentByStuId(Long stuId);


    /**
     * 查询学生列表
     * 
     * @param student 学生
     * @return 学生集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 根据班级查询学生
     *
     * @param className 班级名
     * @return 学生集合
     */
    public List<String> selectClassIdList(@Param("className") String className);

    /**
     * 新增学生
     * 
     * @param student 学生
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生
     * 
     * @param student 学生
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 删除学生
     * 
     * @param stuId 学生主键
     * @return 结果
     */
    public int deleteStudentByStuId(Long stuId);

    /**
     * 批量删除学生
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByStuIds(Long[] stuIds);

    /**
     * 批量删除多个学生对多个老师
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStuToTeaByStuIds(Long[] stuIds);
    
    /**
     * 批量新增多个学生对多个老师
     * 
     * @param stuToTeaList 多个学生对多个老师列表
     * @return 结果
     */
    public int batchStuToTea(List<StuToTea> stuToTeaList);
    

    /**
     * 通过学生主键删除多个学生对多个老师信息
     * 
     * @param stuId 学生ID
     * @return 结果
     */
    public int deleteStuToTeaByStuId(Long stuId);
}
