package com.ruoyi.core.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.domain.StuToTea;
import com.ruoyi.core.domain.bo.StudentModuleTotalBo;
import com.ruoyi.core.domain.dto.StudentDTO;
import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.core.domain.vo.StudentModuleScore;
import org.apache.ibatis.annotations.MapKey;
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
     * 根据班级和日期查询所有学生去重成绩信息
     * @param className  班级名
     * @param crDate  日期
     * @return 学生去重成绩信息
     */
    public List<StudentCourseGrades> selectDeduplicationStudentAchievementList(@Param("className") String className, @Param("crDate") String crDate);

    /**
     * 根据班级和日期查询所有学生成绩信息等
     * @param stuId  学生ID
     * @param crDate  日期
     * @return 所有学生成绩信息
     */
    public List<Map<String,Object>> selectStudentAchievementList(@Param("stuId") Long stuId, @Param("crDate") Date crDate, @Param("score") String score);

    /**
     * 根据学生ID查询去重日期
     * @param stuId 学生ID
     * @param enumId  枚举ID
     * @return 去重日期
     */
    public List<Map<String,Object>> selectDeduplicationCrDateByStuIdAndEnumIdList(@Param("stuId") Long stuId, @Param("enumId") Integer enumId,@Param("hour") String hour);

    /**
     *  根据学生ID查询当天的所有成绩次数
     * @param stuId 学生ID
     * @param enumId 枚举ID
     * @param crDate 时间
     * @return 当天的所有成绩次数
     */
    public List<Map<String,Object>> selectCrDateByStuIdAndEnumIdAndCrDateList(@Param("stuId") Long stuId, @Param("enumId") Integer enumId, @Param("crDate") Date crDate);

    /**
     * 根据学生ID查询成绩
     * @param stuId 学生ID
     * @return 学生的所有成绩
     */
    public List<Map<String,Object>> selectStudentAchievementByStuId(@Param("stuId") Long stuId, @Param("crDate") String crDate,@Param("score") String score);

    /**
     * 查询classId班所有学生进步分
     * @param classId 班级ID
     * @return
     */
    public List<StudentModuleScore> selectStudentProgressScoreList(@Param("classId") Integer classId);

    /**
     * 查询所有学生各模块期末成绩
     * @param studentDTO
     * @return
     */
    public List<StudentModuleTotalBo> selectStudentFinalGrade(StudentDTO studentDTO);

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
