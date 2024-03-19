package com.ruoyi.match.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.match.domain.CPersonnelSheet;
import com.ruoyi.match.mapper.CBallteamMapper;
import com.ruoyi.match.domain.CBallteam;
import com.ruoyi.match.service.ICBallteamService;

/**
 * 球队参赛Service业务层处理
 * 
 * @author heye
 * @date 2024-03-19
 */
@Service
public class CBallteamServiceImpl implements ICBallteamService 
{
    @Autowired
    private CBallteamMapper cBallteamMapper;

    /**
     * 查询球队参赛
     * 
     * @param balId 球队参赛主键
     * @return 球队参赛
     */
    @Override
    public CBallteam selectCBallteamByBalId(Long balId)
    {
        return cBallteamMapper.selectCBallteamByBalId(balId);
    }

    /**
     * 查询球队参赛列表
     * 
     * @param cBallteam 球队参赛
     * @return 球队参赛
     */
    @Override
    public List<CBallteam> selectCBallteamList(CBallteam cBallteam)
    {
        return cBallteamMapper.selectCBallteamList(cBallteam);
    }

    /**
     * 新增球队参赛
     * 
     * @param cBallteam 球队参赛
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCBallteam(CBallteam cBallteam)
    {
        int rows = cBallteamMapper.insertCBallteam(cBallteam);
        insertCPersonnelSheet(cBallteam);
        return rows;
    }

    /**
     * 修改球队参赛
     * 
     * @param cBallteam 球队参赛
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCBallteam(CBallteam cBallteam)
    {
        cBallteamMapper.deleteCPersonnelSheetByBalId(cBallteam.getBalId());
        insertCPersonnelSheet(cBallteam);
        return cBallteamMapper.updateCBallteam(cBallteam);
    }

    /**
     * 批量删除球队参赛
     * 
     * @param balIds 需要删除的球队参赛主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCBallteamByBalIds(Long[] balIds)
    {
        cBallteamMapper.deleteCPersonnelSheetByBalIds(balIds);
        return cBallteamMapper.deleteCBallteamByBalIds(balIds);
    }

    /**
     * 删除球队参赛信息
     * 
     * @param balId 球队参赛主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCBallteamByBalId(Long balId)
    {
        cBallteamMapper.deleteCPersonnelSheetByBalId(balId);
        return cBallteamMapper.deleteCBallteamByBalId(balId);
    }

    /**
     * 新增C 球队内人员信息
     * 
     * @param cBallteam 球队参赛对象
     */
    public void insertCPersonnelSheet(CBallteam cBallteam)
    {
        List<CPersonnelSheet> cPersonnelSheetList = cBallteam.getCPersonnelSheetList();
        Long balId = cBallteam.getBalId();
        if (StringUtils.isNotNull(cPersonnelSheetList))
        {
            List<CPersonnelSheet> list = new ArrayList<CPersonnelSheet>();
            for (CPersonnelSheet cPersonnelSheet : cPersonnelSheetList)
            {
                cPersonnelSheet.setBalId(balId);
                list.add(cPersonnelSheet);
            }
            if (list.size() > 0)
            {
                cBallteamMapper.batchCPersonnelSheet(list);
            }
        }
    }
}