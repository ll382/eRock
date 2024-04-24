package com.ruoyi.practice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.practice.domain.ABallExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.practice.mapper.AMarkSheetMapper;
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.service.IAMarkSheetService;

/**
 *  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算Service业务层处理
 *
 * @author ljy
 * @date 2024-04-11
 */
@Service
public class AMarkSheetServiceImpl implements IAMarkSheetService 
{
    @Autowired
    private AMarkSheetMapper aMarkSheetMapper;

    /**
     * 查询 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param msId  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算主键
     * @return  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     */
    @Override
    public AMarkSheet selectAMarkSheetByMsId(Long msId)
    {
        return aMarkSheetMapper.selectAMarkSheetByMsId(msId);
    }

    /**
     * 查询 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算列表
     *
     * @param aMarkSheet  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * @return  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     */
    @Override
    public List<AMarkSheet> selectAMarkSheetList(AMarkSheet aMarkSheet)
    {
        return aMarkSheetMapper.selectAMarkSheetList(aMarkSheet);
    }

    /**
     * 查询PC端第三页面
     * @param msId 评分Id
     * @param enumId 枚举Id
     * @return
     */
    public Map<String, Object> selectAMarkSheetByMsIdAndEnumId(Integer msId,Integer enumId){
        List<Map<String, Object>> maps = aMarkSheetMapper.selectAMarkSheetByMsIdAndEnumId(msId, enumId);
        ArrayList<String> mp4List = new ArrayList<>();
        ArrayList<String> jspList = new ArrayList<>();
        Map<String, Object> data = null;
        Boolean mp4 = false;
        for (Map<String, Object> msp:maps){
            if (data==null){
                data = msp;
            }
            String erSource = (String) msp.get("er_source");
            if (erSource.endsWith(".mp4")){
                mp4List.add(erSource);
                mp4 = true;
            }
            jspList.add(erSource);

        }
        if (mp4){
            if (mp4List.size() != 0){
                data.put("mp4", mp4List.get(0));
            }
            if (jspList.size() != 0){
                data.put("jsp", jspList.get(0));
            }
        }else {
            if (jspList.size()==1){
                data.put("jsp", jspList.get(0));
            }else {
                data.put("mp4", jspList.get(1));
                data.put("jsp", jspList.get(0));
            }
        }

        Map<String, Object> pageThreeMap = aMarkSheetMapper.selectAPageThreeByPcId(Integer.parseInt((String) data.get("pc_id")));
        data.putAll(pageThreeMap);
        return data;
    }

    /**
     * 新增 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param aMarkSheet  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * @return 结果
     */
    @Override
    public int insertAMarkSheet(AMarkSheet aMarkSheet)
    {
        return aMarkSheetMapper.insertAMarkSheet(aMarkSheet);
    }

    /**
     * 修改 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param aMarkSheet  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     * @return 结果
     */
    @Override
    public int updateAMarkSheet(AMarkSheet aMarkSheet)
    {
        return aMarkSheetMapper.updateAMarkSheet(aMarkSheet);
    }

    /**
     * 批量删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算
     *
     * @param msIds 需要删除的 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算主键
     * @return 结果
     */
    @Override
    public int deleteAMarkSheetByMsIds(Long[] msIds)
    {
        return aMarkSheetMapper.deleteAMarkSheetByMsIds(msIds);
    }

    /**
     * 删除 A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算信息
     *
     * @param msId  A1 A2 A3 技能练习、技能测试评分D模块进步分从这个中获取期初和期末的技能测试数据进行计算主键
     * @return 结果
     */
    @Override
    public int deleteAMarkSheetByMsId(Long msId)
    {
        return aMarkSheetMapper.deleteAMarkSheetByMsId(msId);
    }
}
