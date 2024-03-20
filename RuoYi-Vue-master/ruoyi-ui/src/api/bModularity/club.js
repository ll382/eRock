import request from '@/utils/request'

// 查询球队、社团训练列表
export function listClub(query) {
  return request({
    url: '/bModularity/club/list',
    method: 'get',
    params: query
  })
}

// 查询球队、社团训练详细
export function getClub(tcId) {
  return request({
    url: '/bModularity/club/' + tcId,
    method: 'get'
  })
}

// 新增球队、社团训练
export function addClub(data) {
  return request({
    url: '/bModularity/club',
    method: 'post',
    data: data
  })
}

// 修改球队、社团训练
export function updateClub(data) {
  return request({
    url: '/bModularity/club',
    method: 'put',
    data: data
  })
}

// 删除球队、社团训练
export function delClub(tcId) {
  return request({
    url: '/bModularity/club/' + tcId,
    method: 'delete'
  })
}
