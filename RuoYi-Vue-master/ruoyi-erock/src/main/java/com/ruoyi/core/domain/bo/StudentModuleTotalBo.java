package com.ruoyi.core.domain.bo;

import com.ruoyi.core.domain.Student;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;

import java.io.Serializable;
import java.util.List;

public class StudentModuleTotalBo extends Student implements Serializable {

    /** 总分对象 */
    private TotalScore totalScore;

    /** 模块分数对象 */
    private List<ModuleScore> moduleScoreList;

    public TotalScore getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(TotalScore totalScore) {
        this.totalScore = totalScore;
    }

    public List<ModuleScore> getModuleScoreList() {
        return moduleScoreList;
    }

    public void setModuleScoreList(List<ModuleScore> moduleScoreList) {
        this.moduleScoreList = moduleScoreList;
    }


}
