package com.ruoyi.practice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.ruoyi.core.domain.vo.StudentCourseGrades;
import com.ruoyi.core.domain.Grade;
import com.ruoyi.core.domain.Semester;
import com.ruoyi.core.domain.Student;
import com.ruoyi.core.mapper.GradeMapper;
import com.ruoyi.core.mapper.StudentMapper;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.service.impl.SelectUserImpl;
import com.ruoyi.match.domain.ClassRegister;
import com.ruoyi.practice.domain.ABallExam;
import com.ruoyi.practice.domain.AExerciseTask;
import com.ruoyi.practice.mapper.AExerciseTaskMapper;
import com.ruoyi.practice.mapper.AmodeClassRegisterMapper;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.mapper.TotalScoreMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.practice.domain.AExerciseResource;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.practice.domain.AExerciseResource;
import com.ruoyi.practice.mapper.AMarkSheetMapper;
import com.ruoyi.practice.domain.AMarkSheet;
import com.ruoyi.practice.service.IAMarkSheetService;

/**
 * 练习、测试评分表Service业务层处理
 *
 * @author ljy
 * @date 2024-04-11
 */
@Service
public class AMarkSheetServiceImpl implements IAMarkSheetService
{
    @Autowired
    private AMarkSheetMapper aMarkSheetMapper;

    @Autowired
    private SelectUser selectUser;

    @Autowired
    private StudentMapper studentMapper;

    static List enumList = new ArrayList<>();
    static List main = new ArrayList();
    List mainAfter = Arrays.asList("观察你的投篮动作，我注意到你的投篮弧线不足。别担心，我们可以一起解决这个问题。\n" +
            "\n" +
            "首先，我们来看看你的投篮姿势。站稳双脚，保持身体平衡，膝盖微弯，手臂自然放松，肘部略微弯曲。记得在投篮时，将手指放在篮球的下半部分，而不是中心位置，这样可以帮助你产生更好的旋转和弧线。\n" +
            "\n" +
            "其次，我们需要练习投篮的力量和角度控制。进行一些力量训练，比如俯卧撑和哑铃推举，这会增强你的上肢力量。同时，练习不同角度的投篮，特别是远距离投篮，这样你就能更好地掌握投篮角度，产生更合适的弧线。\n" +
            "\n" +
            "最后，记得要持之以恒地练习。每天都安排一些时间进行投篮练习，特别关注弧线的控制。你还可以通过观看专业球员的比赛视频来学习他们的投篮技术和动作，这会对你的进步有很大帮助。\n" +
            "\n" +
            "相信我，只要你坚持不懈地练习，并且按照这些建议来调整你的投篮动作，你的投篮弧线一定会有所改善的。加油！",
            "我注意到你的投篮后旋不足，这可能会影响球的稳定性和准确性。别灰心，我们可以通过一些训练来改善这个问题。\n" +
            "\n" +
            "首先，让我们关注你的手部动作。在投篮时，确保手臂伸直，并且手指在释放球时给予足够的旋转。试着用指尖来控制球的旋转，而不是整个手掌，这样可以更精确地调整球的后旋角度。\n" +
            "\n" +
            "其次，练习投篮时的身体姿势。确保你的身体稳定，膝盖微弯，双脚平行并站稳。当你释放球时，用力将身体向上推动，这样可以帮助你产生更多的力量和旋转。\n" +
            "\n" +
            "另外，加强你的核心力量也很重要。通过做一些核心训练，比如仰卧起坐和平板支撑，来加强你的腹部和背部肌肉。一个稳定的核心可以帮助你在投篮时保持平衡和控制。\n" +
            "\n" +
            "最后，练习是关键。每天花一些时间在球场上练习投篮，特别关注后旋的控制。你还可以寻求教练或者更有经验的球员的帮助，让他们给你一些建议和指导。\n" +
            "\n" +
            "记住，提高技术需要时间和耐心。相信自己，坚持练习，你的投篮后旋一定会有所改善的。加油！",
            "我注意到你在投篮时的偏角不足，这可能会导致球偏离目标。但别担心，我们可以通过一些训练来改善这个问题。\n" +
            "\n" +
            "首先，让我们重点关注你的身体姿势。在投篮时，确保你的身体面向篮筐，双脚平行并站稳。注意保持膝盖微弯，这样可以帮助你更好地控制力量和平衡。\n" +
            "\n" +
            "其次，调整你的投篮动作。试着将肘部对准篮筐，并在释放球时用力向上推动。这样可以产生更好的弧线和偏角，增加命中的几率。\n" +
            "\n" +
            "另外，练习时要注重手部的动作。确保手臂伸直，手指在释放球时给予足够的力量和旋转。试着用指尖来控制球的方向，而不是整个手掌，这样可以更精准地调整偏角。\n" +
            "\n" +
            "最后，坚持练习是关键。每天花一些时间在球场上练习投篮，特别关注偏角的控制。你还可以寻求教练或更有经验的球员的帮助，让他们给你一些指导和建议。\n" +
            "\n" +
            "记住，提高投篮技术需要耐心和持续的努力。相信自己，坚持不懈地练习，你的投篮偏角一定会有所改善的。加油！");

