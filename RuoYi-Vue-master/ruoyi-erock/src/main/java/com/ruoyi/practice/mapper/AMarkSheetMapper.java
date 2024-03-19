package com.ruoyi.practice.mapper;

import java.util.List;
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.domain.ABallExam;

/**
 * 练习、测试评分表Mapper接口
 * 
 * @author houq
 * @date 2024-03-19
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
     * 批量删除投篮运球表
     * 
     * @param msIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteABallExamByMsIds(Long[] msIds);
    
    /**
     * 批量新增投篮运球表
     * 
     * @param aBallExamList 投篮运球表列表
     * @return 结果
     */
    public int batchABallExam(List<ABallExam> aBallExamList);
    

    /**
     * 通过练习、测试评分表主键删除投篮运球表信息
     * 
     * @param msId 练习、测试评分表ID
     * @return 结果
     */
    public int deleteABallExamByMsId(Long msId);
}
