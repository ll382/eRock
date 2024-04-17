package com.ruoyi.match.service;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.match.domain.ClassRegister;

/**
 * 课堂记录Service接口
 *
 * @author houq
 * @date 2024-04-08
 */
public interface IClassRegisterService
{
    public ClassRegister selectAmodeClassRegisterByCrId(Long crId);
    /**
     * 查询课堂记录
     *
     * @param crId 课堂记录主键
     * @return 课堂记录
     */
    public ClassRegister selectClassRegisterByCrId(Long crId);

    /**
     * 查询课堂记录列表
     *
     * @param classRegister 课堂记录
     * @return 课堂记录集合
     */
    public List<ClassRegister> selectClassRegisterList(ClassRegister classRegister);

    /**
     * 新增课堂记录
     *
     * @param classRegister 课堂记录
     * @return 结果
     */
    public int insertClassRegister(ClassRegister classRegister);

    /**
     * 修改课堂记录
     *
     * @param classRegister 课堂记录
     * @return 结果
     */
    public int updateClassRegister(ClassRegister classRegister);

    /**
     * 批量删除课堂记录
     *
     * @param crIds 需要删除的课堂记录主键集合
     * @return 结果
     */
    public int deleteClassRegisterByCrIds(Long[] crIds);

    public int deleteAmodeClassRegister(Long crIds);

    /**
     * 删除课堂记录信息
     *
     * @param crId 课堂记录主键
     * @return 结果
     */
    public int deleteClassRegisterByCrId(Long crId);

    /**
     * 新增课内赛课堂记录
     *
     * @param map 课堂记录
     * @return 结果
     */
    public int insertClassRegisterKn(HashMap<String, Object> map);
}