    List mainAnthers = Arrays.asList("我注意到你在运球时可能会感到不够稳定，这可能会影响你的球场表现和自信心。但别担心，我们可以通过一些训练来改善这个问题。\n" +
            "\n" +
            "首先，让我们重点关注你的姿势和手部动作。在运球时，确保你的身体保持低姿态，膝盖微弯，双脚分开与肩同宽。将重心放在脚尖和脚跟之间，保持身体平衡。\n" +
            "\n" +
            "其次，练习控制球的力量和节奏。试着用手掌控制篮球，轻轻用力推球，保持轻松的手部动作。不要用太大的力量去拍打篮球，这样会影响控制和稳定性。\n" +
            "\n" +
            "另外，加强你的视野和感知能力。注视前方，保持头部和眼睛的平衡，时刻关注球的位置和动向。练习时可以尝试使用外界干扰物，如小圆锥或障碍物，来提高对周围环境的感知和应对能力。\n" +
            "\n" +
            "最后，坚持练习并逐步增加难度。从简单的基础运球开始，逐渐过渡到更复杂的运球动作和挑战。定期进行反馈和调整，记录你的进步并找出改进的空间。\n" +
            "\n" +
            "记住，运球稳定性需要时间和耐心来提高。相信自己，坚持不懈地练习，你的运球技术一定会有所提高。相信自己，你一定能够克服这个困难，成为更好的球员。加油！",
            "我知道你想要提高运球的力量，这样你在比赛中就能更加自信和有效地控制球场局面。以下是一些帮助你增强运球力量的建议：\n" +
            "\n" +
            "首先，要注意你的身体姿势。保持身体低位，膝盖微弯，双脚与肩同宽，重心稳定。这样可以使你的身体更好地支撑和控制运球动作。\n" +
            "\n" +
            "其次，注重手部动作和力量传递。用手掌控制篮球，使手部肌肉更好地参与到运球中来。练习时，尝试用更多的力量推动球，但不要牺牲控制。逐渐增加力量的同时保持稳定性。\n" +
            "\n" +
            "另外，加强核心肌群和下肢肌肉的训练。核心肌群包括腹部、背部和臀部肌肉，它们是身体稳定性和力量传递的关键。下肢肌肉则是支撑和推动身体的主要力量来源。通过增加核心和下肢肌肉的力量训练，你可以提高整体的身体稳定性和运球力量。\n" +
            "\n" +
            "最后，多练习，并注重技巧和力量的结合。在每次训练中，注重技术细节和力量发挥的结合。通过反复练习，你可以逐渐提高运球的力量和效率。\n" +
            "\n" +
            "记住，提高运球力量是一个持续的过程，需要耐心和坚持。相信自己，坚持不懈地训练，你一定能够取得进步。加油！",
            "我注意到你运球速度不够快，别担心，这是可以通过专注的练习和技巧改善的。下面是一些帮助你提高运球速度的建议：\n" +
            "\n" +
            "首先，要注重手部动作和控制。确保你用手掌控制篮球，而不是用指尖。这样可以提高控制力和稳定性，从而使你能够更快地推动篮球。\n" +
            "\n" +
            "其次，练习变化节奏和步伐。在运球时，不要总是保持相同的速度和节奏。尝试加快和减慢运球的速度，以及改变步伐的频率和方向。这样可以让你更灵活地应对不同的防守和场景。\n" +
            "\n" +
            "另外，注重爆发力和加速能力的训练。进行爆发力和加速度的训练，比如做一些快速的冲刺、跳跃和爆发力训练，这可以提高你的起步速度和突破能力，从而使你在比赛中更快地运球。\n" +
            "\n" +
            "最后，多进行模拟比赛的实战训练。在练习中模拟比赛场景，尽量在高压力和高速度下进行运球训练，这样可以帮助你更好地适应比赛中的压力和节奏，提高你的运球速度和应对能力。\n" +
            "\n" +
            "记住，提高运球速度需要持之以恒的练习和耐心。相信自己，坚持不懈地训练，你一定能够取得进步。加油！");

