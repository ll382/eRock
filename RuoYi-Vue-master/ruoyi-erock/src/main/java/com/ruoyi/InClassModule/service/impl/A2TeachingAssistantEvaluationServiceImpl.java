package com.ruoyi.InClassModule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.InClassModule.domain.A2IdeologicalPerformance;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.service.SelectUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.InClassModule.mapper.A2TeachingAssistantEvaluationMapper;
import com.ruoyi.InClassModule.domain.A2TeachingAssistantEvaluation;
import com.ruoyi.InClassModule.service.IA2TeachingAssistantEvaluationService;

/**
 * A2 合作学习 教师评价Service业务层处理
 *
 * @author heye
 * @date 2024-04-11
 */
@Service
public class A2TeachingAssistantEvaluationServiceImpl implements IA2TeachingAssistantEvaluationService
{
    @Autowired
    private A2TeachingAssistantEvaluationMapper a2TeachingAssistantEvaluationMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SelectUser selectUser;

    /**
     * 查询A2 合作学习 教师评价
     *
     * @param crId A2 合作学习 教师评价主键
     * @return A2 合作学习 教师评价
     */
    @Override
    public List<A2TeachingAssistantEvaluation> selectA2TeachingAssistantEvaluationByTaeId(Long crId)
    {
        List<A2TeachingAssistantEvaluation> a2TeachingAssistantEvaluations = a2TeachingAssistantEvaluationMapper.selectA2TeachingAssistantEvaluationByTaeId(crId);
        a2TeachingAssistantEvaluations.forEach(System.out::println);
        return selectUser.selectInGroupStudent(selectUser.selectTeacher(a2TeachingAssistantEvaluations));
    }

    /**
     * 查询A2 合作学习 教师评价列表
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return A2 合作学习 教师评价
     */
    @Override
    public List<A2TeachingAssistantEvaluation> selectA2TeachingAssistantEvaluationList(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        return selectUser.selectTeacher(a2TeachingAssistantEvaluationMapper.selectA2TeachingAssistantEvaluationList(a2TeachingAssistantEvaluation));
    }

    /**
     * 新增A2 合作学习 教师评价
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return 结果
     */
    @Override
    public int insertA2TeachingAssistantEvaluation(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        A2TeachingAssistantEvaluation evaluation = new A2TeachingAssistantEvaluation();
        evaluation.setCrId(a2TeachingAssistantEvaluation.getCrId());
        evaluation.setGgId(a2TeachingAssistantEvaluation.getGgId());
        List<A2TeachingAssistantEvaluation> a2TeachingAssistantEvaluations = a2TeachingAssistantEvaluationMapper.selectA2TeachingAssistantEvaluationList(evaluation);
//        判断是否已经存在
        if (StringUtils.isNotEmpty(a2TeachingAssistantEvaluations)) {
            a2TeachingAssistantEvaluations.forEach(System.out::println);
            return 0;
        }else {
//            通过小组id给所有小组成员评分
            int u = a2TeachingAssistantEvaluationMapper.insertA2TeachingAssistantEvaluation(a2TeachingAssistantEvaluation);
            Student student = new Student();
            student.setGgId(a2TeachingAssistantEvaluation.getGgId());
            List<Student> students = studentMapper.selectStudentList(student);
            students.forEach(stu -> {
                AModuleScore aModuleScore = calculateScore(stu.getStuId());
                int i = selectUser.updateStudentAScore(aModuleScore, stu.getStuId());
                System.out.println(i);
            });
            return u;
        }
    }

    /**
     * 修改A2 合作学习 教师评价
     *
     * @param a2TeachingAssistantEvaluation A2 合作学习 教师评价
     * @return 结果
     */
    @Override
    public int updateA2TeachingAssistantEvaluation(A2TeachingAssistantEvaluation a2TeachingAssistantEvaluation)
    {
        int i = a2TeachingAssistantEvaluationMapper.updateA2TeachingAssistantEvaluation(a2TeachingAssistantEvaluation);
        if (i == 0) {
            return 0;
        }else {
            Student student = new Student();
            student.setGgId(a2TeachingAssistantEvaluation.getGgId());
            List<Student> students = studentMapper.selectStudentList(student);
            students.forEach(stu -> {
                AModuleScore aModuleScore = calculateScore(stu.getStuId());
                aModuleScore.setTeacherEvaluation(BigDecimal.valueOf(stu.getStuId()));
                int u = selectUser.updateStudentAScore(aModuleScore, stu.getStuId());
                System.out.println(u);
            });
            return i;
        }
    }

    /**
     * 批量删除A2 合作学习 教师评价
     *
     * @param taeIds 需要删除的A2 合作学习 教师评价主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantEvaluationByTaeIds(Long[] taeIds)
    {
        return a2TeachingAssistantEvaluationMapper.deleteA2TeachingAssistantEvaluationByTaeIds(taeIds);
    }

    /**
     * 删除A2 合作学习 教师评价信息
     *
     * @param taeId A2 合作学习 教师评价主键
     * @return 结果
     */
    @Override
    public int deleteA2TeachingAssistantEvaluationByTaeId(Long taeId)
    {
        return a2TeachingAssistantEvaluationMapper.deleteA2TeachingAssistantEvaluationByTaeId(taeId);
    }

    public AModuleScore calculateScore (Long stuId) {
        A2TeachingAssistantEvaluation evaluation = new A2TeachingAssistantEvaluation();
        evaluation.setStuId(stuId);
//        26代表表现优秀
        evaluation.setTaeType("26");
        int performWell = a2TeachingAssistantEvaluationMapper.selectA2TeachingAssistantEvaluationList(evaluation).size();
//        查找差劲表现记录
//        28代表表现差劲
        evaluation.setTaeType("28");
        int poorPerformance = a2TeachingAssistantEvaluationMapper.selectA2TeachingAssistantEvaluationList(evaluation).size();
//        计算表现总分
        Double ratingScore = selectUser.A2calculationTimes(performWell,poorPerformance);
        AModuleScore score = new AModuleScore();
        score.setTeacherEvaluation(BigDecimal.valueOf(ratingScore));
        return score;
    }
}
