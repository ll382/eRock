import request from '@/utils/request'

// 查询模块分数列表
export function listScore(query) {
  return request({
    url: '/score/score/list',
    method: 'get',
    params: query
  })
}

// 查询模块分数详细
export function getScore(modId) {
  return request({
    url: '/score/score/' + modId,
    method: 'get'
  })
}

// 新增模块分数
export function addScore(data) {
  return request({
    url: '/score/score',
    method: 'post',
    data: data
  })
}

// 修改模块分数
export function updateScore(data) {
  return request({
    url: '/score/score',
    method: 'put',
    data: data
  })
}

// 删除模块分数
export function delScore(modId) {
  return request({
    url: '/score/score/' + modId,
    method: 'delete'
  })
}
