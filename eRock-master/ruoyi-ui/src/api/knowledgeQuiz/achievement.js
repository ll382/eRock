import request from '@/utils/request'

// 查询A1 知识测试 学生成绩列表
export function listAchievement(query) {
  return request({
    url: '/knowledgeQuiz/achievement/list',
    method: 'get',
    params: query
  })
}

// 查询A1 知识测试 学生成绩详细
export function getAchievement(ansId) {
  return request({
    url: '/knowledgeQuiz/achievement/' + ansId,
    method: 'get'
  })
}

// 新增A1 知识测试 学生成绩
export function addAchievement(data) {
  return request({
    url: '/knowledgeQuiz/achievement',
    method: 'post',
    data: data
  })
}

// 修改A1 知识测试 学生成绩
export function updateAchievement(data) {
  return request({
    url: '/knowledgeQuiz/achievement',
    method: 'put',
    data: data
  })
}

// 删除A1 知识测试 学生成绩
export function delAchievement(ansId) {
  return request({
    url: '/knowledgeQuiz/achievement/' + ansId,
    method: 'delete'
  })
}
