package com.ruoyi.practice.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.practice.domain.ABallExam;
import com.ruoyi.practice.mapper.AMarkSheetMapper;
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.service.IAMarkSheetService;

/**
 * 练习、测试评分表Service业务层处理
 * 
 * @author houq
 * @date 2024-04-09
 */
@Service
public class AMarkSheetServiceImpl implements IAMarkSheetService 
{
    @Autowired
    private AMarkSheetMapper aMarkSheetMapper;

    /**
     * 查询练习、测试评分表
     * 
     * @param msId 练习、测试评分表主键
     * @return 练习、测试评分表
     */
    @Override
    public AMarkSheet selectAMarkSheetByMsId(Long msId)
    {
        return aMarkSheetMapper.selectAMarkSheetByMsId(msId);
    }

    /**
     * 查询练习、测试评分表列表
     * 
     * @param aMarkSheet 练习、测试评分表
     * @return 练习、测试评分表
     */
    @Override
    public List<AMarkSheet> selectAMarkSheetList(AMarkSheet aMarkSheet)
    {
        return aMarkSheetMapper.selectAMarkSheetList(aMarkSheet);
    }

    /**
     * 新增练习、测试评分表
     * 
     * @param aMarkSheet 练习、测试评分表
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAMarkSheet(AMarkSheet aMarkSheet)
    {
        int rows = aMarkSheetMapper.insertAMarkSheet(aMarkSheet);
        insertABallExam(aMarkSheet);
        return rows;
    }

    /**
     * 修改练习、测试评分表
     * 
     * @param aMarkSheet 练习、测试评分表
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAMarkSheet(AMarkSheet aMarkSheet)
    {
        aMarkSheetMapper.deleteABallExamByMsId(aMarkSheet.getMsId());
        insertABallExam(aMarkSheet);
        return aMarkSheetMapper.updateAMarkSheet(aMarkSheet);
    }

    /**
     * 批量删除练习、测试评分表
     * 
     * @param msIds 需要删除的练习、测试评分表主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAMarkSheetByMsIds(Long[] msIds)
    {
        aMarkSheetMapper.deleteABallExamByMsIds(msIds);
        return aMarkSheetMapper.deleteAMarkSheetByMsIds(msIds);
    }

    /**
     * 删除练习、测试评分表信息
     * 
     * @param msId 练习、测试评分表主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAMarkSheetByMsId(Long msId)
    {
        aMarkSheetMapper.deleteABallExamByMsId(msId);
        return aMarkSheetMapper.deleteAMarkSheetByMsId(msId);
    }

    /**
     * 新增投篮运球表信息
     * 
     * @param aMarkSheet 练习、测试评分表对象
     */
    public void insertABallExam(AMarkSheet aMarkSheet)
    {
        List<ABallExam> aBallExamList = aMarkSheet.getABallExamList();
        Long msId = aMarkSheet.getMsId();
        if (StringUtils.isNotNull(aBallExamList))
        {
            List<ABallExam> list = new ArrayList<ABallExam>();
            for (ABallExam aBallExam : aBallExamList)
            {
                aBallExam.setMsId(msId);
                list.add(aBallExam);
            }
            if (list.size() > 0)
            {
                aMarkSheetMapper.batchABallExam(list);
            }
        }
    }
}
