package com.ruoyi.score.mapper;

import java.util.List;
import com.ruoyi.score.domain.TotalScore;

/**
 * �ܷ�Mapper�ӿ�
 * 
 * @author houq
 * @date 2024-04-22
 */
public interface TotalScoreMapper 
{
    /**
     * ��ѯ�ܷ�
     * 
     * @param tsId �ܷ�����
     * @return �ܷ�
     */
    public TotalScore selectTotalScoreByTsId(Long tsId);

    /**
     * ��ѯ�ܷ��б�
     * 
     * @param totalScore �ܷ�
     * @return �ּܷ���
     */
    public List<TotalScore> selectTotalScoreList(TotalScore totalScore);

    /**
     * �����ܷ�
     * 
     * @param totalScore �ܷ�
     * @return ���
     */
    public int insertTotalScore(TotalScore totalScore);

    /**
     * �޸��ܷ�
     * 
     * @param totalScore �ܷ�
     * @return ���
     */
    public int updateTotalScore(TotalScore totalScore);

    /**
     * ɾ���ܷ�
     * 
     * @param tsId �ܷ�����
     * @return ���
     */
    public int deleteTotalScoreByTsId(Long tsId);

    /**
     * ����ɾ���ܷ�
     * 
     * @param tsIds ��Ҫɾ����������������
     * @return ���
     */
    public int deleteTotalScoreByTsIds(Long[] tsIds);
}
