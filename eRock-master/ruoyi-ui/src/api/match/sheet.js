import request from '@/utils/request'

// 查询C 球队内人员列表
export function listSheet(query) {
  return request({
    url: '/match/sheet/list',
    method: 'get',
    params: query
  })
}

// 查询C 球队内人员详细
export function getSheet(psId) {
  return request({
    url: '/match/sheet/' + psId,
    method: 'get'
  })
}

// 新增C 球队内人员
export function addSheet(data) {
  return request({
    url: '/match/sheet',
    method: 'post',
    data: data
  })
}

// 修改C 球队内人员
export function updateSheet(data) {
  return request({
    url: '/match/sheet',
    method: 'put',
    data: data
  })
}

// 删除C 球队内人员
export function delSheet(psId) {
  return request({
    url: '/match/sheet/' + psId,
    method: 'delete'
  })
}
