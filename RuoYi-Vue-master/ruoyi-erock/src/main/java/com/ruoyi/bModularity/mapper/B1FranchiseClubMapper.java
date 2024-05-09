package com.ruoyi.bModularity.mapper;

import com.ruoyi.bModularity.domain.B1FranchiseClub;
import com.ruoyi.bModularity.domain.B1MassSource;
import com.ruoyi.bModularity.domain.B1Student;
import com.ruoyi.core.domain.Semester;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 球队、社团训练Mapper接口
 *
 * @author houq
 * @date 2024-03-27
 */
public interface B1FranchiseClubMapper {
	/**
	 * 查询球队、社团训练
	 *
	 * @param tcId 球队、社团训练主键
	 * @return 球队、社团训练
	 */
	public B1FranchiseClub selectB1FranchiseClubByTcId(Long tcId);

	/**
	 * 查询球队、社团训练列表
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 球队、社团训练集合
	 */
	public List<B1FranchiseClub> selectB1FranchiseClubList(B1FranchiseClub b1FranchiseClub);

	/**
	 * 新增球队、社团训练
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 结果
	 */
	public int insertB1FranchiseClub(B1FranchiseClub b1FranchiseClub);

	/**
	 * 修改球队、社团训练
	 *
	 * @param b1FranchiseClub 球队、社团训练
	 * @return 结果
	 */
	public int updateB1FranchiseClub(B1FranchiseClub b1FranchiseClub);

	/**
	 * 删除球队、社团训练
	 *
	 * @param tcId 球队、社团训练主键
	 * @return 结果
	 */
	public int deleteB1FranchiseClubByTcId(Long tcId);

	/**
	 * 批量删除球队、社团训练
	 *
	 * @param tcIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteB1FranchiseClubByTcIds(Long[] tcIds);

	/**
	 * 批量删除B1 资源
	 *
	 * @param tcIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteB1MassSourceByTcIds(Long[] tcIds);

	/**
	 * 批量新增B1 资源
	 *
	 * @param b1MassSourceList B1 资源列表
	 * @return 结果
	 */
	public int batchB1MassSource(List<B1MassSource> b1MassSourceList);


	/**
	 * 通过球队、社团训练主键删除B1 资源信息
	 *
	 * @param tcId 球队、社团训练ID
	 * @return 结果
	 */
	public int deleteB1MassSourceByTcId(Long tcId);

	/**
	 * 获取学生信息第一页
	 *
	 * @return 结果
	 */
	public List<B1Student> selectB1Student();

	/**
	 * 获取学生本周训练记录
	 *
	 * @param id
	 * @return
	 */
	public B1FranchiseClub getStudentThisWeekFranchise(Long id);

	/**
	 * 获取学生本周的训练分数
	 *
	 * @param id
	 * @return
	 */
	public Integer getThisWeekFranchiseNum(Long id);

	/**
	 * 获取学期id
	 */
	@Select("SELECT semester_id AS semesterId, start_date AS startDate, finish_date AS finishDate FROM semester WHERE CURDATE() BETWEEN start_date AND finish_date")
	public Semester selectSemester();

	/**
	 * 获取学期内训练次数
	 */
	@Select("SELECT COUNT(*) AS num FROM b1_franchise_club WHERE (tc_time BETWEEN #{startDate} AND #{finishDate}) AND stu_id = #{stuId} GROUP BY stu_id")
	public int countTrainScore(@Param("startDate") String startDate, @Param("finishDate") String finishDate, @Param("stuId") Long stuId);

	/**
	 * 上传后获取
	 */
	public List<B1FranchiseClub> getAfterUpload(B1FranchiseClub b1FranchiseClub);
}
