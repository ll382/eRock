import request from '@/utils/request'

// 查询A1 线上学习学生线上观看记录表列表
export function listTeachingExchange(query) {
  return request({
    url: '/teachingExchange/teachingExchange/list',
    method: 'get',
    params: query
  })
}

// 查询A1 线上学习学生线上观看记录表详细
export function getTeachingExchange(teachingId) {
  return request({
    url: '/teachingExchange/teachingExchange/' + teachingId,
    method: 'get'
  })
}

// 新增A1 线上学习学生线上观看记录表
export function addTeachingExchange(data) {
  return request({
    url: '/teachingExchange/teachingExchange',
    method: 'post',
    data: data
  })
}

// 修改A1 线上学习学生线上观看记录表
export function updateTeachingExchange(data) {
  return request({
    url: '/teachingExchange/teachingExchange',
    method: 'put',
    data: data
  })
}

// 删除A1 线上学习学生线上观看记录表
export function delTeachingExchange(teachingId) {
  return request({
    url: '/teachingExchange/teachingExchange/' + teachingId,
    method: 'delete'
  })
}
