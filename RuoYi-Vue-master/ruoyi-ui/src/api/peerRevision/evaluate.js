import request from '@/utils/request'

// 查询A2 合作学习 学生互评列表
export function listEvaluate(query) {
  return request({
    url: '/peerRevision/evaluate/list',
    method: 'get',
    params: query
  })
}

// 查询A2 合作学习 学生互评详细
export function getEvaluate(seId) {
  return request({
    url: '/peerRevision/evaluate/' + seId,
    method: 'get'
  })
}

// 新增A2 合作学习 学生互评
export function addEvaluate(data) {
  return request({
    url: '/peerRevision/evaluate',
    method: 'post',
    data: data
  })
}

// 修改A2 合作学习 学生互评
export function updateEvaluate(data) {
  return request({
    url: '/peerRevision/evaluate',
    method: 'put',
    data: data
  })
}

// 删除A2 合作学习 学生互评
export function delEvaluate(seId) {
  return request({
    url: '/peerRevision/evaluate/' + seId,
    method: 'delete'
  })
}
