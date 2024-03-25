import request from '@/utils/request'

// 查询A1交流列表
export function listCommunication(query) {
  return request({
    url: '/communication/communication/list',
    method: 'get',
    params: query
  })
}

// 查询A1交流详细
export function getCommunication(comId) {
  return request({
    url: '/communication/communication/' + comId,
    method: 'get'
  })
}

// 新增A1交流
export function addCommunication(data) {
  return request({
    url: '/communication/communication',
    method: 'post',
    data: data
  })
}

// 修改A1交流
export function updateCommunication(data) {
  return request({
    url: '/communication/communication',
    method: 'put',
    data: data
  })
}

// 删除A1交流
export function delCommunication(comId) {
  return request({
    url: '/communication/communication/' + comId,
    method: 'delete'
  })
}
