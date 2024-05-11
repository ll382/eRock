package com.ruoyi.core.service.impl;

import com.ruoyi.afterClassModel.domain.A3PhysicalTraining;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SelectUserVo;
import com.ruoyi.common.core.domain.entity.Group;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.core.domain.AModuleScore;
import com.ruoyi.core.domain.Semester;
import com.ruoyi.core.mapper.AModuleScoreMapper;
import com.ruoyi.core.service.IStuGroupService;
import com.ruoyi.core.service.IStudentService;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.core.mapper.SelectUserMapper;
import com.ruoyi.knowledgeQuiz.domain.A1Task;
import com.ruoyi.score.domain.DModelScore;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.mapper.DModelScoreMapper;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.mapper.TotalScoreMapper;
import com.ruoyi.score.service.ITotalScoreService;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
@Service
public class SelectUserImpl<T extends BaseEntity> implements SelectUser<T> {
    @Autowired
    SelectUserMapper selectUserMapper;

    @Autowired
    IStudentService studentService;

    @Autowired
    IStuGroupService stuGroupService;

//    总分表

    @Autowired
    private TotalScoreMapper totalScoreMapper;

//    模块表

    @Autowired
    private ModuleScoreMapper moduleScoreMapper;

//    D模块成绩

    @Autowired
    private DModelScoreMapper dModelScoreMapper;

    @Autowired
    SelectUser selectUser;

//    总分模块的使用

    @Autowired
    private ITotalScoreService totalScoreService;

//    A模块成绩

    @Autowired
    private AModuleScoreMapper aModuleScoreMapper;

//    随机数组
    public static LinkedHashSet<Integer> generateRandomNumbers(int n, int max) {
        if (n > max) {
            throw new IllegalArgumentException("n must be less than or equal to max");
        }

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        Random random = new Random();

        while (set.size() < n) {
            int num = random.nextInt(max) + 1;
            set.add(num);
        }

        return set;
    }

//    拼音工具
    public static String toPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    @Override
    public List<T> selectStudent(List<T> stuList){
        stuList.forEach(student -> {
            student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId()));
        });
        return stuList;
    }

    @Override
    public A1Task calculateScore(A1Task a1Task){
////        计算每道题平均百分制占分
//        NumberFormat nf = NumberFormat.getNumberInstance();
//        nf.setMaximumFractionDigits(2);
//        Double tNum = Double.parseDouble(nf.format(100.0 / a1Task.getTaskNum()));
////        为每位同学计算对应的分数
//        a1Task.getAnswerList().forEach(obj -> {
//            obj.setAnsScore(tNum * obj.getAnsApos());
//        });
//        return a1Task;
        // 确保 a1Task 不为 null
        if (a1Task == null) {
            throw new IllegalArgumentException("a1Task cannot be null");
        }

        // 确保任务数量不为 null 且不为 0，避免除以零的错误
        if (a1Task.getTaskNum() == null || a1Task.getTaskNum() == 0) {
            throw new IllegalArgumentException("Task number cannot be null or zero");
        }

        // 计算每道题平均百分制占分
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        Double tNum = Double.parseDouble(nf.format(100.0 / a1Task.getTaskNum()));

        // 为每位同学计算对应的分数，确保答案列表不为 null
        if (a1Task.getAnswerList() != null) {
            a1Task.getAnswerList().forEach(obj -> {
                if (obj != null) { // 确保答案对象不为 null
                    obj.setAnsScore(tNum * (obj.getAnsApos() != null ? obj.getAnsApos() : 0)); // 确保答案位置不为 null
                }
            });
        }
        return a1Task;
    }


    @Override
    public T selectStudent(T student) {
            student.setStudent(selectUserMapper.selectStudentbyOne(student.getStuId()));
        return student;
    }

    @Override
    public List<T> selectTeacher(List<T> teachers) {
        teachers.forEach(teacher -> {
            teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        });
        return teachers;
    }

    @Override
    public T selectTeacher(T teacher) {
            teacher.setTeacher(selectUserMapper.selectTeacherbyOne(teacher.getTeaId()));
        return teacher;
    }

    @Override
    public T selectInGroupStudent(T GroupStudent) {
        // 查询组内成员，并判断是否是组长
        GroupStudent.setGroup(selectUserMapper.selectGroupbyOne(GroupStudent.getGgId()));

        return GroupStudent;
    }

    @Override
    public List<T> selectInGroupStudent(List<T> GroupStudent ) {
        GroupStudent.forEach(group -> {
            // 查询已交小组名称
            group.setGroup(selectUserMapper.selectGroupbyOne(group.getGgId()));
            // 查询组内成员，并判断是否是组长
            this.setGroup(group);
        });
        return GroupStudent;
    }

