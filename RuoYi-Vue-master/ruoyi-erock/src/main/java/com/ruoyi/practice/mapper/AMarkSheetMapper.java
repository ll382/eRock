package com.ruoyi.practice.mapper;

import java.util.List;

import com.ruoyi.core.domain.Student;
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.domain.AExerciseResource;
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
    public int insertAMarkSheet(AMarkSheet aMarkSheet);

    /**
     * 修改练习、测试评分表
     *
     * @param aMarkSheet 练习、测试评分表
     * @return 结果
     */
    public int updateAMarkSheet(AMarkSheet aMarkSheet);

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
