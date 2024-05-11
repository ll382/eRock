package com.ruoyi.InClassModule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.InClassModule.domain.A2TeachingAssistantEvaluation;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.match.domain.ClassRegister;
import com.ruoyi.match.service.IClassRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2TeachingAssistantMapper;
import com.ruoyi.InClassModule.domain.A2TeachingAssistant;
import com.ruoyi.InClassModule.service.IA2TeachingAssistantService;

/**
 * A2 合作学习 助教评价
Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2TeachingAssistantServiceImpl implements IA2TeachingAssistantService
{
    @Autowired
    private A2TeachingAssistantMapper a2TeachingAssistantMapper;
    @Autowired
    private SelectUser selectUser;
    @Autowired
    private IClassRegisterService classRegisterService;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询A2 合作学习 助教评价

     *
     * @param crId A2 合作学习 助教评价
主键
     * @return A2 合作学习 助教评价

     */
    @Override
    public List<A2TeachingAssistant> selectA2TeachingAssistantByTaId(Long crId)
    {
        return selectUser.selectStudent(a2TeachingAssistantMapper.selectA2TeachingAssistantByTaId(crId));
    }

    /**
     * 查询A2 合作学习 助教评价
列表
     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return A2 合作学习 助教评价

     */
    @Override
    public List<A2TeachingAssistant> selectA2TeachingAssistantList(A2TeachingAssistant a2TeachingAssistant)
    {
        List<A2TeachingAssistant> list = a2TeachingAssistantMapper.selectA2TeachingAssistantList(a2TeachingAssistant);
        list.forEach(l -> {
            List<ClassRegister> classRegisters = classRegisterService.selectClassRegisterList(new ClassRegister(l.getCrId()));
            if (classRegisters.size() > 0) {
                l.setClassInfo(classRegisters.get(0));
            }
        });
        return selectUser.selectStudent(list);
    }

    /**
     * 新增A2 合作学习 助教评价

     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return 结果
     */
    @Override
    public int insertA2TeachingAssistant(A2TeachingAssistant a2TeachingAssistant)
    {
        A2TeachingAssistant assistant = new A2TeachingAssistant();
//        判断是否已经存在
        assistant.setCrId(a2TeachingAssistant.getCrId());
        assistant.setTaStu(a2TeachingAssistant.getTaStu());
        assistant.setStuId(a2TeachingAssistant.getStuId());
        List<A2TeachingAssistant> assistants = a2TeachingAssistantMapper.selectA2TeachingAssistantList(assistant);
        if (StringUtils.isNotEmpty(assistants)) {
            return 0;
        }else {
//            插入评价数据
            int i = a2TeachingAssistantMapper.insertA2TeachingAssistant(a2TeachingAssistant);
//            插入成绩数据
            AModuleScore aModuleScore = calculateScore(a2TeachingAssistant.getStuId());
            int u = selectUser.updateStudentAScore(aModuleScore, a2TeachingAssistant.getStuId());
            System.out.println(i);
            return i;
        }
    }

    /**
     * 修改A2 合作学习 助教评价

     *
     * @param a2TeachingAssistant A2 合作学习 助教评价

     * @return 结果
     */
    @Override
    public int updateA2TeachingAssistant(A2TeachingAssistant a2TeachingAssistant)
    {
        int i = a2TeachingAssistantMapper.updateA2TeachingAssistant(a2TeachingAssistant);
        if (i == 0) {
            return 0;
        }
        AModuleScore aModuleScore = calculateScore(a2TeachingAssistant.getStuId());
        int u = selectUser.updateStudentAScore(aModuleScore, a2TeachingAssistant.getStuId());
        System.out.println(u);
        return i;
    }

    /**
     * 批量删除A2 合作学习 助教评价

     *
     * @param taIds 需要删除的A2 合作学习 助教评价
主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantByTaIds(Long[] taIds)
    {
        return a2TeachingAssistantMapper.deleteA2TeachingAssistantByTaIds(taIds);
    }

    /**
     * 删除A2 合作学习 助教评价
信息
     *
     * @param taId A2 合作学习 助教评价
主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantByTaId(Long taId)
    {
        return a2TeachingAssistantMapper.deleteA2TeachingAssistantByTaId(taId);
    }

    public AModuleScore calculateScore (Long stuId) {
        A2TeachingAssistant assistant = new A2TeachingAssistant();
        assistant.setStuId(stuId);
//        26代表表现优秀
        assistant.setTaType("26");
        int performWell = a2TeachingAssistantMapper.selectA2TeachingAssistantList(assistant).size();
//        查找差劲表现记录
//        28代表表现差劲
        assistant.setTaType("28");
        int poorPerformance = a2TeachingAssistantMapper.selectA2TeachingAssistantList(assistant).size();
//        计算表现总分
        Double ratingScore = selectUser.A2calculationTimes(performWell,poorPerformance);
        AModuleScore score = new AModuleScore();
        score.setTeacherEvaluation(BigDecimal.valueOf(ratingScore));
        return score;
    }
}
