package com.ruoyi.match.mapper;

import com.ruoyi.core.domain.StuGroup;
import com.ruoyi.core.domain.Student;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.domain.CPersonnelSheet;

import java.util.List;

/**
 * 球队参赛Mapper接口
 * 
 * @author heye
 * @date 2024-03-19
 */
public interface CBallteamMapper 
{
    /**
     * 查询球队参赛
     * 
     * @param balId 球队参赛主键
     * @return 球队参赛
     */
    public CBallteam selectCBallteamByBalId(Long balId);

    /**
     * 查询球队参赛列表
     * 
     * @param cBallteam 球队参赛
     * @return 球队参赛集合
     */
    public List<CBallteam> selectCBallteamList(CBallteam cBallteam);

    /**
     * 新增球队参赛
     * 
     * @param cBallteam 球队参赛
     * @return 结果
     */
    public int insertCBallteam(CBallteam cBallteam);

    /**
     * 修改球队参赛
     * 
     * @param cBallteam 球队参赛
     * @return 结果
     */
    public int updateCBallteam(CBallteam cBallteam);

    /**
     * 删除球队参赛
     * 
     * @param balId 球队参赛主键
     * @return 结果
     */
    public int deleteCBallteamByBalId(Long balId);

    /**
     * 批量删除球队参赛
     * 
     * @param balIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCBallteamByBalIds(Long[] balIds);

    /**
     * 批量删除C 球队内人员
     * 
     * @param balIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCPersonnelSheetByBalIds(Long[] balIds);
    
    /**
     * 批量新增C 球队内人员
     * 
     * @param cPersonnelSheetList C 球队内人员列表
     * @return 结果
     */
    public int batchCPersonnelSheet(List<CPersonnelSheet> cPersonnelSheetList);
    

    /**
     * 通过球队参赛主键删除C 球队内人员信息
     * 
     * @param balId 球队参赛ID
     * @return 结果
     */
    public int deleteCPersonnelSheetByBalId(Long balId);
    
    
    /**
     * 获取比赛小组信息
     *
     * @param ccRId
     * @return
     */
    public List<CBallteam> selectCompetitionRecordByCcRName(Long ccRId);


    /**
     * 新增小组
     *
     * @param stuGroup 小组
     * @return 结果
     */
    public int insertStuGroup(StuGroup stuGroup);
}
