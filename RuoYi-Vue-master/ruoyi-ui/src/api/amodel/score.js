import request from '@/utils/request'

// 查询 A 模块成绩列表
export function listScore(query) {
  return request({
    url: '/amodel/score/list',
    method: 'get',
    params: query
  })
}

// 查询 A 模块成绩详细
export function getScore(modscoId) {
  return request({
    url: '/amodel/score/' + modscoId,
    method: 'get'
  })
}

// 新增 A 模块成绩
export function addScore(data) {
  return request({
    url: '/amodel/score',
    method: 'post',
    data: data
  })
}

// 修改 A 模块成绩
export function updateScore(data) {
  return request({
    url: '/amodel/score',
    method: 'put',
    data: data
  })
}

// 删除 A 模块成绩
export function delScore(modscoId) {
  return request({
    url: '/amodel/score/' + modscoId,
    method: 'delete'
  })
}
