package com.ruoyi.core.service;

import java.util.List;
import com.ruoyi.core.domain.AModuleScore;

/**
 *  A 模块成绩Service接口
 * 
 * @author heye
 * @date 2024-04-28
 */
public interface IAModuleScoreService 
{
    /**
     * 查询 A 模块成绩
     * 
     * @param modscoId  A 模块成绩主键
     * @return  A 模块成绩
     */
    public AModuleScore selectAModuleScoreByModscoId(Long modscoId);

    /**
     * 查询 A 模块成绩列表
     * 
     * @param aModuleScore  A 模块成绩
     * @return  A 模块成绩集合
     */
    public List<AModuleScore> selectAModuleScoreList(AModuleScore aModuleScore);

    /**
     * 新增 A 模块成绩
     * 
     * @param aModuleScore  A 模块成绩
     * @return 结果
     */
    public int insertAModuleScore(AModuleScore aModuleScore);

    /**
     * 修改 A 模块成绩
     * 
     * @param aModuleScore  A 模块成绩
     * @return 结果
     */
    public int updateAModuleScore(AModuleScore aModuleScore);

    /**
     * 批量删除 A 模块成绩
     * 
     * @param modscoIds 需要删除的 A 模块成绩主键集合
     * @return 结果
     */
    public int deleteAModuleScoreByModscoIds(Long[] modscoIds);

    /**
     * 删除 A 模块成绩信息
     * 
     * @param modscoId  A 模块成绩主键
     * @return 结果
     */
    public int deleteAModuleScoreByModscoId(Long modscoId);
}
