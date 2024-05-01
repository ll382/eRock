package com.ruoyi.InClassModule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.InClassModule.domain.A2TeachingAssistant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2StudentEvaluateMapper;
import com.ruoyi.InClassModule.domain.A2StudentEvaluate;
import com.ruoyi.InClassModule.service.IA2StudentEvaluateService;

/**
 * A2 合作学习 学生互评Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2StudentEvaluateServiceImpl implements IA2StudentEvaluateService
{
    @Autowired
    private A2StudentEvaluateMapper a2StudentEvaluateMapper;
    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 合作学习 学生互评
     *
     * @param crId A2 合作学习 学生互评主键
     * @return A2 合作学习 学生互评
     */
    @Override
    public List<A2StudentEvaluate> selectA2StudentEvaluateBySeId(Long crId)
    {
        return selectUser.selectStudent(a2StudentEvaluateMapper.selectA2StudentEvaluateBySeId(crId));
    }

    /**
     * 查询A2 合作学习 学生互评列表
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return A2 合作学习 学生互评
     */
    @Override
    public List<A2StudentEvaluate> selectA2StudentEvaluateList(A2StudentEvaluate a2StudentEvaluate)
    {
        return selectUser.selectStudent(a2StudentEvaluateMapper.selectA2StudentEvaluateList(a2StudentEvaluate));
    }

    /**
     * 新增A2 合作学习 学生互评
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return 结果
     */
    @Override
    public int insertA2StudentEvaluate(A2StudentEvaluate a2StudentEvaluate)
    {
        A2StudentEvaluate as = new A2StudentEvaluate();
//        判断评价学生以及评价学生者和评价课程是否相同
        as.setStuId(a2StudentEvaluate.getStuId());
        as.setEsId(a2StudentEvaluate.getEsId());
        as.setCrId(a2StudentEvaluate.getCrId());
        List<A2StudentEvaluate> a2StudentEvaluates = a2StudentEvaluateMapper.selectA2StudentEvaluateList(as);
//        相同直接返回警告
        if (StringUtils.isNotEmpty(a2StudentEvaluates)){
            return 0;
        }else {
            int i = a2StudentEvaluateMapper.insertA2StudentEvaluate(a2StudentEvaluate);
            AModuleScore aModuleScore = calculateScore(a2StudentEvaluate.getStuId());
            int u = selectUser.updateStudentAScore(aModuleScore, a2StudentEvaluate.getStuId());
            System.out.println(u);
            return i;
        }
    }

    /**
     * 修改A2 合作学习 学生互评
     *
     * @param a2StudentEvaluate A2 合作学习 学生互评
     * @return 结果
     */
    @Override
    public int updateA2StudentEvaluate(A2StudentEvaluate a2StudentEvaluate)
    {
        int u = a2StudentEvaluateMapper.updateA2StudentEvaluate(a2StudentEvaluate);
        if (u == 0) {
            return 0;
        }
        AModuleScore aModuleScore = calculateScore(a2StudentEvaluate.getStuId());
        int i = selectUser.updateStudentAScore(aModuleScore, a2StudentEvaluate.getStuId());
        System.out.println(i);
        return u;
    }

    /**
     * 批量删除A2 合作学习 学生互评
     *
     * @param seIds 需要删除的A2 合作学习 学生互评主键
     * @return 结果
     */
    @Override
    public int deleteA2StudentEvaluateBySeIds(Long[] seIds)
    {
        return a2StudentEvaluateMapper.deleteA2StudentEvaluateBySeIds(seIds);
    }

    /**
     * 删除A2 合作学习 学生互评信息
     *
     * @param seId A2 合作学习 学生互评主键
     * @return 结果
     */
    @Override
    public int deleteA2StudentEvaluateBySeId(Long seId)
    {
        return a2StudentEvaluateMapper.deleteA2StudentEvaluateBySeId(seId);
    }

    public AModuleScore calculateScore (Long stuId) {
        A2StudentEvaluate evaluate = new A2StudentEvaluate();
        evaluate.setStuId(stuId);
//        26代表表现优秀
        evaluate.setEeType("26");
        int performWell = a2StudentEvaluateMapper.selectA2StudentEvaluateList(evaluate).size();
//        查找差劲表现记录
//        28代表表现差劲
        evaluate.setEeType("28");
        int poorPerformance = a2StudentEvaluateMapper.selectA2StudentEvaluateList(evaluate).size();
//        计算表现总分
        Double ratingScore = selectUser.A2calculationTimes(performWell,poorPerformance);
        AModuleScore score = new AModuleScore();
        score.setPeerAssessment(BigDecimal.valueOf(ratingScore));
        return score;
    }
}
