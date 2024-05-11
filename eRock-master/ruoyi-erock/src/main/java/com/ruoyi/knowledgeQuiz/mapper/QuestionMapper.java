package com.ruoyi.knowledgeQuiz.mapper;

import java.util.List;
import java.util.Set;

import com.ruoyi.knowledgeQuiz.domain.Question;
import com.ruoyi.knowledgeQuiz.domain.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * A1 知识测试 问Mapper接口
 *
 * @author ljy
 * @date 2024-03-18
 */
public interface QuestionMapper
{
    /**
     * 查询A1 知识测试 问
     *
     * @param qqId A1 知识测试 问主键
     * @return A1 知识测试 问
     */
    public Question selectQuestionByQqId(Long qqId);

    @Select("select qq_id from a1_question")
    public List<Integer> selectQuestionByMax();

//    用集合搜题
    public List<Question> selectQuestionByList(@Param("taskList") List<Integer> taskList);

    /**
     * 查询A1 知识测试 问
     *
     * @param qqId A1 知识测试 问主键
     * @return A1 知识测试 问
     */
    public Question selectQuestionByQqIdList(Long qqId);

    /**
     * 查询A1 知识测试 问列表
     *
     * @param question A1 知识测试 问
     * @return A1 知识测试 问集合
     */
    public List<Question> selectQuestionList(Question question);

    /**
     * 新增A1 知识测试 问
     *
     * @param question A1 知识测试 问
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改A1 知识测试 问
     *
     * @param question A1 知识测试 问
     * @return 结果
     */
    public int updateQuestion(Question question);

    /**
     * 删除A1 知识测试 问
     *
     * @param qqId A1 知识测试 问主键
     * @return 结果
     */
    public int deleteQuestionByQqId(Long qqId);

    /**
     * 批量删除A1 知识测试 问
     *
     * @param qqIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionByQqIds(Long[] qqIds);

    /**
     * 批量删除A1 知识测试 答
     *
     * @param qqIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResultByQqIds(Long[] qqIds);

    /**
     * 批量新增A1 知识测试 答
     *
     * @param resultList A1 知识测试 答列表
     * @return 结果
     */
    public int batchResult(List<Result> resultList);


    /**
     * 通过A1 知识测试 问主键删除A1 知识测试 答信息
     *
     * @param qqId A1 知识测试 问ID
     * @return 结果
     */
    public int deleteResultByQqId(Long qqId);
}
