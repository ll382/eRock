import request from '@/utils/request'

// 查询A3 学生周训资源列表
export function listTraining(query) {
  return request({
    url: '/resource/training/list',
    method: 'get',
    params: query
  })
}

// 查询A3 学生周训资源详细
export function getTraining(wetrId) {
  return request({
    url: '/resource/training/' + wetrId,
    method: 'get'
  })
}

// 新增A3 学生周训资源
export function addTraining(data) {
  return request({
    url: '/resource/training',
    method: 'post',
    data: data
  })
}

// 修改A3 学生周训资源
export function updateTraining(data) {
  return request({
    url: '/resource/training',
    method: 'put',
    data: data
  })
}

// 删除A3 学生周训资源
export function delTraining(wetrId) {
  return request({
    url: '/resource/training/' + wetrId,
    method: 'delete'
  })
}
