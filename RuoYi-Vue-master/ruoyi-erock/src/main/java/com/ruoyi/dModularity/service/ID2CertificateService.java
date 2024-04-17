package com.ruoyi.dModularity.service;

import java.util.List;
import com.ruoyi.dModularity.domain.D2Certificate;

/**
 * D2 证书表Service接口
 * 
 * @author houq
 * @date 2024-04-16
 */
public interface ID2CertificateService 
{
    /**
     * 查询D2 证书表
     * 
     * @param certificateId D2 证书表主键
     * @return D2 证书表
     */
    public D2Certificate selectD2CertificateByCertificateId(Long certificateId);

    /**
     * 查询D2 证书表列表
     * 
     * @param d2Certificate D2 证书表
     * @return D2 证书表集合
     */
    public List<D2Certificate> selectD2CertificateList(D2Certificate d2Certificate);

    /**
     * 新增D2 证书表
     * 
     * @param d2Certificate D2 证书表
     * @return 结果
     */
    public int insertD2Certificate(D2Certificate d2Certificate);

    /**
     * 修改D2 证书表
     * 
     * @param d2Certificate D2 证书表
     * @return 结果
     */
    public int updateD2Certificate(D2Certificate d2Certificate);

    /**
     * 批量删除D2 证书表
     * 
     * @param certificateIds 需要删除的D2 证书表主键集合
     * @return 结果
     */
    public int deleteD2CertificateByCertificateIds(Long[] certificateIds);

    /**
     * 删除D2 证书表信息
     * 
     * @param certificateId D2 证书表主键
     * @return 结果
     */
    public int deleteD2CertificateByCertificateId(Long certificateId);
}
