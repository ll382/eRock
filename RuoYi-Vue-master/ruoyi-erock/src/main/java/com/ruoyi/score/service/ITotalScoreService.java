package com.ruoyi.score.service;

import java.util.List;
import com.ruoyi.score.domain.TotalScore;

/**
 * �ܷ�Service�ӿ�
 * 
 * @author houq
 * @date 2024-04-22
 */
public interface ITotalScoreService 
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
     * ����ɾ���ܷ�
     * 
     * @param tsIds ��Ҫɾ�����ܷ���������
     * @return ���
     */
    public int deleteTotalScoreByTsIds(Long[] tsIds);

    /**
     * ɾ���ܷ���Ϣ
     * 
     * @param tsId �ܷ�����
     * @return ���
     */
    public int deleteTotalScoreByTsId(Long tsId);
}
