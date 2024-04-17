import request from '@/utils/request'

// 查询A2 课堂表现classRegistercr         列表
export function listAttendance(query) {
  return request({
    url: '/performance/attendance/list',
    method: 'get',
    params: query
  })
}

// 查询A2 课堂表现classRegistercr         详细
export function getAttendance(aaId) {
  return request({
    url: '/performance/attendance/' + aaId,
    method: 'get'
  })
}

// 新增A2 课堂表现classRegistercr         
export function addAttendance(data) {
  return request({
    url: '/performance/attendance',
    method: 'post',
    data: data
  })
}

// 修改A2 课堂表现classRegistercr         
export function updateAttendance(data) {
  return request({
    url: '/performance/attendance',
    method: 'put',
    data: data
  })
}

// 删除A2 课堂表现classRegistercr         
export function delAttendance(aaId) {
  return request({
    url: '/performance/attendance/' + aaId,
    method: 'delete'
  })
}
