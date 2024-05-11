import request from '@/utils/request'

// 查询A1 知识测试 问列表
export function listQuestion(query) {
  return request({
    url: '/knowledgeQuiz/question/list',
    method: 'get',
    params: query
  })
}

// 查询A1 知识测试 问详细
export function getQuestion(qqId) {
  return request({
    url: '/knowledgeQuiz/question/' + qqId,
    method: 'get'
  })
}

// 新增A1 知识测试 问
export function addQuestion(data) {
  return request({
    url: '/knowledgeQuiz/question',
    method: 'post',
    data: data
  })
}

// 修改A1 知识测试 问
export function updateQuestion(data) {
  return request({
    url: '/knowledgeQuiz/question',
    method: 'put',
    data: data
  })
}

// 删除A1 知识测试 问
export function delQuestion(qqId) {
  return request({
    url: '/knowledgeQuiz/question/' + qqId,
    method: 'delete'
  })
}
