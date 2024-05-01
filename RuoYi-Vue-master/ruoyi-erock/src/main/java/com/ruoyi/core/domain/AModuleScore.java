package com.ruoyi.core.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  A 模块成绩对象 a_module_score
 *
 * @author heye
 * @date 2024-04-28
 */
public class AModuleScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** A模块成绩主键 */
    private Long modscoId;

    /** 总分ID */
    @Excel(name = "总分ID")
    private Long tsId;

    /** 线上学习 */
    @Excel(name = "线上学习")
    private BigDecimal onlineCourse;

    /** 知识测试 */
    @Excel(name = "知识测试")
    private BigDecimal knowledgeTests;

    /** 技能练习1 */
    @Excel(name = "技能练习1")
    private BigDecimal skillTests1;

    /** 课堂表现 */
    @Excel(name = "课堂表现")
    private BigDecimal classroomPerformance;

    /** 思政教育 */
    @Excel(name = "思政教育")
    private BigDecimal ideologicalPolitical;

    /** 学生互评 */
    @Excel(name = "学生互评")
    private BigDecimal peerAssessment;

    /** 助教评价 */
    @Excel(name = "助教评价")
    private BigDecimal teachingAssistant;

    /** 教师评价 */
    @Excel(name = "教师评价")
    private BigDecimal teacherEvaluation;

    /** 技能测试2 */
    @Excel(name = "技能测试2")
    private BigDecimal skillTests2;

    /** 体能测试2 */
    @Excel(name = "体能测试2")
    private BigDecimal fitnessTests2;

    /** 技术练习3 */
    @Excel(name = "技术练习3")
    private BigDecimal skillTests3;

    /** 体能练习3 */
    @Excel(name = "体能练习3")
    private BigDecimal fitnessTests3;

    public void IAModuleScore() {
        this.onlineCourse = BigDecimal.ZERO;
        this.knowledgeTests = BigDecimal.ZERO;
        this.skillTests1 = BigDecimal.ZERO;
        this.classroomPerformance = BigDecimal.ZERO;
        this.ideologicalPolitical = BigDecimal.ZERO;
        this.peerAssessment = BigDecimal.ZERO;
        this.teachingAssistant = BigDecimal.ZERO;
        this.skillTests2 = BigDecimal.ZERO;
        this.fitnessTests2 = BigDecimal.ZERO;
        this.skillTests3 = BigDecimal.ZERO;
        this.fitnessTests3 = BigDecimal.ZERO;
    }

    public void setModscoId(Long modscoId)
    {
        this.modscoId = modscoId;
    }

    public Long getModscoId()
    {
        return modscoId;
    }
    public void setTsId(Long tsId)
    {
        this.tsId = tsId;
    }

    public Long getTsId()
    {
        return tsId;
    }
    public void setOnlineCourse(BigDecimal onlineCourse)
    {
        this.onlineCourse = onlineCourse;
    }

    public BigDecimal getOnlineCourse()
    {
        return onlineCourse;
    }
    public void setKnowledgeTests(BigDecimal knowledgeTests)
    {
        this.knowledgeTests = knowledgeTests;
    }

    public BigDecimal getKnowledgeTests()
    {
        return knowledgeTests;
    }
    public void setSkillTests1(BigDecimal skillTests1)
    {
        this.skillTests1 = skillTests1;
    }

    public BigDecimal getSkillTests1()
    {
        return skillTests1;
    }
    public void setClassroomPerformance(BigDecimal classroomPerformance)
    {
        this.classroomPerformance = classroomPerformance;
    }

    public BigDecimal getClassroomPerformance()
    {
        return classroomPerformance;
    }
    public void setIdeologicalPolitical(BigDecimal ideologicalPolitical)
    {
        this.ideologicalPolitical = ideologicalPolitical;
    }

    public BigDecimal getTeacherEvaluation() {
        return teacherEvaluation;
    }

    public void setTeacherEvaluation(BigDecimal teacherEvaluation) {
        this.teacherEvaluation = teacherEvaluation;
    }

    public BigDecimal getIdeologicalPolitical()
    {
        return ideologicalPolitical;
    }
    public void setPeerAssessment(BigDecimal peerAssessment)
    {
        this.peerAssessment = peerAssessment;
    }

    public BigDecimal getPeerAssessment()
    {
        return peerAssessment;
    }
    public void setTeachingAssistant(BigDecimal teachingAssistant)
    {
        this.teachingAssistant = teachingAssistant;
    }

    public BigDecimal getTeachingAssistant()
    {
        return teachingAssistant;
    }
    public void setSkillTests2(BigDecimal skillTests2)
    {
        this.skillTests2 = skillTests2;
    }

    public BigDecimal getSkillTests2()
    {
        return skillTests2;
    }
    public void setFitnessTests2(BigDecimal fitnessTests2)
    {
        this.fitnessTests2 = fitnessTests2;
    }

    public BigDecimal getFitnessTests2()
    {
        return fitnessTests2;
    }
    public void setSkillTests3(BigDecimal skillTests3)
    {
        this.skillTests3 = skillTests3;
    }

    public BigDecimal getSkillTests3()
    {
        return skillTests3;
    }
    public void setFitnessTests3(BigDecimal fitnessTests3)
    {
        this.fitnessTests3 = fitnessTests3;
    }

    public BigDecimal getFitnessTests3()
    {
        return fitnessTests3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("modscoId", getModscoId())
            .append("tsId", getTsId())
            .append("onlineCourse", getOnlineCourse())
            .append("knowledgeTests", getKnowledgeTests())
            .append("skillTests1", getSkillTests1())
            .append("classroomPerformance", getClassroomPerformance())
            .append("ideologicalPolitical", getIdeologicalPolitical())
            .append("peerAssessment", getPeerAssessment())
            .append("teachingAssistant", getTeachingAssistant())
            .append("skillTests2", getSkillTests2())
            .append("fitnessTests2", getFitnessTests2())
            .append("teacherEvaluation", getTeacherEvaluation())
            .append("skillTests3", getSkillTests3())
            .append("fitnessTests3", getFitnessTests3())
            .toString();
    }
}
