package com.ruoyi.practice.mapper;

import java.util.List;

import com.ruoyi.core.domain.Student;
import com.ruoyi.match.domain.ClassRegister;
import com.ruoyi.practice.domain.ABallExam;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.domain.AExerciseResource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 练习、测试评分表Mapper接口
 *
 * @author houq
 * @date 2024-04-09
 */
public interface AMarkSheetMapper
{
    /**
     * 查询练习、测试评分表
     *
     * @param msId 练习、测试评分表主键
     * @return 练习、测试评分表
     */
    public AMarkSheet selectAMarkSheetByMsId(Long msId);

    /**
     * 查询练习、测试评分表列表
     *
     * @param aMarkSheet 练习、测试评分表
     * @return 练习、测试评分表集合
     */
    public List<AMarkSheet> selectAMarkSheetList(AMarkSheet aMarkSheet);

    @Select("SELECT ms.ms_score as msScore FROM class_register as cr JOIN a_exercise_task as et on cr.cr_id=et.cr_id JOIN a_mark_sheet as ms on et.et_id=ms.et_id WHERE cr.enum_id=#{enumId} AND et.enum_id=2 AND ms.stu_id = #{stuId} AND cr.semester_id=#{} ORDER BY ms.ms_time ASC;")
    public List<Long> selectMinMsScore(@Param("enumId") Long enumId,@Param("stuId") Long stuId);

    @Select("SELECT ms.ms_score as msScore FROM class_register as cr JOIN a_exercise_task as et on cr.cr_id=et.cr_id JOIN a_mark_sheet as ms on et.et_id=ms.et_id WHERE cr.enum_id=#{enumId} AND et.enum_id=2 AND ms.stu_id = #{stuId} ORDER BY ms.ms_time ASC;")
    public List<ClassRegister> selectClassRegisters(@Param("enumId") Long enumId, @Param("stuId") Long stuId);

    /**
     * 查询未提交名单
     *
     * @param aMarkSheet 练习、测试评分表
     * @return 练习、测试评分表集合
     */
    @Select("Select s.stu_id as stuId, e.avatar as stuImg,s.stu_name as stuName from student s left JOIN sys_user e ON e.user_id = s.user_id where s.stu_id NOT IN (SELECT stu_id FROM a_mark_sheet WHERE et_id = #{etId})")
    public List<Student> selectAMarkSheet(Long etId);

    /**
     * 新增练习、测试评分表
     *
     * @param aMarkSheet 练习、测试评分表
     * @return 结果
     */
    @Options(useGeneratedKeys = true, keyProperty = "msId")
    public int insertAMarkSheet(AMarkSheet aMarkSheet);

    @Select("SELECT cr.enum_id FROM a_mark_sheet ms LEFT JOIN a_exercise_task et ON ms.et_id = et.et_id LEFT JOIN class_register cr ON et.cr_id = cr.cr_id WHERE ms_id = #{aMarkSheet}")
    public String selectCriteria(Long aMarkSheet);

    /**
     * 修改练习、测试评分表
     *
     * @param aMarkSheet 练习、测试评分表
     * @return 结果
     */
    public int updateAMarkSheet(AMarkSheet aMarkSheet);

    @Insert(" INSERT INTO `a_ball_exam` (`ms_id`, `dri_stability`, `dri_power`, `dri_speed`, `dri_analysis`, `sho_arc`, `sho_spinner`, `sho_angle`, `sho_analysis`) VALUES (#{msId}, #{driStability}, #{driPower}, #{driSpeed}, #{driAnalysis}, #{shoArc}, #{shoSpinner}, #{shoAngle}, #{shoAnalysis})")
    public int insertBallTeam(ABallExam aBallExam);

    /**
     * 删除练习、测试评分表
     *
     * @param msId 练习、测试评分表主键
     * @return 结果
     */
    public int deleteAMarkSheetByMsId(Long msId);

    /**
     * 批量删除练习、测试评分表
     *
     * @param msIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAMarkSheetByMsIds(Long[] msIds);

    /**
     * 批量删除练习资源表
     *
     * @param msIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAExerciseResourceByMsIds(Long[] msIds);

    /**
     * 批量新增练习资源表
     *
     * @param aExerciseResourceList 练习资源表列表
     * @return 结果
     */
    public int batchAExerciseResource(List<AExerciseResource> aExerciseResourceList);


    /**
     * 通过练习、测试评分表主键删除练习资源表信息
     *
     * @param msId 练习、测试评分表ID
     * @return 结果
     */
    public int deleteAExerciseResourceByMsId(Long msId);
}
