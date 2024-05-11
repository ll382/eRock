import request from '@/utils/request'

// 查询课时单元列表
export function listUnits(query) {
  return request({
    url: '/unit/units/list',
    method: 'get',
    params: query
  })
}

// 查询课时单元详细
export function getUnits(lesId) {
  return request({
    url: '/unit/units/' + lesId,
    method: 'get'
  })
}

// 新增课时单元
export function addUnits(data) {
  return request({
    url: '/unit/units',
    method: 'post',
    data: data
  })
}

// 修改课时单元
export function updateUnits(data) {
  return request({
    url: '/unit/units',
    method: 'put',
    data: data
  })
}

// 删除课时单元
export function delUnits(lesId) {
  return request({
    url: '/unit/units/' + lesId,
    method: 'delete'
  })
}
