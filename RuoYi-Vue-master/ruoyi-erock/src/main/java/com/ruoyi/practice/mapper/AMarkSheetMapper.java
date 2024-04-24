package com.ruoyi.practice.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.practice.domain.AMarkSheet;
import org.apache.ibatis.annotations.Param;

/**
 *  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算Mapper接口
 * 
 * @author ljy
 * @date 2024-04-11
 */
public interface AMarkSheetMapper 
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
    public List<Map<String,Object>> selectAMarkSheetByMsIdAndEnumId(@Param("msId") Integer msId,@Param("enumId") Integer enumId);

    /**
     * 查询内容视频
     * @param pcId pcId
     * @return
     */
    public Map<String,Object> selectAPageThreeByPcId(Integer pcId);
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
     * 删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * 
     * @param msId  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算主键
     * @return 结果
     */
    public int deleteAMarkSheetByMsId(Long msId);

    /**
     * 批量删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * 
     * @param msIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAMarkSheetByMsIds(Long[] msIds);
}
