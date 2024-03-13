import request from '@/utils/request'

// 查询小组列表
export function listGroup(query) {
  return request({
    url: '/userslist/group/list',
    method: 'get',
    params: query
  })
}

// 查询小组详细
export function getGroup(gId) {
  return request({
    url: '/userslist/group/' + gId,
    method: 'get'
  })
}

// 新增小组
export function addGroup(data) {
  return request({
    url: '/userslist/group',
    method: 'post',
    data: data
  })
}

// 修改小组
export function updateGroup(data) {
  return request({
    url: '/userslist/group',
    method: 'put',
    data: data
  })
}

// 删除小组
export function delGroup(gId) {
  return request({
    url: '/userslist/group/' + gId,
    method: 'delete'
  })
}
