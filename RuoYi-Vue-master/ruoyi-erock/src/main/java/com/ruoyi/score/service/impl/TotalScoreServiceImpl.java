package com.ruoyi.score.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.score.mapper.TotalScoreMapper;
import com.ruoyi.score.domain.TotalScore;
import com.ruoyi.score.service.ITotalScoreService;

/**
 * �ܷ�Serviceҵ��㴦��
 * 
 * @author houq
 * @date 2024-04-22
 */
@Service
public class TotalScoreServiceImpl implements ITotalScoreService 
{
    @Autowired
    private TotalScoreMapper totalScoreMapper;

    /**
     * ��ѯ�ܷ�
     * 
     * @param tsId �ܷ�����
     * @return �ܷ�
     */
    @Override
    public TotalScore selectTotalScoreByTsId(Long tsId)
    {
        return totalScoreMapper.selectTotalScoreByTsId(tsId);
    }

    /**
     * ��ѯ�ܷ��б�
     * 
     * @param totalScore �ܷ�
     * @return �ܷ�
     */
    @Override
    public List<TotalScore> selectTotalScoreList(TotalScore totalScore)
    {
        return totalScoreMapper.selectTotalScoreList(totalScore);
    }

    /**
     * �����ܷ�
     * 
     * @param totalScore �ܷ�
     * @return ���
     */
    @Override
    public int insertTotalScore(TotalScore totalScore)
    {
        return totalScoreMapper.insertTotalScore(totalScore);
    }

    /**
     * �޸��ܷ�
     * 
     * @param totalScore �ܷ�
     * @return ���
     */
    @Override
    public int updateTotalScore(TotalScore totalScore)
    {
        return totalScoreMapper.updateTotalScore(totalScore);
    }

    /**
     * ����ɾ���ܷ�
     * 
     * @param tsIds ��Ҫɾ�����ܷ�����
     * @return ���
     */
    @Override
    public int deleteTotalScoreByTsIds(Long[] tsIds)
    {
        return totalScoreMapper.deleteTotalScoreByTsIds(tsIds);
    }

    /**
     * ɾ���ܷ���Ϣ
     * 
     * @param tsId �ܷ�����
     * @return ���
     */
    @Override
    public int deleteTotalScoreByTsId(Long tsId)
    {
        return totalScoreMapper.deleteTotalScoreByTsId(tsId);
    }
}
