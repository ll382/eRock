import request from '@/utils/request'

// 查询C 比赛记录列表
export function listRecord(query) {
  return request({
    url: '/match/record/list',
    method: 'get',
    params: query
  })
}

// 查询C 比赛记录详细
export function getRecord(ccRId) {
  return request({
    url: '/match/record/' + ccRId,
    method: 'get'
  })
}

// 新增C 比赛记录
export function addRecord(data) {
  return request({
    url: '/match/record',
    method: 'post',
    data: data
  })
}

// 修改C 比赛记录
export function updateRecord(data) {
  return request({
    url: '/match/record',
    method: 'put',
    data: data
  })
}

// 删除C 比赛记录
export function delRecord(ccRId) {
  return request({
    url: '/match/record/' + ccRId,
    method: 'delete'
  })
}
