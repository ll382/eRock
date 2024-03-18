import request from '@/utils/request'

// 查询A1 知识测试 答列表
export function listAnswer(query) {
  return request({
    url: '/knowledgeQuiz/answer/list',
    method: 'get',
    params: query
  })
}

// 查询A1 知识测试 答详细
export function getAnswer(ooId) {
  return request({
    url: '/knowledgeQuiz/answer/' + ooId,
    method: 'get'
  })
}

// 新增A1 知识测试 答
export function addAnswer(data) {
  return request({
    url: '/knowledgeQuiz/answer',
    method: 'post',
    data: data
  })
}

// 修改A1 知识测试 答
export function updateAnswer(data) {
  return request({
    url: '/knowledgeQuiz/answer',
    method: 'put',
    data: data
  })
}

// 删除A1 知识测试 答
export function delAnswer(ooId) {
  return request({
    url: '/knowledgeQuiz/answer/' + ooId,
    method: 'delete'
  })
}