    /**
     * 查询练习、测试评分表
     *
     * @param msId 练习、测试评分表主键
     * @return 练习、测试评分表
     */
    @Override
    public AMarkSheet selectAMarkSheetByMsId(Long msId)
    {
        return (AMarkSheet) selectUser.selectStudent(aMarkSheetMapper.selectAMarkSheetByMsId(msId));
    }

    /**
     * 查询未提交名单
     *
     * @param etId 练习、测试评分表
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
     * 新增练习、测试评分表
     *
     * @param aMarkSheet 练习、测试评分表
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAMarkSheet(AMarkSheet aMarkSheet)
    {

//        查找学期
        Semester semester = selectUser.selectDate(new Date());

//        进步分计算
        List<BigDecimal> progressists = this.tScore(aMarkSheet,semester);

//        progressists.forEach(System.out::println);

//        算投篮分（15枚举）
        BigDecimal basketScore = progressists.get(0).multiply(BigDecimal.valueOf(0.5));
//        算运球分（15枚举）
        BigDecimal dribbleScore = progressists.get(0).multiply(BigDecimal.valueOf(0.5));
//        查找学期和学号
        TotalScore totalScore = new TotalScore();
        totalScore.setSemesterId(semester.getSemesterId());
        totalScore.setStuId(aMarkSheet.getStuId());
//         判断当前学期和学号是否有数据如果   有则返回id  没有创建并返回id
        Long tsId = selectUser.judgeInformation(totalScore);
//        查询当前任务是否有子成绩
        ModuleScore moduleScore = new ModuleScore();
//        设置总分ID
        moduleScore.setTsId(tsId);
//        D模块（8A，9B，10C，11D）
        moduleScore.setEnumId(11L);
        moduleScore.setAvsScore(basketScore.add(dribbleScore));
//        调用查找方法
        ModuleScore score = selectUser.judgeModuleScore(moduleScore);
//        D1模块结束
//        System.out.println("D模块分数：");
//        System.out.println(score);

//        维护pc数据，将用户的值输入至ballExam表中
        ABallExam ballExam = new ABallExam();

        aMarkSheet.setMsTime(new Date());

        AMarkSheet aMark = new AMarkSheet();
        aMark.setStuId(aMarkSheet.getStuId());
        aMark.setEtId(aMarkSheet.getEtId());
        if (StringUtils.isNotEmpty(aMarkSheetMapper.selectAMarkSheetList(aMark))){
            return -1;
        }
//        判断如果是运球则将内容换过
        if ("16".equals(aMarkSheetMapper.selectCriteria(aMarkSheet.getMsId()))){
            enumList = Arrays.asList("稳定性","力量","速度");
            main = mainAnthers;
        }else {
            enumList = Arrays.asList("弧线","后旋","偏角");
            main = mainAfter;
        }
//        编辑处方内容
        String s = aMarkSheet.getMs1().compareTo(aMarkSheet.getMs2()) == -1 ? (aMarkSheet.getMs1().compareTo(aMarkSheet.getMs3()) == -1 ? enumList.get(0)+"最小" + aMarkSheet.getMs1() + "分，" + main.get(0) : enumList.get(2)+"最小" + aMarkSheet.getMs3() + "分，" + main.get(2)) : (aMarkSheet.getMs2().compareTo(aMarkSheet.getMs3()) == -1 ? enumList.get(1)+"最小" + aMarkSheet.getMs2() + "分，" + main.get(1) : enumList.get(2)+"最小" + aMarkSheet.getMs3() + "分，" + main.get(2));
        aMarkSheet.setMsClass("义眼丁真，鉴定为：" + s);
        int rows = aMarkSheetMapper.insertAMarkSheet(aMarkSheet);
//        查找ballExam内容
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
        return Math.toIntExact(aMarkSheet.getMsId());
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
    public List<BigDecimal> tScore(AMarkSheet aMarkSheet, Semester semester){
        List<BigDecimal> ld = new ArrayList<BigDecimal>();
//        设置投篮运球循环，将两个不同的内容查找出来
        for (Long i : new Long[]{15L, 16L}) {

//            如果找不到学期返回空
            if (StringUtils.isEmpty(new Semester[]{semester})){
                return null;
            }
//             查到本学期所有的内容
            List<AMarkSheet> taskSheet = aMarkSheetMapper.selectClassRegisters(i, aMarkSheet.getStuId(),semester.getSemesterId());
//            System.out.println("---------------------");
//            taskSheet.forEach(System.out::println);
//            System.out.println("---------------------");
//            通过学生id查找班级
            List<Student> students = studentMapper.selectStudentList(new Student(aMarkSheet.getStuId()));
//            获取班级学生内容
            Student student = students.get(0);
//            本学期第一组成绩
            AMarkSheet markShe = taskSheet.get(0);
//            查找期初全部人的成绩取其平均值
            Double beginSocre = aMarkSheetMapper.selectEveroneMsScore(i, semester.getSemesterId(), student.getClassId(), markShe.getMsTime());
//            查找期末全部人的成绩取其平均值
            Double endSocre = aMarkSheetMapper.selectEveroneMsScore(i, semester.getSemesterId(), student.getClassId(), null);
//            System.out.println("期初平均成绩");
//            System.out.println(beginSocre);
//            System.out.println("期末平均成绩");
//            System.out.println(endSocre);
//            创造成绩数组
            List<BigDecimal> msScore = new ArrayList<>();
            for (AMarkSheet markSheet : taskSheet) {
                msScore.add(markSheet.getMsScore());
            }
//          小数后两位
            int scale = 2;

//            本学期第一个成绩
            BigDecimal minMsScore = msScore.get(0);
//            本学期最后一个成绩
            BigDecimal maxMsScore = msScore.get(msScore.size() - 1);
//            TODO：标准差的计算
//            开根号下的(Σ(Xi - Xba)?)/n-1
            Double std = SelectUserImpl.standardDeviation(msScore,scale);
            System.out.println(std);

//            TODO: T分的计算
//            期初T分
            BigDecimal tBeginningScore = minMsScore.subtract(BigDecimal.valueOf(beginSocre)).divide(BigDecimal.valueOf(std),scale,RoundingMode.HALF_UP);
//            System.out.println(tBeginningScore);
//            期末T分
            BigDecimal tEndScore = maxMsScore.subtract(BigDecimal.valueOf(endSocre)).divide(BigDecimal.valueOf(std),scale,RoundingMode.HALF_UP);
//            System.out.println(tEndScore);
//            T分查找d1_conversion进步分
            Double tBProgressist =0D;
            Double tEProgressist =0D;
            BigDecimal integration = BigDecimal.valueOf(20);
            if(tBeginningScore.compareTo(integration) == 1){
                tBProgressist = aMarkSheetMapper.selectTScore(tBeginningScore,tBeginningScore.add(BigDecimal.ONE));
//                System.out.println(tBProgressist);
            }
            if(tBeginningScore.compareTo(integration) == 1) {
                tEProgressist = aMarkSheetMapper.selectTScore(tEndScore,tEndScore.add(BigDecimal.ONE));
//                System.out.println(tEProgressist);
            }
//            学期前减学期后获得真进步分
            Double tureProgressist = tEProgressist - tBProgressist;
            ld.add(BigDecimal.valueOf(tureProgressist));
//            System.out.println(tureProgressist);
        }
        return ld;
    }
}
