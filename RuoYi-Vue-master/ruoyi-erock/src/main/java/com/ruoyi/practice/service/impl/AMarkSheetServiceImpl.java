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
 * ��ϰ���������ֱ�Serviceҵ��㴦��
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
    List mainAfter = Arrays.asList("�۲����Ͷ����������ע�⵽���Ͷ�����߲��㡣���ģ����ǿ���һ����������⡣\n" +
            "\n" +
            "���ȣ��������������Ͷ�����ơ�վ��˫�ţ���������ƽ�⣬ϥ��΢�䣬�ֱ���Ȼ���ɣ��ⲿ��΢�������ǵ���Ͷ��ʱ������ָ����������°벿�֣�����������λ�ã��������԰�����������õ���ת�ͻ��ߡ�\n" +
            "\n" +
            "��Σ�������Ҫ��ϰͶ���������ͽǶȿ��ơ�����һЩ����ѵ�������縩�Գź������ƾ٣������ǿ�����֫������ͬʱ����ϰ��ͬ�Ƕȵ�Ͷ�����ر���Զ����Ͷ������������ܸ��õ�����Ͷ���Ƕȣ����������ʵĻ��ߡ�\n" +
            "\n" +
            "��󣬼ǵ�Ҫ��֮�Ժ����ϰ��ÿ�춼����һЩʱ�����Ͷ����ϰ���ر��ע���ߵĿ��ơ��㻹����ͨ���ۿ�רҵ��Ա�ı�����Ƶ��ѧϰ���ǵ�Ͷ�������Ͷ�����������Ľ����кܴ������\n" +
            "\n" +
            "�����ң�ֻҪ���ֲ�и����ϰ�����Ұ�����Щ�������������Ͷ�����������Ͷ������һ�����������Ƶġ����ͣ�",
            "��ע�⵽���Ͷ���������㣬����ܻ�Ӱ������ȶ��Ժ�׼ȷ�ԡ�����ģ����ǿ���ͨ��һЩѵ��������������⡣\n" +
            "\n" +
            "���ȣ������ǹ�ע����ֲ���������Ͷ��ʱ��ȷ���ֱ���ֱ��������ָ���ͷ���ʱ�����㹻����ת��������ָ�������������ת���������������ƣ��������Ը���ȷ�ص�����ĺ����Ƕȡ�\n" +
            "\n" +
            "��Σ���ϰͶ��ʱ���������ơ�ȷ����������ȶ���ϥ��΢�䣬˫��ƽ�в�վ�ȡ������ͷ���ʱ�����������������ƶ����������԰���������������������ת��\n" +
            "\n" +
            "���⣬��ǿ��ĺ�������Ҳ����Ҫ��ͨ����һЩ����ѵ������������������ƽ��֧�ţ�����ǿ��ĸ����ͱ������⡣һ���ȶ��ĺ��Ŀ��԰�������Ͷ��ʱ����ƽ��Ϳ��ơ�\n" +
            "\n" +
            "�����ϰ�ǹؼ���ÿ�컨һЩʱ����������ϰͶ�����ر��ע�����Ŀ��ơ��㻹����Ѱ��������߸��о������Ա�İ����������Ǹ���һЩ�����ָ����\n" +
            "\n" +
            "��ס����߼�����Ҫʱ������ġ������Լ��������ϰ�����Ͷ������һ�����������Ƶġ����ͣ�",
            "��ע�⵽����Ͷ��ʱ��ƫ�ǲ��㣬����ܻᵼ����ƫ��Ŀ�ꡣ�����ģ����ǿ���ͨ��һЩѵ��������������⡣\n" +
            "\n" +
            "���ȣ��������ص��ע����������ơ���Ͷ��ʱ��ȷ�����������������˫��ƽ�в�վ�ȡ�ע�Ᵽ��ϥ��΢�䣬�������԰�������õؿ���������ƽ�⡣\n" +
            "\n" +
            "��Σ��������Ͷ�����������Ž��ⲿ��׼���𣬲����ͷ���ʱ���������ƶ����������Բ������õĻ��ߺ�ƫ�ǣ��������еļ��ʡ�\n" +
            "\n" +
            "���⣬��ϰʱҪע���ֲ��Ķ�����ȷ���ֱ���ֱ����ָ���ͷ���ʱ�����㹻����������ת��������ָ����������ķ��򣬶������������ƣ��������Ը���׼�ص���ƫ�ǡ�\n" +
            "\n" +
            "��󣬼����ϰ�ǹؼ���ÿ�컨һЩʱ����������ϰͶ�����ر��עƫ�ǵĿ��ơ��㻹����Ѱ���������о������Ա�İ����������Ǹ���һЩָ���ͽ��顣\n" +
            "\n" +
            "��ס�����Ͷ��������Ҫ���ĺͳ�����Ŭ���������Լ�����ֲ�и����ϰ�����Ͷ��ƫ��һ�����������Ƶġ����ͣ�");

    List mainAnthers = Arrays.asList("��ע�⵽��������ʱ���ܻ�е������ȶ�������ܻ�Ӱ������򳡱��ֺ������ġ������ģ����ǿ���ͨ��һЩѵ��������������⡣\n" +
            "\n" +
            "���ȣ��������ص��ע������ƺ��ֲ�������������ʱ��ȷ��������屣�ֵ���̬��ϥ��΢�䣬˫�ŷֿ����ͬ�������ķ��ڽż�ͽŸ�֮�䣬��������ƽ�⡣\n" +
            "\n" +
            "��Σ���ϰ������������ͽ��ࡣ���������ƿ������������������򣬱������ɵ��ֲ���������Ҫ��̫�������ȥ�Ĵ�����������Ӱ����ƺ��ȶ��ԡ�\n" +
            "\n" +
            "���⣬��ǿ�����Ұ�͸�֪������ע��ǰ��������ͷ�����۾���ƽ�⣬ʱ�̹�ע���λ�úͶ�����ϰʱ���Գ���ʹ�����������СԲ׶���ϰ������߶���Χ�����ĸ�֪��Ӧ��������\n" +
            "\n" +
            "��󣬼����ϰ���������Ѷȡ��Ӽ򵥵Ļ�������ʼ���𽥹��ɵ������ӵ�����������ս�����ڽ��з����͵�������¼��Ľ������ҳ��Ľ��Ŀռ䡣\n" +
            "\n" +
            "��ס�������ȶ�����Ҫʱ�����������ߡ������Լ�����ֲ�и����ϰ�����������һ����������ߡ������Լ�����һ���ܹ��˷�������ѣ���Ϊ���õ���Ա�����ͣ�",
            "��֪������Ҫ���������������������ڱ����о��ܸ������ź���Ч�ؿ����򳡾��档������һЩ��������ǿ���������Ľ��飺\n" +
            "\n" +
            "���ȣ�Ҫע������������ơ����������λ��ϥ��΢�䣬˫�����ͬ�������ȶ�����������ʹ���������õ�֧�źͿ�����������\n" +
            "\n" +
            "��Σ�ע���ֲ��������������ݡ������ƿ�������ʹ�ֲ�������õز��뵽������������ϰʱ�������ø���������ƶ��򣬵���Ҫ�������ơ�������������ͬʱ�����ȶ��ԡ�\n" +
            "\n" +
            "���⣬��ǿ���ļ�Ⱥ����֫�����ѵ�������ļ�Ⱥ�����������������β����⣬�����������ȶ��Ժ��������ݵĹؼ�����֫��������֧�ź��ƶ��������Ҫ������Դ��ͨ�����Ӻ��ĺ���֫���������ѵ����������������������ȶ��Ժ�����������\n" +
            "\n" +
            "��󣬶���ϰ����ע�ؼ��ɺ������Ľ�ϡ���ÿ��ѵ���У�ע�ؼ���ϸ�ں��������ӵĽ�ϡ�ͨ��������ϰ�����������������������Ч�ʡ�\n" +
            "\n" +
            "��ס���������������һ�������Ĺ��̣���Ҫ���ĺͼ�֡������Լ�����ֲ�и��ѵ������һ���ܹ�ȡ�ý��������ͣ�",
            "��ע�⵽�������ٶȲ����죬���ģ����ǿ���ͨ��רע����ϰ�ͼ��ɸ��Ƶġ�������һЩ��������������ٶȵĽ��飺\n" +
            "\n" +
            "���ȣ�Ҫע���ֲ������Ϳ��ơ�ȷ���������ƿ������򣬶�������ָ�⡣����������߿��������ȶ��ԣ��Ӷ�ʹ���ܹ�������ƶ�����\n" +
            "\n" +
            "��Σ���ϰ�仯����Ͳ�����������ʱ����Ҫ���Ǳ�����ͬ���ٶȺͽ��ࡣ���Լӿ�ͼ���������ٶȣ��Լ��ı䲽����Ƶ�ʺͷ��������������������Ӧ�Բ�ͬ�ķ��غͳ�����\n" +
            "\n" +
            "���⣬ע�ر������ͼ���������ѵ�������б������ͼ��ٶȵ�ѵ����������һЩ���ٵĳ�̡���Ծ�ͱ�����ѵ������������������ٶȺ�ͻ���������Ӷ�ʹ���ڱ����и��������\n" +
            "\n" +
            "��󣬶����ģ�������ʵսѵ��������ϰ��ģ����������������ڸ�ѹ���͸��ٶ��½�������ѵ�����������԰�������õ���Ӧ�����е�ѹ���ͽ��࣬�����������ٶȺ�Ӧ��������\n" +
            "\n" +
            "��ס����������ٶ���Ҫ��֮�Ժ����ϰ�����ġ������Լ�����ֲ�и��ѵ������һ���ܹ�ȡ�ý��������ͣ�");

    /**
     * ��ѯ��ϰ���������ֱ�
     *
     * @param msId ��ϰ���������ֱ�����
     * @return ��ϰ���������ֱ�
     */
    @Override
    public AMarkSheet selectAMarkSheetByMsId(Long msId)
    {
        return (AMarkSheet) selectUser.selectStudent(aMarkSheetMapper.selectAMarkSheetByMsId(msId));
    }

    /**
     * ��ѯδ�ύ����
     *
     * @param etId ��ϰ���������ֱ�
     * @return ��ϰ���������ֱ�
     */
    @Override
    public List<Student> selectAMarkSheetList(Long etId)
    {
        return aMarkSheetMapper.selectAMarkSheet(etId);
    }


    /**
     * ��ѯ��ϰ���������ֱ��б�
     *
     * @param aMarkSheet ��ϰ���������ֱ�
     * @return ��ϰ���������ֱ�
     */
    @Override
    public List<AMarkSheet> selectAMarkSheetList(AMarkSheet aMarkSheet)
    {
        return selectUser.selectStudent(aMarkSheetMapper.selectAMarkSheetList(aMarkSheet));
    }

    /**
     * ��ѯPC�˵���ҳ��
     * @param msId ����Id
     * @param enumId ö��Id
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
     * ������ϰ���������ֱ�
     *
     * @param aMarkSheet ��ϰ���������ֱ�
     * @return ���
     */
    @Transactional
    @Override
    public int insertAMarkSheet(AMarkSheet aMarkSheet)
    {

//        ����ѧ��
        Semester semester = selectUser.selectDate(new Date());

//        �����ּ���
        List<BigDecimal> progressists = this.tScore(aMarkSheet,semester);

//        progressists.forEach(System.out::println);

//        ��Ͷ���֣�15ö�٣�
        BigDecimal basketScore = progressists.get(0).multiply(BigDecimal.valueOf(0.5));
//        ������֣�15ö�٣�
        BigDecimal dribbleScore = progressists.get(0).multiply(BigDecimal.valueOf(0.5));
//        ����ѧ�ں�ѧ��
        TotalScore totalScore = new TotalScore();
        totalScore.setSemesterId(semester.getSemesterId());
        totalScore.setStuId(aMarkSheet.getStuId());
//         �жϵ�ǰѧ�ں�ѧ���Ƿ����������   ���򷵻�id  û�д���������id
        Long tsId = selectUser.judgeInformation(totalScore);
//        ��ѯ��ǰ�����Ƿ����ӳɼ�
        ModuleScore moduleScore = new ModuleScore();
//        �����ܷ�ID
        moduleScore.setTsId(tsId);
//        Dģ�飨8A��9B��10C��11D��
        moduleScore.setEnumId(11L);
        moduleScore.setAvsScore(basketScore.add(dribbleScore));
//        ���ò��ҷ���
        ModuleScore score = selectUser.judgeModuleScore(moduleScore);
//        D1ģ�����
//        System.out.println("Dģ�������");
//        System.out.println(score);

//        ά��pc���ݣ����û���ֵ������ballExam����
        ABallExam ballExam = new ABallExam();

        aMarkSheet.setMsTime(new Date());

        AMarkSheet aMark = new AMarkSheet();
        aMark.setStuId(aMarkSheet.getStuId());
        aMark.setEtId(aMarkSheet.getEtId());
        if (StringUtils.isNotEmpty(aMarkSheetMapper.selectAMarkSheetList(aMark))){
            return -1;
        }
//        �ж���������������ݻ���
        if ("16".equals(aMarkSheetMapper.selectCriteria(aMarkSheet.getMsId()))){
            enumList = Arrays.asList("�ȶ���","����","�ٶ�");
            main = mainAnthers;
        }else {
            enumList = Arrays.asList("����","����","ƫ��");
            main = mainAfter;
        }
//        �༭��������
        String s = aMarkSheet.getMs1().compareTo(aMarkSheet.getMs2()) == -1 ? (aMarkSheet.getMs1().compareTo(aMarkSheet.getMs3()) == -1 ? enumList.get(0)+"��С" + aMarkSheet.getMs1() + "�֣�" + main.get(0) : enumList.get(2)+"��С" + aMarkSheet.getMs3() + "�֣�" + main.get(2)) : (aMarkSheet.getMs2().compareTo(aMarkSheet.getMs3()) == -1 ? enumList.get(1)+"��С" + aMarkSheet.getMs2() + "�֣�" + main.get(1) : enumList.get(2)+"��С" + aMarkSheet.getMs3() + "�֣�" + main.get(2));
        aMarkSheet.setMsClass("���۶��棬����Ϊ��" + s);
        int rows = aMarkSheetMapper.insertAMarkSheet(aMarkSheet);
//        ����ballExam����
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
     * �޸���ϰ���������ֱ�
     *
     * @param aMarkSheet ��ϰ���������ֱ�
     * @return ���
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
     * ����ɾ����ϰ���������ֱ�
     *
     * @param msIds ��Ҫɾ������ϰ���������ֱ�����
     * @return ���
     */
    @Transactional
    @Override
    public int deleteAMarkSheetByMsIds(Long[] msIds)
    {
        aMarkSheetMapper.deleteAExerciseResourceByMsIds(msIds);
        return aMarkSheetMapper.deleteAMarkSheetByMsIds(msIds);
    }

    /**
     * ɾ����ϰ���������ֱ���Ϣ
     *
     * @param msId ��ϰ���������ֱ�����
     * @return ���
     */
    @Transactional
    @Override
    public int deleteAMarkSheetByMsId(Long msId)
    {
        aMarkSheetMapper.deleteAExerciseResourceByMsId(msId);
        return aMarkSheetMapper.deleteAMarkSheetByMsId(msId);
    }

    /**
     * ������ϰ��Դ����Ϣ
     *
     * @param aMarkSheet ��ϰ���������ֱ����
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
//        ����Ͷ������ѭ������������ͬ�����ݲ��ҳ���
        for (Long i : new Long[]{15L, 16L}) {

//            ����Ҳ���ѧ�ڷ��ؿ�
            if (StringUtils.isEmpty(new Semester[]{semester})){
                return null;
            }
//             �鵽��ѧ�����е�����
            List<AMarkSheet> taskSheet = aMarkSheetMapper.selectClassRegisters(i, aMarkSheet.getStuId(),semester.getSemesterId());
//            System.out.println("---------------------");
//            taskSheet.forEach(System.out::println);
//            System.out.println("---------------------");
//            ͨ��ѧ��id���Ұ༶
            List<Student> students = studentMapper.selectStudentList(new Student(aMarkSheet.getStuId()));
//            ��ȡ�༶ѧ������
            Student student = students.get(0);
//            ��ѧ�ڵ�һ��ɼ�
            AMarkSheet markShe = taskSheet.get(0);
//            �����ڳ�ȫ���˵ĳɼ�ȡ��ƽ��ֵ
            Double beginSocre = aMarkSheetMapper.selectEveroneMsScore(i, semester.getSemesterId(), student.getClassId(), markShe.getMsTime());
//            ������ĩȫ���˵ĳɼ�ȡ��ƽ��ֵ
            Double endSocre = aMarkSheetMapper.selectEveroneMsScore(i, semester.getSemesterId(), student.getClassId(), null);
//            System.out.println("�ڳ�ƽ���ɼ�");
//            System.out.println(beginSocre);
//            System.out.println("��ĩƽ���ɼ�");
//            System.out.println(endSocre);
//            ����ɼ�����
            List<BigDecimal> msScore = new ArrayList<>();
            for (AMarkSheet markSheet : taskSheet) {
                msScore.add(markSheet.getMsScore());
            }
//          С������λ
            int scale = 2;

//            ��ѧ�ڵ�һ���ɼ�
            BigDecimal minMsScore = msScore.get(0);
//            ��ѧ�����һ���ɼ�
            BigDecimal maxMsScore = msScore.get(msScore.size() - 1);
//            TODO����׼��ļ���
//            �������µ�(��(Xi - Xba)?)/n-1
            Double std = SelectUserImpl.standardDeviation(msScore,scale);
            System.out.println(std);

//            TODO: T�ֵļ���
//            �ڳ�T��
            BigDecimal tBeginningScore = minMsScore.subtract(BigDecimal.valueOf(beginSocre)).divide(BigDecimal.valueOf(std),scale,RoundingMode.HALF_UP);
//            System.out.println(tBeginningScore);
//            ��ĩT��
            BigDecimal tEndScore = maxMsScore.subtract(BigDecimal.valueOf(endSocre)).divide(BigDecimal.valueOf(std),scale,RoundingMode.HALF_UP);
//            System.out.println(tEndScore);
//            T�ֲ���d1_conversion������
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
//            ѧ��ǰ��ѧ�ں����������
            Double tureProgressist = tEProgressist - tBProgressist;
            ld.add(BigDecimal.valueOf(tureProgressist));
//            System.out.println(tureProgressist);
        }
        return ld;
    }
}
