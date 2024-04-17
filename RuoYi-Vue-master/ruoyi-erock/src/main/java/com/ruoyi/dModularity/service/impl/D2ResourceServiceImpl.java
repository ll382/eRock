package com.ruoyi.dModularity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dModularity.mapper.D2ResourceMapper;
import com.ruoyi.dModularity.domain.D2Resource;
import com.ruoyi.dModularity.service.ID2ResourceService;

/**
 * D2 资源Service业务层处理
 * 
 * @author houq
 * @date 2024-04-16
 */
@Service
public class D2ResourceServiceImpl implements ID2ResourceService 
{
    @Autowired
    private D2ResourceMapper d2ResourceMapper;

    /**
     * 查询D2 资源
     * 
     * @param resourceId D2 资源主键
     * @return D2 资源
     */
    @Override
    public D2Resource selectD2ResourceByResourceId(Long resourceId)
    {
        return d2ResourceMapper.selectD2ResourceByResourceId(resourceId);
    }

    /**
     * 查询D2 资源列表
     * 
     * @param d2Resource D2 资源
     * @return D2 资源
     */
    @Override
    public List<D2Resource> selectD2ResourceList(D2Resource d2Resource)
    {
        return d2ResourceMapper.selectD2ResourceList(d2Resource);
    }

    /**
     * 新增D2 资源
     * 
     * @param d2Resource D2 资源
     * @return 结果
     */
    @Override
    public int insertD2Resource(D2Resource d2Resource)
    {
        return d2ResourceMapper.insertD2Resource(d2Resource);
    }

    /**
     * 修改D2 资源
     * 
     * @param d2Resource D2 资源
     * @return 结果
     */
    @Override
    public int updateD2Resource(D2Resource d2Resource)
    {
        return d2ResourceMapper.updateD2Resource(d2Resource);
    }

    /**
     * 批量删除D2 资源
     * 
     * @param resourceIds 需要删除的D2 资源主键
     * @return 结果
     */
    @Override
    public int deleteD2ResourceByResourceIds(Long[] resourceIds)
    {
        return d2ResourceMapper.deleteD2ResourceByResourceIds(resourceIds);
    }

    /**
     * 删除D2 资源信息
     * 
     * @param resourceId D2 资源主键
     * @return 结果
     */
    @Override
    public int deleteD2ResourceByResourceId(Long resourceId)
    {
        return d2ResourceMapper.deleteD2ResourceByResourceId(resourceId);
    }
}
