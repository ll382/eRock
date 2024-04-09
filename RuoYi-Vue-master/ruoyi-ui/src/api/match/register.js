import request from '@/utils/request'

// 查询课堂记录列表
export function listRegister(query) {
  return request({
    url: '/match/register/list',
    method: 'get',
    params: query
  })
}

// 查询课堂记录详细
export function getRegister(crId) {
  return request({
    url: '/match/register/' + crId,
    method: 'get'
  })
}

// 新增课堂记录
export function addRegister(data) {
  return request({
    url: '/match/register',
    method: 'post',
    data: data
  })
}

// 修改课堂记录
export function updateRegister(data) {
  return request({
    url: '/match/register',
    method: 'put',
    data: data
  })
}

// 删除课堂记录
export function delRegister(crId) {
  return request({
    url: '/match/register/' + crId,
    method: 'delete'
  })
}
