package com.ruoyi;


import com.ruoyi.core.domain.bo.StudentModuleTotalBo;
import com.ruoyi.core.domain.dto.StudentDTO;
import com.ruoyi.core.domain.vo.StudentModuleTotalScoreAndRankingVo;
import com.ruoyi.core.domain.vo.StudentModuleTotalScoreVo;
import com.ruoyi.core.mapper.StudentMapper;

import com.ruoyi.score.domain.ModuleScore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest
class RuoYiApplicationTests {

    @Autowired
    private StudentMapper studentMapper;


    @Test
    public void s1() {
        System.out.println(123);
    }

    @Test
    public void test(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setClassId(1l);
        studentDTO.setStuId(71l);

        List<StudentModuleTotalBo> studentModuleTotalBos = studentMapper.selectStudentFinalGrade(studentDTO);

        List<StudentModuleTotalScoreAndRankingVo> collect = new ArrayList<>();
        Integer index = 0;
        for (StudentModuleTotalBo studentModuleTotalBo:studentModuleTotalBos){
            index++;
            StudentModuleTotalScoreAndRankingVo studentModuleTotalScoreAndRankingVo = new StudentModuleTotalScoreAndRankingVo();
            BeanUtils.copyProperties(studentModuleTotalBo, studentModuleTotalScoreAndRankingVo);
            if (studentModuleTotalBo.getTotalScore() != null){
                studentModuleTotalScoreAndRankingVo.setEpScore(studentModuleTotalBo.getTotalScore().getEpScore());
            }
            List<ModuleScore> moduleScoreList = studentModuleTotalBo.getModuleScoreList();
            ArrayList<BigDecimal> avsScoreList = new ArrayList<>();
            for (ModuleScore moduleScore: moduleScoreList){
                avsScoreList.add(moduleScore.getAvsScore());
            }
            studentModuleTotalScoreAndRankingVo.setAvsScore(avsScoreList);
            studentModuleTotalScoreAndRankingVo.setRanking(index);
            collect.add(studentModuleTotalScoreAndRankingVo);
        }

        if (studentDTO.getStuId() != null){
            for (StudentModuleTotalScoreAndRankingVo studentModuleTotalScoreAndRankingVo :collect){
                if (studentDTO.getStuId() == studentModuleTotalScoreAndRankingVo.getStuId()){
                    collect.clear();
                    collect.add(studentModuleTotalScoreAndRankingVo);
                    System.out.println(collect);
                    return;
                }
            }
        }


    }
}
