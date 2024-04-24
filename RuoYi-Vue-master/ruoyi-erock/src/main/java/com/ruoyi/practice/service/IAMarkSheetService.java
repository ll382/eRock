package com.ruoyi.practice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.practice.domain.AMarkSheet;

/**
 *  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算Service接口
 *
 * @author ljy
 * @date 2024-04-11
 */
public interface IAMarkSheetService 
{
    /**
     * 查询 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param msId  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算主键
     * @return  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     */
    public AMarkSheet selectAMarkSheetByMsId(Long msId);

    /**
     * 查询 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算列表
     *
     * @param aMarkSheet  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * @return  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算集合
     */
    public List<AMarkSheet> selectAMarkSheetList(AMarkSheet aMarkSheet);

    /**
     * 查询PC端第三页面
     * @param msId 评分Id
     * @param enumId 枚举Id
     * @return
     */
    Map<String, Object> selectAMarkSheetByMsIdAndEnumId(Integer msId, Integer enumId);

    /**
     * 新增 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param aMarkSheet  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * @return 结果
     */
    public int insertAMarkSheet(AMarkSheet aMarkSheet);

    /**
     * 修改 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param aMarkSheet  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * @return 结果
     */
    public int updateAMarkSheet(AMarkSheet aMarkSheet);

    /**
     * 批量删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param msIds 需要删除的 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算主键集合
     * @return 结果
     */
    public int deleteAMarkSheetByMsIds(Long[] msIds);

    /**
     * 删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算信息
     *
     * @param msId  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算主键
     * @return 结果
     */
    public int deleteAMarkSheetByMsId(Long msId);
}
