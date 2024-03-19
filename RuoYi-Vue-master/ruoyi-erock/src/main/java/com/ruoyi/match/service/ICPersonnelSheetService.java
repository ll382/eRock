package com.ruoyi.match.service;

import java.util.List;
import com.ruoyi.match.domain.CPersonnelSheet;

/**
 * C 球队内人员Service接口
 * 
 * @author heye
 * @date 2024-03-19
 */
public interface ICPersonnelSheetService 
{
    /**
     * 查询C 球队内人员
     * 
     * @param psId C 球队内人员主键
     * @return C 球队内人员
     */
    public CPersonnelSheet selectCPersonnelSheetByPsId(Long psId);

    /**
     * 查询C 球队内人员列表
     * 
     * @param cPersonnelSheet C 球队内人员
     * @return C 球队内人员集合
     */
    public List<CPersonnelSheet> selectCPersonnelSheetList(CPersonnelSheet cPersonnelSheet);

    /**
     * 新增C 球队内人员
     * 
     * @param cPersonnelSheet C 球队内人员
     * @return 结果
     */
    public int insertCPersonnelSheet(CPersonnelSheet cPersonnelSheet);

    /**
     * 修改C 球队内人员
     * 
     * @param cPersonnelSheet C 球队内人员
     * @return 结果
     */
    public int updateCPersonnelSheet(CPersonnelSheet cPersonnelSheet);

    /**
     * 批量删除C 球队内人员
     * 
     * @param psIds 需要删除的C 球队内人员主键集合
     * @return 结果
     */
    public int deleteCPersonnelSheetByPsIds(Long[] psIds);

    /**
     * 删除C 球队内人员信息
     * 
     * @param psId C 球队内人员主键
     * @return 结果
     */
    public int deleteCPersonnelSheetByPsId(Long psId);
}