import request from '@/utils/request'

// 查询练习、测试任务表列表
export function listTask(query) {
  return request({
    url: '/practice/task/list',
    method: 'get',
    params: query
  })
}

// 查询练习、测试任务表详细
export function getTask(etId) {
  return request({
    url: '/practice/task/' + etId,
    method: 'get'
  })
}

// 新增练习、测试任务表
export function addTask(data) {
  return request({
    url: '/practice/task',
    method: 'post',
    data: data
  })
}

// 修改练习、测试任务表
export function updateTask(data) {
  return request({
    url: '/practice/task',
    method: 'put',
    data: data
  })
}

// 删除练习、测试任务表
export function delTask(etId) {
  return request({
    url: '/practice/task/' + etId,
    method: 'delete'
  })
}
