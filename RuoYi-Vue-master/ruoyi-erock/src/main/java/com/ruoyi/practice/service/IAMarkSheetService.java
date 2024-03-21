package com.ruoyi.practice.service;

import java.util.List;
import com.ruoyi.practice.domain.AMarkSheet;

/**
 * 练习、测试评分表Service接口
 * 
 * @author houq
 * @date 2024-03-19
 */
public interface IAMarkSheetService 
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
     * 批量删除练习、测试评分表
     * 
     * @param msIds 需要删除的练习、测试评分表主键集合
     * @return 结果
     */
    public int deleteAMarkSheetByMsIds(Long[] msIds);

    /**
     * 删除练习、测试评分表信息
     * 
     * @param msId 练习、测试评分表主键
     * @return 结果
     */
    public int deleteAMarkSheetByMsId(Long msId);
}