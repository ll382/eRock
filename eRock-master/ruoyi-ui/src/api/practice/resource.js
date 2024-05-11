import request from '@/utils/request'

// 查询练习资源表列表
export function listResource(query) {
  return request({
    url: '/practice/resource/list',
    method: 'get',
    params: query
  })
}

// 查询练习资源表详细
export function getResource(erId) {
  return request({
    url: '/practice/resource/' + erId,
    method: 'get'
  })
}

// 新增练习资源表
export function addResource(data) {
  return request({
    url: '/practice/resource',
    method: 'post',
    data: data
  })
}

// 修改练习资源表
export function updateResource(data) {
  return request({
    url: '/practice/resource',
    method: 'put',
    data: data
  })
}

// 删除练习资源表
export function delResource(erId) {
  return request({
    url: '/practice/resource/' + erId,
    method: 'delete'
  })
}