//    将userId变为studentId
    @Override
    public Long selectStudentStuId(Long userId) {
        return selectUserMapper.selectStudentStuId(userId);
    }

    //    将userId变为techaerId
    @Override
    public Long selectTeacherTeaId(Long userId) {
        return selectUserMapper.selectTeacherTeaId(userId);
    }

    @Override
    public Semester selectDate(Date date) {
        return selectUserMapper.selectDate(date);
    }

    @Override
    public HashMap<String, Object> selectUndoneGroup(List<T> stuList,Long ggId) {
        List<Long> idList = new ArrayList<>();
        stuList.forEach(id -> {
            idList.add(id.getStuId());
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("doneStudets",stuList);
        map.put("unDoneStudets",selectUserMapper.selectUndoneGroup(idList,ggId));
        return map;
    }

    //    查清已做未
    @Override
    public HashMap<String, Object> selectFrequency(List<T> users , Long phtrId){
//        返回类型
        HashMap<String, Object> map = new HashMap<>();
//        查学生id
        List<Long> vo = new ArrayList<>();
        users.forEach(user -> {
            vo.add(user.getStuId());
        });
//        通过学生id获取对应的没做的名单  users数据为空的后果就是全查
//        为空不查
        List<SelectUserVo> doneStudents = selectUserMapper.selectStudents(vo);
//        为空全查
        List<SelectUserVo> undoneStudents = selectUserMapper.selectUndoneStudents(vo);
//        通过名单查出所有做了和没做的学生列表
        List<SelectUserVo> doneStudentslist = selectUserMapper.selectUserFrequencyByRtId(doneStudents,phtrId);
        List<SelectUserVo> undoneStudentslist = selectUserMapper.selectUserFrequencyByRtId(undoneStudents,phtrId);
//        放到返回类型中
        A3PhysicalTraining a3PhysicalTraining = selectUserMapper.selectStudentFrequency(phtrId);
        a3PhysicalTraining.setTeacher(selectUserMapper.selectTeacherbyOne(a3PhysicalTraining.getTeaId()));

        map.put("teaTask",a3PhysicalTraining);
        map.put("phtrId",String.valueOf(phtrId));
        map.put("doneStudents",doneStudentslist);
        map.put("undoneStudents",undoneStudentslist);
        return map;
    }
    @Override
    public HashMap<String, Object> selectFrequency(List<T> users){
//        返回类型
        HashMap<String, Object> map = new HashMap<>();
//        查学生id
        List<Long> vo = new ArrayList<>();
        users.forEach(user -> {
            vo.add(user.getStuId());
        });
//        通过学生id获取对应的做了和没做的名单
        List<SelectUserVo> undoneStudents = selectUserMapper.selectUndoneStudents(vo);
        List<SelectUserVo> doneStudents = selectUserMapper.selectStudents(vo);
//        通过对应做了和没做的名单引申出它的次数
        List<SelectUserVo> undoneStudentslist = undoneStudents;
        List<SelectUserVo> doneStudentslist = selectUserMapper.selectUserFrequency(doneStudents);
        if (undoneStudents.size() > 0) {
            undoneStudentslist = selectUserMapper.selectUserFrequency(undoneStudents);
        }
//        放到返回类型中
        map.put("doneStudents",doneStudentslist);
        map.put("undoneStudents",undoneStudentslist);

        return map;
    }


    @Override
    public HashMap<String, Object> selectGroup(List<T> groupList) {

//        controller层数据定义
        HashMap<String, Object> map = new HashMap<>();
//        所有小组id集合
        List<Long> num = new ArrayList<>();
//        未交小组集合
        List<Group> sgroups;
//      查询已交小组ID
        groupList.forEach(group -> {
            if (group.getGgId() != null)num.add(group.getGgId());
        });
//        不为空则查出本组对应未交小组
        sgroups = selectUserMapper.selectGroup(num);
//        相信后人的智慧
        sgroups.forEach(gstu -> {
            gstu.setStudentList(selectUserMapper.selectInGroupStudent(gstu.getGgId()));
            gstu.getStudentList().forEach(gro -> {
                if (gro.getId() != null)
                    if (gro.getId().equals(gstu.getStuGroupLeader())) {
                        gro.setRole("组长");
                        gstu.setStuGroupLeaderName(gro.getName());
                    } else {
                        gro.setRole("组员");
                    }
            });
        });
        map.put("doneGroup",groupList);
        map.put("undoneGroup",sgroups);
        return map;
    }

    @Override
    public HashMap selectUndoneStudent(List<T> stuList) {
        //        controller层数据定义
        HashMap<String, Object> map = new HashMap<>();
//        所有小组id集合
        List<Long> num = new ArrayList<>();
//        未交小组集合
        List<SelectUserVo> undoneStu;

        stuList.forEach(stu -> {
            if (stu.getStuId() != null)num.add(stu.getStuId());
        });
//        不为空则查出本组对应未交小组
        undoneStu = selectUserMapper.selectUndoneStudents(num);
        map.put("doneStudents",stuList);
        map.put("undoneStudents",undoneStu);
        return map;
    }

//    小组学生查询工具方法 要求，有ggid的Entity继承类
    private void setGroup(BaseEntity GroupStudent) {
        List<SelectUserVo> list = selectUserMapper.selectInGroupStudent(GroupStudent.getGgId());
//        遍历小组成员
        list.forEach(gro -> {
//            如果学生id不为空的话，判断小组id是否是组长id
            if (gro.getId() != null)
                if (gro.getId().equals(GroupStudent.getGroup().getStuGroupLeader())) {
//                    是则给其赋值组长
                    gro.setRole("组长");
//                    给最外层赋值组长名
                    GroupStudent.getGroup().setStuGroupLeaderName(gro.getName());
                } else {
//                    否则给其赋值组员
                    gro.setRole("组员");
                }
        });
//        将组员组长以及外层的小组赋值给传入数据
        GroupStudent.getGroup().setStudentList(list);
    }

    @Override
    public Long judgeDModel(DModelScore dModelScore){
//        查询D模块成绩是否有数据
        List<DModelScore> dModelScores = dModelScoreMapper.selectDModelScoreList(new DModelScore(dModelScore.getTsId()));
        if(StringUtils.isEmpty(dModelScores)){
//            没有新建一个
            dModelScoreMapper.insertDModelScore(dModelScore);
            return dModelScore.getModscId();
        }
//        有直接更改
        dModelScore.setModscId(dModelScores.get(0).getModscId());
        dModelScoreMapper.updateDModelScore(dModelScore);
        return dModelScores.get(0).getModscId();
    }

    @Override
    public BigDecimal countDModel(Long modscId) {
        DModelScore countScore = dModelScoreMapper.selectDModelScoreByModscId(modscId);
        if (StringUtils.isNotNull(countScore)) {
//            进步分
            BigDecimal progressist = countScore.getModscShoot().add(countScore.getModscDribble());
//            附加分
            BigDecimal extraPoint = countScore.getModscAdditional();
            System.out.println("进步分：" + progressist);
            System.out.println("附加分：" + extraPoint);
            return progressist.add(extraPoint);
        }
        return null;
    }

    //         通过学期和学号返回总分表数据 如果   有则返回id  没有创建并返回id
    /*
    * @Param totalScore 总成绩列表
    * */
    public Long judgeInformation(TotalScore totalScore){
//        查找总成绩列表
        List<TotalScore> totalScores = totalScoreMapper.selectTotalScoreList(totalScore);
//        判断数据库是否有数据
        if (StringUtils.isNotEmpty(totalScores)) {
//            如果有直接返回
            return totalScores.get(0).getTsId();
        }else {
//            新插入数据默认成绩为零
            totalScore.setEpScore(BigDecimal.valueOf(0));
//            插入内容
            totalScoreMapper.insertTotalScore(totalScore);
            return totalScore.getTsId();
        }
    }

//         通过学期和学号返回模块表数据 如果   有则返回id  没有创建并返回id
    /*
     * @Param moduleScore 模块成绩列表
     * */
    public ModuleScore judgeModuleScore(ModuleScore moduleScore){
//        查找总成绩列表
        BigDecimal avsScore = moduleScore.getAvsScore();
//        确保成绩不参与查询
        moduleScore.setAvsScore(null);
        List<ModuleScore> moduleScores = moduleScoreMapper.selectModuleScoreList(moduleScore);
        moduleScore.setAvsScore(avsScore);
        if (StringUtils.isNotEmpty(moduleScores)) {
//            修改查到目标Id内容
            moduleScore.setModId(moduleScores.get(0).getModId());
            moduleScoreMapper.updateModuleScore(moduleScore);
            return moduleScore;
        }else {
//            插入内容
            moduleScoreMapper.insertModuleScore(moduleScore);
            return moduleScore;
        }
    }


//    TODO: 用于增加或修改学生的A模块成绩

//        TODO: 通用方法 根据当前时间以及学生id查询学生的总分ID
    private Long selectModuleScoreList(Long stuId){
        TotalScore totalScore = new TotalScore();
    //        查找学期
        Semester semester = selectUser.selectDate(new Date());
        totalScore.setSemesterId(semester.getSemesterId());
//        给出总分表的id
        Long tsId = this.judgeInformation(totalScore);
        return tsId;

    }

//    todo: 通用方法 根据当前A模块id获总分并插入总分表
    private ModuleScore aModuleTotalScore(Long modscoId) {
// 通过 modscoId 查询 AModuleScore
        AModuleScore aModuleScore = aModuleScoreMapper.selectAModuleScoreByModscoId(modscoId);
        System.out.println(aModuleScore);
// 调用 addAll 方法，计算所有分数的总和
        BigDecimal all = aModuleScore.addAll();
// 创建一个新的 ModuleScore 对象
        ModuleScore moduleScore = new ModuleScore();
// 设置 ModuleScore 的 avsScore 为所有分数的总和
        moduleScore.setAvsScore(all);
// 设置 ModuleScore 的 tsId 为 aModuleScore 的 tsId
        moduleScore.setTsId(aModuleScore.getTsId());
// 调用 judgeModuleScore 方法，进行模块分数的处理
        ModuleScore judgeModuleScore = judgeModuleScore(moduleScore);
// 打印处理后的模块分数对象
        System.out.println(judgeModuleScore.toString());

        return judgeModuleScore;
    }

//    A1模块通用处理方法      本接口用于计算学生A1成绩
//    number 为任务总提交次数，stuNumber为学生提交次数
    @Override
    public Double A1calculationTimes(int number, int stuNumber) {
//        计算封顶为2分的学生得分
        Double maxNumber = 2.0;
//        计算学生得分
        Double maxScore = maxNumber / number;
        Double stuScore = maxScore * stuNumber;
//        保留两位小数
        BigDecimal b  =  new BigDecimal(stuScore);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
//        正常需求
//        Integer supAns = 16 + (stuNumber - number);
//        if (supAns < 0) {
//            supAns = 0;
//        } else if (supAns < 16) {
//            supAns = 1;
//        } else {
//            supAns = 2;
//        }
//        return supAns;
    }

//    A2模块通用处理方法      本接口用于计算学生A2成绩
//    excellent 为优秀次数，ordinary为普通次数
    @Override
    public Double A2calculationTimes(int excellent, int ordinary) {
//        计算封顶为2分的学生得分
        Double maxNumber = 2.0;
        Double maxScore = maxNumber / (excellent + ordinary);
//        计算优秀分数
        Double stuEScore = maxScore * excellent;
//        计算普通分数
        Double stuOScore = maxScore * ordinary;
//        计算学生得分初始得分1.4
        Double i = 1.4 + stuEScore - stuOScore;
//        最大值为2
        i = i > 2.0 ?  2.0 : i;
//        保留两位小数
        BigDecimal b  =  new BigDecimal(i);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
        return f1;
    }

//    线上学习      本接口用于修改A模块学生学习成绩表
    @Override
    public int updateStudentAScore(AModuleScore number , Long stuId) {
//        查询A模块的成绩
        AModuleScore aModuleScore = new AModuleScore();
//        插入总分ID
        Long tsId = this.selectModuleScoreList(stuId);
        aModuleScore.setTsId(tsId);
        List<AModuleScore> aModuleScores = aModuleScoreMapper.selectAModuleScoreList(aModuleScore);
//        如果没有数据则插入数据
        if (StringUtils.isEmpty(aModuleScores)) {
            int i = aModuleScoreMapper.insertAModuleScore(number);
//            计算模块分数
            ModuleScore moduleScore = aModuleTotalScore(number.getModscoId());
            TotalScore totalScore = new TotalScore();
            totalScore.setTsId(moduleScore.getTsId());
            judgeInformation(totalScore);
            return i;
        }else {
//            如果有数据则修改数据
            number.setModscoId(aModuleScores.get(0).getModscoId());
            int i = aModuleScoreMapper.updateAModuleScore(number);
// 计算模块分数
            ModuleScore moduleScore = aModuleTotalScore(number.getModscoId());
// 打印模块分数
            System.out.println(moduleScore);
// 创建一个新的总分对象
            TotalScore totalScore = new TotalScore();
// 设置总分对象的 tsId 为模块分数的 tsId
            totalScore.setTsId(moduleScore.getTsId());
// 判断总分信息是否存在，如果不存在则创建，返回总分的 tsId
            Long judged = judgeInformation(totalScore);
// 设置总分对象的 tsId 为判断后的 tsId
            totalScore.setTsId(judged);
// 设置总分对象的 EpScore 为模块分数的 avsScore
            totalScore.setEpScore(moduleScore.getAvsScore());
// 调用总分服务的 addingTotalScore 方法，增加总分
            totalScoreService.addingTotalScore(totalScore);

            return i;
        }
    }




//    TODO：标准差的计算

    public static Double standardDeviation(List<BigDecimal> msScore,int scale){
//            标准差的计算的是msScore的标准差，scale是精确位数；注意：标准差的计算公式是

//            开根号下的(Σ(Xi - Xba)²)/n-1

//            精确位数,所有除数都得弄好小数处理,否则遇到无限循环小数会抛ArithmeticException错误

//            数组个数
        int size = msScore.size();
//          算出平均值
        BigDecimal stuScore = BigDecimal.ZERO;
        for (BigDecimal score : msScore) {
            stuScore = stuScore.add(score);
        }
//            平均值得出
        BigDecimal average = stuScore.divide(BigDecimal.valueOf(size), scale, RoundingMode.HALF_UP);
//            求和
        BigDecimal summation = BigDecimal.ZERO;
        for (BigDecimal score : msScore) {
//                成绩的平方
            summation = summation.add(score.subtract(average).pow(2));

        }

//            方差的得出
        BigDecimal variance = summation.divide(BigDecimal.valueOf(size - 1), scale, RoundingMode.HALF_UP);
//            标准差的得出
        double std = Math.sqrt(variance.doubleValue());

//        测试代码
//        msScore.forEach(System.out::println);
//        System.out.println("--------------------");
//        System.out.println(variance);
//        System.out.println("--------------------");
//        System.out.println(std);
        return std;
    }


}