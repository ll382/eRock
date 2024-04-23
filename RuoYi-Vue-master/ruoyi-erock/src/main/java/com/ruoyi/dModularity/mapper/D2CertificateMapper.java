package com.ruoyi.dModularity.mapper;

import com.ruoyi.core.domain.Semester;
import com.ruoyi.dModularity.domain.D2Certificate;
import com.ruoyi.dModularity.domain.D2CertificateAuditByStuId;
import com.ruoyi.dModularity.domain.D2Resource;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * D2 证书表Mapper接口
 *
 * @author houq
 * @date 2024-04-16
 */
public interface D2CertificateMapper {
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
	 * 删除D2 证书表
	 *
	 * @param certificateId D2 证书表主键
	 * @return 结果
	 */
	public int deleteD2CertificateByCertificateId(Long certificateId);

	/**
	 * 批量删除D2 证书表
	 *
	 * @param certificateIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteD2CertificateByCertificateIds(Long[] certificateIds);

	/**
	 * 批量删除D2 资源
	 *
	 * @param certificateIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteD2ResourceByCertificateIds(Long[] certificateIds);

	/**
	 * 批量新增D2 资源
	 *
	 * @param d2ResourceList D2 资源列表
	 * @return 结果
	 */
	public int batchD2Resource(List<D2Resource> d2ResourceList);


	/**
	 * 通过D2 证书表主键删除D2 资源信息
	 *
	 * @param certificateId D2 证书表ID
	 * @return 结果
	 */
	public int deleteD2ResourceByCertificateId(Long certificateId);

	/**
	 * D2学生名单列表
	 *
	 * @return
	 */
	public List<D2CertificateAuditByStuId> selectD2CertificateAudit(Integer enumId);

	/**
	 * D2查询学生上传资料信息
	 *
	 * @param map
	 * @return
	 */
	public List<HashMap<String, Object>> selectD2CertificateAuditByStuId(HashMap<String, Object> map);

	// 获取学期id
	@Select("SELECT semester_id AS semesterId, start_date AS startDate , finish_date AS finishDate FROM semester WHERE #{date} BETWEEN start_date AND finish_date")
	public Semester selectDate(Date date);
}
