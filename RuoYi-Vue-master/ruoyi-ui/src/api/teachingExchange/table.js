import request from '@/utils/request'

// 查询A1 线上学习 教学列表
export function listTable(query) {
  return request({
    url: '/teachingExchange/table/list',
    method: 'get',
    params: query
  })
}

// 查询A1 线上学习 教学详细
export function getTable(teachingId) {
  return request({
    url: '/teachingExchange/table/' + teachingId,
    method: 'get'
  })
}

// 新增A1 线上学习 教学

export function addTable(data) {
  return request({
    url: '/teachingExchange/table',
    method: 'post',
    data: data
  })
}

// 修改A1 线上学习 教学

export function updateTable(data) {
  return request({
    url: '/teachingExchange/table',
    method: 'put',
    data: data
  })
}

// 删除A1 线上学习 教学

export function delTable(teachingId) {
  return request({
    url: '/teachingExchange/table/' + teachingId,
    method: 'delete'
  })
}
