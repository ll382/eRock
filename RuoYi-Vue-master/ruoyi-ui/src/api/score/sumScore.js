import request from '@/utils/request'

// 查询总分列表
export function listSumScore(query) {
  return request({
    url: '/score/sumScore/list',
    method: 'get',
    params: query
  })
}

// 查询总分详细
export function getSumScore(tsId) {
  return request({
    url: '/score/sumScore/' + tsId,
    method: 'get'
  })
}

// 新增总分
export function addSumScore(data) {
  return request({
    url: '/score/sumScore',
    method: 'post',
    data: data
  })
}

// 修改总分
export function updateSumScore(data) {
  return request({
    url: '/score/sumScore',
    method: 'put',
    data: data
  })
}

// 删除总分
export function delSumScore(tsId) {
  return request({
    url: '/score/sumScore/' + tsId,
    method: 'delete'
  })
}
