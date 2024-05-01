package com.ruoyi.InClassModule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.InClassModule.domain.A2Attendance;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.service.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2IdeologicalPerformanceMapper;
import com.ruoyi.InClassModule.domain.A2IdeologicalPerformance;
import com.ruoyi.InClassModule.service.IA2IdeologicalPerformanceService;

/**
 * A2  思政教育 思政现Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2IdeologicalPerformanceServiceImpl implements IA2IdeologicalPerformanceService
{
    @Autowired
    private A2IdeologicalPerformanceMapper a2IdeologicalPerformanceMapper;
    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2  思政教育 思政现
     *
     * @param crId A2  思政教育 思政现主键
     * @return A2  思政教育 思政现
     */
    @Override
    public List<A2IdeologicalPerformance> selectA2IdeologicalPerformanceByIpId(Long crId)
    {
        return selectUser.selectStudent(a2IdeologicalPerformanceMapper.selectA2IdeologicalPerformanceByIpId(crId));
    }

    /**
     * 查询A2  思政教育 思政现列表
     *
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return A2  思政教育 思政现
     */
    @Override
    public List<A2IdeologicalPerformance> selectA2IdeologicalPerformanceList(A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        return selectUser.selectStudent(a2IdeologicalPerformanceMapper.selectA2IdeologicalPerformanceList(a2IdeologicalPerformance));
    }

    /**
     * 新增A2  思政教育 思政现
     *
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return 结果
     */
    @Override
    public int insertA2IdeologicalPerformance(A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        A2IdeologicalPerformance performance = new A2IdeologicalPerformance();
        performance.setStuId(a2IdeologicalPerformance.getStuId());
        performance.setCrId(a2IdeologicalPerformance.getCrId());
        List<A2IdeologicalPerformance> list = a2IdeologicalPerformanceMapper.selectA2IdeologicalPerformanceList(performance);

        if (StringUtils.isNotEmpty(list)) {
            return -1;
        }else {
            int i = a2IdeologicalPerformanceMapper.insertA2IdeologicalPerformance(a2IdeologicalPerformance);
            AModuleScore aModuleScore = calculateScore(a2IdeologicalPerformance);
            int score = selectUser.updateStudentAScore(aModuleScore, a2IdeologicalPerformance.getStuId());
            System.out.println(score);
            return i;

        }
    }

    /**
     * 修改A2  思政教育 思政现
     *
     * @param a2IdeologicalPerformance A2  思政教育 思政现
     * @return 结果
     */
    @Override
    public int updateA2IdeologicalPerformance(A2IdeologicalPerformance a2IdeologicalPerformance)
    {
        int i = a2IdeologicalPerformanceMapper.updateA2IdeologicalPerformance(a2IdeologicalPerformance);
        System.out.println(i);
        if (i == 0) {
            return 0;
        }
        AModuleScore aModuleScore = calculateScore(a2IdeologicalPerformance);
        int score = selectUser.updateStudentAScore(aModuleScore, a2IdeologicalPerformance.getStuId());
        System.out.println(score);
        return i;
    }

    /**
     * 批量删除A2  思政教育 思政现
     *
     * @param ipIds 需要删除的A2  思政教育 思政现主键
     * @return 结果
     */
    @Override
    public int deleteA2IdeologicalPerformanceByIpIds(Long[] ipIds)
    {
        return a2IdeologicalPerformanceMapper.deleteA2IdeologicalPerformanceByIpIds(ipIds);
    }

    /**
     * 删除A2  思政教育 思政现信息
     *
     * @param ipId A2  思政教育 思政现主键
     * @return 结果
     */
    @Override
    public int deleteA2IdeologicalPerformanceByIpId(Long ipId)
    {
        return a2IdeologicalPerformanceMapper.deleteA2IdeologicalPerformanceByIpId(ipId);
    }

//    计算思政教育模块总分
    public AModuleScore calculateScore (A2IdeologicalPerformance a2IdeologicalPerformance) {
        A2IdeologicalPerformance performance = new A2IdeologicalPerformance();
        performance.setStuId(a2IdeologicalPerformance.getStuId());
//        26代表表现优秀
        performance.setIpType("26");
        int performWell = a2IdeologicalPerformanceMapper.selectA2IdeologicalPerformanceList(performance).size();
//        查找差劲表现记录
//        28代表表现差劲
        performance.setIpType("28");
        int poorPerformance = a2IdeologicalPerformanceMapper.selectA2IdeologicalPerformanceList(performance).size();
//        计算表现总分
        Double ratingScore = selectUser.A2calculationTimes(performWell,poorPerformance);
        AModuleScore score = new AModuleScore();
        score.setIdeologicalPolitical(BigDecimal.valueOf(ratingScore));
        return score;
    }
}
