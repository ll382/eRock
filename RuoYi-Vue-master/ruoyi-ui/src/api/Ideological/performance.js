import request from '@/utils/request'

// 查询A2  思政教育 思政现列表
export function listPerformance(query) {
  return request({
    url: '/Ideological/performance/list',
    method: 'get',
    params: query
  })
}

// 查询A2  思政教育 思政现详细
export function getPerformance(ipId) {
  return request({
    url: '/Ideological/performance/' + ipId,
    method: 'get'
  })
}

// 新增A2  思政教育 思政现
export function addPerformance(data) {
  return request({
    url: '/Ideological/performance',
    method: 'post',
    data: data
  })
}

// 修改A2  思政教育 思政现
export function updatePerformance(data) {
  return request({
    url: '/Ideological/performance',
    method: 'put',
    data: data
  })
}

// 删除A2  思政教育 思政现
export function delPerformance(ipId) {
  return request({
    url: '/Ideological/performance/' + ipId,
    method: 'delete'
  })
}
