package com.ruoyi.practice.mapper;

import java.util.List;
import com.ruoyi.match.domain.ClassRegister;
import com.ruoyi.practice.domain.AExerciseTask;
import org.apache.ibatis.annotations.Select;


/**
 * 课堂记录Mapper接口
 *
 * @author houq
 * @date 2024-04-09
 */
public interface AmodeClassRegisterMapper
{
    /**
     * 查询课堂记录
     *
     * @param crId 课堂记录主键
     * @return 课堂记录
     */
    public ClassRegister selectAmodeClassRegisterByCrId(Long crId);

    /**
     * 查询课堂记录列表
     *
     * @param AmodeClassRegister 课堂记录
     * @return 课堂记录集合
     */
    public List<ClassRegister> selectAmodeClassRegisterList(ClassRegister AmodeClassRegister);

    /**
     * 新增课堂记录
     *
     * @param AmodeClassRegister 课堂记录
     * @return 结果
     */
    public int insertAmodeClassRegister(ClassRegister AmodeClassRegister);

    /**
     * 修改课堂记录
     *
     * @param classRegister 课堂记录
     * @return 结果
     */
    public int updateAmodeClassRegister(ClassRegister AmodeClassRegister);

    /**
     * 删除课堂记录
     *
     * @param crId 课堂记录主键
     * @return 结果
     */
    public int deleteAmodeClassRegisterByCrId(Long crId);

    /**
     * 批量删除课堂记录
     *
     * @param crIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAmodeClassRegisterByCrIds(Long[] crIds);

    /**
     * 批量删除练习、测试任务表
     *
     * @param crIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAExerciseTaskByCrIds(Long[] crIds);

    /**
     * 批量新增练习、测试任务表
     *
     * @param aExerciseTaskList 练习、测试任务表列表
     * @return 结果
     */
    public int batchAExerciseTask(List<AExerciseTask> aExerciseTaskList);


    /**
     * 通过课堂记录主键删除练习、测试任务表信息
     *
     * @param crId 课堂记录ID
     * @return 结果
     */
    public int deleteAExerciseTaskByCrId(Long crId);
}
