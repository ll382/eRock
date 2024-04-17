import request from '@/utils/request'

// 查询A2 A3 体能成绩列表
export function listScore(query) {
  return request({
    url: '/staminaScore/score/list',
    method: 'get',
    params: query
  })
}

// 查询A2 A3 体能成绩详细
export function getScore(physId) {
  return request({
    url: '/staminaScore/score/' + physId,
    method: 'get'
  })
}

// 新增A2 A3 体能成绩
export function addScore(data) {
  return request({
    url: '/staminaScore/score',
    method: 'post',
    data: data
  })
}

// 修改A2 A3 体能成绩
export function updateScore(data) {
  return request({
    url: '/staminaScore/score',
    method: 'put',
    data: data
  })
}

// 删除A2 A3 体能成绩
export function delScore(physId) {
  return request({
    url: '/staminaScore/score/' + physId,
    method: 'delete'
  })
}
