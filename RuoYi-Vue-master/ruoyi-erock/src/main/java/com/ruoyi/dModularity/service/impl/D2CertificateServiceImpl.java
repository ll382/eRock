package com.ruoyi.dModularity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.dModularity.domain.D2Resource;
import com.ruoyi.dModularity.mapper.D2CertificateMapper;
import com.ruoyi.dModularity.domain.D2Certificate;
import com.ruoyi.dModularity.service.ID2CertificateService;

/**
 * D2 证书表Service业务层处理
 * 
 * @author houq
 * @date 2024-04-16
 */
@Service
public class D2CertificateServiceImpl implements ID2CertificateService 
{
    @Autowired
    private D2CertificateMapper d2CertificateMapper;

    /**
     * 查询D2 证书表
     * 
     * @param certificateId D2 证书表主键
     * @return D2 证书表
     */
    @Override
    public D2Certificate selectD2CertificateByCertificateId(Long certificateId)
    {
        return d2CertificateMapper.selectD2CertificateByCertificateId(certificateId);
    }

    /**
     * 查询D2 证书表列表
     * 
     * @param d2Certificate D2 证书表
     * @return D2 证书表
     */
    @Override
    public List<D2Certificate> selectD2CertificateList(D2Certificate d2Certificate)
    {
        return d2CertificateMapper.selectD2CertificateList(d2Certificate);
    }

    /**
     * 新增D2 证书表
     * 
     * @param d2Certificate D2 证书表
     * @return 结果
     */
    @Transactional
    @Override
    public int insertD2Certificate(D2Certificate d2Certificate)
    {
        int rows = d2CertificateMapper.insertD2Certificate(d2Certificate);
        insertD2Resource(d2Certificate);
        return rows;
    }

    /**
     * 修改D2 证书表
     * 
     * @param d2Certificate D2 证书表
     * @return 结果
     */
    @Transactional
    @Override
    public int updateD2Certificate(D2Certificate d2Certificate)
    {
        d2CertificateMapper.deleteD2ResourceByCertificateId(d2Certificate.getCertificateId());
        insertD2Resource(d2Certificate);
        return d2CertificateMapper.updateD2Certificate(d2Certificate);
    }

    /**
     * 批量删除D2 证书表
     * 
     * @param certificateIds 需要删除的D2 证书表主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteD2CertificateByCertificateIds(Long[] certificateIds)
    {
        d2CertificateMapper.deleteD2ResourceByCertificateIds(certificateIds);
        return d2CertificateMapper.deleteD2CertificateByCertificateIds(certificateIds);
    }

    /**
     * 删除D2 证书表信息
     * 
     * @param certificateId D2 证书表主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteD2CertificateByCertificateId(Long certificateId)
    {
        d2CertificateMapper.deleteD2ResourceByCertificateId(certificateId);
        return d2CertificateMapper.deleteD2CertificateByCertificateId(certificateId);
    }

    /**
     * 新增D2 资源信息
     * 
     * @param d2Certificate D2 证书表对象
     */
    public void insertD2Resource(D2Certificate d2Certificate)
    {
        List<D2Resource> d2ResourceList = d2Certificate.getD2ResourceList();
        Long certificateId = d2Certificate.getCertificateId();
        if (StringUtils.isNotNull(d2ResourceList))
        {
            List<D2Resource> list = new ArrayList<D2Resource>();
            for (D2Resource d2Resource : d2ResourceList)
            {
                d2Resource.setCertificateId(certificateId);
                list.add(d2Resource);
            }
            if (list.size() > 0)
            {
                d2CertificateMapper.batchD2Resource(list);
            }
        }
    }
}
