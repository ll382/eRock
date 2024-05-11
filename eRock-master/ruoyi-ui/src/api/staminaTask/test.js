import request from '@/utils/request'

// 查询A2 A3体能测试列表
export function listTest(query) {
  return request({
    url: '/staminaTask/test/list',
    method: 'get',
    params: query
  })
}

// 查询A2 A3体能测试详细
export function getTest(phyId) {
  return request({
    url: '/staminaTask/test/' + phyId,
    method: 'get'
  })
}

// 新增A2 A3体能测试
export function addTest(data) {
  return request({
    url: '/staminaTask/test',
    method: 'post',
    data: data
  })
}

// 修改A2 A3体能测试
export function updateTest(data) {
  return request({
    url: '/staminaTask/test',
    method: 'put',
    data: data
  })
}

// 删除A2 A3体能测试
export function delTest(phyId) {
  return request({
    url: '/staminaTask/test/' + phyId,
    method: 'delete'
  })
}
