import request from '@/utils/request'

// 查询A1 知识测试任务列表
export function listTask(query) {
  return request({
    url: '/knowledgeQuiz/task/list',
    method: 'get',
    params: query
  })
}

// 查询A1 知识测试任务详细
export function getTask(taskId) {
  return request({
    url: '/knowledgeQuiz/task/' + taskId,
    method: 'get'
  })
}

// 新增A1 知识测试任务
export function addTask(data) {
  return request({
    url: '/knowledgeQuiz/task',
    method: 'post',
    data: data
  })
}

// 修改A1 知识测试任务
export function updateTask(data) {
  return request({
    url: '/knowledgeQuiz/task',
    method: 'put',
    data: data
  })
}

// 删除A1 知识测试任务
export function delTask(taskId) {
  return request({
    url: '/knowledgeQuiz/task/' + taskId,
    method: 'delete'
  })
}
