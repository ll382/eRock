import request from '@/utils/request'

// 查询A2 合作学习 教师评价列表
export function listEvaluation(query) {
  return request({
    url: '/evaluate/evaluation/list',
    method: 'get',
    params: query
  })
}

// 查询A2 合作学习 教师评价详细
export function getEvaluation(taeId) {
  return request({
    url: '/evaluate/evaluation/' + taeId,
    method: 'get'
  })
}

// 新增A2 合作学习 教师评价
export function addEvaluation(data) {
  return request({
    url: '/evaluate/evaluation',
    method: 'post',
    data: data
  })
}

// 修改A2 合作学习 教师评价
export function updateEvaluation(data) {
  return request({
    url: '/evaluate/evaluation',
    method: 'put',
    data: data
  })
}

// 删除A2 合作学习 教师评价
export function delEvaluation(taeId) {
  return request({
    url: '/evaluate/evaluation/' + taeId,
    method: 'delete'
  })
}
