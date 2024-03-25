package com.ruoyi.teachingExchange.mapper;

import java.util.List;
import com.ruoyi.teachingExchange.domain.A1Communication;

/**
 * A1交流Mapper接口
 * 
 * @author heye
 * @date 2024-03-21
 */
public interface A1CommunicationMapper 
{
    /**
     * 查询A1交流
     * 
     * @param comId A1交流主键
     * @return A1交流
     */
    public A1Communication selectA1CommunicationByComId(Long comId);

    /**
     * 查询A1交流列表
     * 
     * @param a1Communication A1交流
     * @return A1交流集合
     */
    public List<A1Communication> selectA1CommunicationList(A1Communication a1Communication);

    /**
     * 新增A1交流
     * 
     * @param a1Communication A1交流
     * @return 结果
     */
    public int insertA1Communication(A1Communication a1Communication);

    /**
     * 修改A1交流
     * 
     * @param a1Communication A1交流
     * @return 结果
     */
    public int updateA1Communication(A1Communication a1Communication);

    /**
     * 删除A1交流
     * 
     * @param comId A1交流主键
     * @return 结果
     */
    public int deleteA1CommunicationByComId(Long comId);

    /**
     * 批量删除A1交流
     * 
     * @param comIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteA1CommunicationByComIds(Long[] comIds);
}
