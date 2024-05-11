import request from '@/utils/request'

// 查询A2 合作学习 助教评价列表
export function listAssistant(query) {
  return request({
    url: '/teaAssistant/assistant/list',
    method: 'get',
    params: query
  })
}

// 查询A2 合作学习 助教评价详细
export function getAssistant(taId) {
  return request({
    url: '/teaAssistant/assistant/' + taId,
    method: 'get'
  })
}

// 新增A2 合作学习 助教评价

export function addAssistant(data) {
  return request({
    url: '/teaAssistant/assistant',
    method: 'post',
    data: data
  })
}

// 修改A2 合作学习 助教评价

export function updateAssistant(data) {
  return request({
    url: '/teaAssistant/assistant',
    method: 'put',
    data: data
  })
}

// 删除A2 合作学习 助教评价

export function delAssistant(taId) {
  return request({
    url: '/teaAssistant/assistant/' + taId,
    method: 'delete'
  })
}
