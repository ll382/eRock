import request from '@/utils/request'

// 查询D 模块成绩列表
export function listDScore(query) {
  return request({
    url: '/score/dScore/list',
    method: 'get',
    params: query
  })
}

// 查询D 模块成绩详细
export function getDScore(modscId) {
  return request({
    url: '/score/dScore/' + modscId,
    method: 'get'
  })
}

// 新增D 模块成绩
export function addDScore(data) {
  return request({
    url: '/score/dScore',
    method: 'post',
    data: data
  })
}

// 修改D 模块成绩
export function updateDScore(data) {
  return request({
    url: '/score/dScore',
    method: 'put',
    data: data
  })
}

// 删除D 模块成绩
export function delDScore(modscId) {
  return request({
    url: '/score/dScore/' + modscId,
    method: 'delete'
  })
}
