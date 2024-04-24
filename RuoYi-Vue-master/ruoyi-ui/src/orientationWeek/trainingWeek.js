import request from '@/utils/request'

// 查询A3 体能训练周列表
export function listTrainingWeek(query) {
  return request({
    url: '/orientationWeek/trainingWeek/list',
    method: 'get',
    params: query
  })
}

// 查询A3 体能训练周详细
export function getTrainingWeek(phtrId) {
  return request({
    url: '/orientationWeek/trainingWeek/' + phtrId,
    method: 'get'
  })
}

// 新增A3 体能训练周
export function addTrainingWeek(data) {
  return request({
    url: '/orientationWeek/trainingWeek',
    method: 'post',
    data: data
  })
}

// 修改A3 体能训练周
export function updateTrainingWeek(data) {
  return request({
    url: '/orientationWeek/trainingWeek',
    method: 'put',
    data: data
  })
}

// 删除A3 体能训练周
export function delTrainingWeek(phtrId) {
  return request({
    url: '/orientationWeek/trainingWeek/' + phtrId,
    method: 'delete'
  })
}
