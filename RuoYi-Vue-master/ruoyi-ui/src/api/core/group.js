import request from '@/utils/request'

// 查询小组列表
export function listGroup(query) {
  return request({
    url: '/core/group/list',
    method: 'get',
    params: query
  })
}

// 查询小组详细
export function getGroup(ggId) {
  return request({
    url: '/core/group/' + ggId,
    method: 'get'
  })
}

// 新增小组
export function addGroup(data) {
  return request({
    url: '/core/group',
    method: 'post',
    data: data
  })
}

// 修改小组
export function updateGroup(data) {
  return request({
    url: '/core/group',
    method: 'put',
    data: data
  })
}

// 删除小组
export function delGroup(ggId) {
  return request({
    url: '/core/group/' + ggId,
    method: 'delete'
  })
}
