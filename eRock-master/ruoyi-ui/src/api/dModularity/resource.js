import request from '@/utils/request'

// 查询D2 资源列表
export function listResource(query) {
  return request({
    url: '/dModularity/resource/list',
    method: 'get',
    params: query
  })
}

// 查询D2 资源详细
export function getResource(resourceId) {
  return request({
    url: '/dModularity/resource/' + resourceId,
    method: 'get'
  })
}

// 新增D2 资源
export function addResource(data) {
  return request({
    url: '/dModularity/resource',
    method: 'post',
    data: data
  })
}

// 修改D2 资源
export function updateResource(data) {
  return request({
    url: '/dModularity/resource',
    method: 'put',
    data: data
  })
}

// 删除D2 资源
export function delResource(resourceId) {
  return request({
    url: '/dModularity/resource/' + resourceId,
    method: 'delete'
  })
}
