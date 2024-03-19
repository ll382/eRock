package com.ruoyi.match.mapper;

import java.util.List;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.domain.CProof;

/**
 * C 球队内人员Mapper接口
 * 
 * @author heye
 * @date 2024-03-19
 */
public interface CPersonnelSheetMapper 
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
     * 删除C 球队内人员
     * 
     * @param psId C 球队内人员主键
     * @return 结果
     */
    public int deleteCPersonnelSheetByPsId(Long psId);

    /**
     * 批量删除C 球队内人员
     * 
     * @param psIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCPersonnelSheetByPsIds(Long[] psIds);

    /**
     * 批量删除C 佐证
     * 
     * @param psIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCProofByPsIds(Long[] psIds);
    
    /**
     * 批量新增C 佐证
     * 
     * @param cProofList C 佐证列表
     * @return 结果
     */
    public int batchCProof(List<CProof> cProofList);
    

    /**
     * 通过C 球队内人员主键删除C 佐证信息
     * 
     * @param psId C 球队内人员ID
     * @return 结果
     */
    public int deleteCProofByPsId(Long psId);
}