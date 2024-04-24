package com.ruoyi.dModularity.service;

import java.util.List;
import com.ruoyi.dModularity.domain.D2Resource;

/**
 * D2 资源Service接口
 * 
 * @author houq
 * @date 2024-04-16
 */
public interface ID2ResourceService 
{
    /**
     * 查询D2 资源
     * 
     * @param resourceId D2 资源主键
     * @return D2 资源
     */
    public D2Resource selectD2ResourceByResourceId(Long resourceId);

    /**
     * 查询D2 资源列表
     * 
     * @param d2Resource D2 资源
     * @return D2 资源集合
     */
    public List<D2Resource> selectD2ResourceList(D2Resource d2Resource);

    /**
     * 新增D2 资源
     * 
     * @param d2Resource D2 资源
     * @return 结果
     */
    public int insertD2Resource(D2Resource d2Resource);

    /**
     * 修改D2 资源
     * 
     * @param d2Resource D2 资源
     * @return 结果
     */
    public int updateD2Resource(D2Resource d2Resource);

    /**
     * 批量删除D2 资源
     * 
     * @param resourceIds 需要删除的D2 资源主键集合
     * @return 结果
     */
    public int deleteD2ResourceByResourceIds(Long[] resourceIds);

    /**
     * 删除D2 资源信息
     * 
     * @param resourceId D2 资源主键
     * @return 结果
     */
    public int deleteD2ResourceByResourceId(Long resourceId);
}
