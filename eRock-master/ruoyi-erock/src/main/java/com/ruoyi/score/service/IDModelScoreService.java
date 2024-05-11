package com.ruoyi.score.service;

import java.util.List;
import com.ruoyi.score.domain.DModelScore;

/**
 * D 模块成绩Service接口
 * 
 * @author heye
 * @date 2024-04-25
 */
public interface IDModelScoreService 
{
    /**
     * 查询D 模块成绩
     * 
     * @param modscId D 模块成绩主键
     * @return D 模块成绩
     */
    public DModelScore selectDModelScoreByModscId(Long modscId);

    /**
     * 查询D 模块成绩列表
     * 
     * @param dModelScore D 模块成绩
     * @return D 模块成绩集合
     */
    public List<DModelScore> selectDModelScoreList(DModelScore dModelScore);

    /**
     * 根据学生ID查询D 模块成绩
     * @param stuId 学生ID
     * @return
     */
    public DModelScore selectDModelScoreByStuId(Long stuId);

    /**
     * 新增D 模块成绩
     * 
     * @param dModelScore D 模块成绩
     * @return 结果
     */
    public int insertDModelScore(DModelScore dModelScore);

    /**
     * 修改D 模块成绩
     * 
     * @param dModelScore D 模块成绩
     * @return 结果
     */
    public int updateDModelScore(DModelScore dModelScore);

    /**
     * 批量删除D 模块成绩
     * 
     * @param modscIds 需要删除的D 模块成绩主键集合
     * @return 结果
     */
    public int deleteDModelScoreByModscIds(Long[] modscIds);

    /**
     * 删除D 模块成绩信息
     * 
     * @param modscId D 模块成绩主键
     * @return 结果
     */
    public int deleteDModelScoreByModscId(Long modscId);
}
