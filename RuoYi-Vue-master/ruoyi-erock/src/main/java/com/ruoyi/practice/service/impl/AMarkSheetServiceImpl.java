package com.ruoyi.practice.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.practice.domain.ABallExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.practice.domain.AExerciseResource;
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

    @Autowired
    private SelectUser selectUser;

    /**
     * 查询练习、测试评分表
     *
     * @param msId 练习、测试评分表主键
     * @return 练习、测试评分表
     */
    @Override
    public AMarkSheet selectAMarkSheetByMsId(Long msId)
    {
        return selectUser.selectStudent(aMarkSheetMapper.selectAMarkSheetByMsId(msId));
    }

    /**
     * 查询未提交名单
     *
     * @param List<Student> 练习、测试评分表
     * @return 练习、测试评分表
     */
    @Override
    public List<Student> selectAMarkSheetList(Long etId)
    {
        return aMarkSheetMapper.selectAMarkSheet(etId);
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
        return selectUser.selectStudent(aMarkSheetMapper.selectAMarkSheetList(aMarkSheet));
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
//        维护pc数据，将用户的值输入至ballExam表中
        ABallExam ballExam = new ABallExam();

        aMarkSheet.setMsTime(new Date());
        int rows = aMarkSheetMapper.insertAMarkSheet(aMarkSheet);

        ballExam.setMsId(aMarkSheet.getMsId());
        ballExam.setDriStability(aMarkSheet.getMs2());
        ballExam.setDriPower(aMarkSheet.getMs1());
        ballExam.setDriSpeed(aMarkSheet.getMs3());
        ballExam.setDriAnalysis(aMarkSheet.getMsClass());
        ballExam.setShoArc(aMarkSheet.getMs3());
        ballExam.setShoAngle(aMarkSheet.getMs2());
        ballExam.setShoSpinner(aMarkSheet.getMs1());
        ballExam.setShoAnalysis(aMarkSheet.getMsClass());

        insertAExerciseResource(aMarkSheet);
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
        aMarkSheetMapper.deleteAExerciseResourceByMsId(aMarkSheet.getMsId());
        insertAExerciseResource(aMarkSheet);
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
        aMarkSheetMapper.deleteAExerciseResourceByMsIds(msIds);
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
        aMarkSheetMapper.deleteAExerciseResourceByMsId(msId);
        return aMarkSheetMapper.deleteAMarkSheetByMsId(msId);
    }

    /**
     * 新增练习资源表信息
     *
     * @param aMarkSheet 练习、测试评分表对象
     */
    public void insertAExerciseResource(AMarkSheet aMarkSheet)
    {
        List<AExerciseResource> aExerciseResourceList = aMarkSheet.getAExerciseResourceList();
        Long msId = aMarkSheet.getMsId();
        if (StringUtils.isNotNull(aExerciseResourceList))
        {
            List<AExerciseResource> list = new ArrayList<AExerciseResource>();
            for (AExerciseResource aExerciseResource : aExerciseResourceList)
            {
                aExerciseResource.setMsId(msId);
                list.add(aExerciseResource);
            }
            if (list.size() > 0)
            {
                aMarkSheetMapper.batchAExerciseResource(list);
            }
        }
    }
}
