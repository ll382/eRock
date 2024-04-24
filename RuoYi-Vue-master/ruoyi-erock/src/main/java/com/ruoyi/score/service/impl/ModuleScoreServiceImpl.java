package com.ruoyi.score.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.score.mapper.ModuleScoreMapper;
import com.ruoyi.score.domain.ModuleScore;
import com.ruoyi.score.service.IModuleScoreService;

/**
 * ģ�����Serviceҵ��㴦��
 * 
 * @author houq
 * @date 2024-04-22
 */
@Service
public class ModuleScoreServiceImpl implements IModuleScoreService 
{
    @Autowired
    private ModuleScoreMapper moduleScoreMapper;

    /**
     * ��ѯģ�����
     * 
     * @param modId ģ���������
     * @return ģ�����
     */
    @Override
    public ModuleScore selectModuleScoreByModId(Long modId)
    {
        return moduleScoreMapper.selectModuleScoreByModId(modId);
    }

    /**
     * ��ѯģ������б�
     * 
     * @param moduleScore ģ�����
     * @return ģ�����
     */
    @Override
    public List<ModuleScore> selectModuleScoreList(ModuleScore moduleScore)
    {
        return moduleScoreMapper.selectModuleScoreList(moduleScore);
    }

    /**
     * ����ģ�����
     * 
     * @param moduleScore ģ�����
     * @return ���
     */
    @Override
    public int insertModuleScore(ModuleScore moduleScore)
    {
        return moduleScoreMapper.insertModuleScore(moduleScore);
    }

    /**
     * �޸�ģ�����
     * 
     * @param moduleScore ģ�����
     * @return ���
     */
    @Override
    public int updateModuleScore(ModuleScore moduleScore)
    {
        return moduleScoreMapper.updateModuleScore(moduleScore);
    }

    /**
     * ����ɾ��ģ�����
     * 
     * @param modIds ��Ҫɾ����ģ���������
     * @return ���
     */
    @Override
    public int deleteModuleScoreByModIds(Long[] modIds)
    {
        return moduleScoreMapper.deleteModuleScoreByModIds(modIds);
    }

    /**
     * ɾ��ģ�������Ϣ
     * 
     * @param modId ģ���������
     * @return ���
     */
    @Override
    public int deleteModuleScoreByModId(Long modId)
    {
        return moduleScoreMapper.deleteModuleScoreByModId(modId);
    }
}
