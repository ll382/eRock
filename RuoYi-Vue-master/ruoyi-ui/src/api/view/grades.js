import request from '@/utils/request'

// 查询学生成绩视图列表
export function listGrades(query) {
  return request({
    url: '/view/grades/list',
    method: 'get',
    params: query
  })
}

// 查询学生成绩视图详细
export function getGrades(stuId) {
  return request({
    url: '/view/grades/' + stuId,
    method: 'get'
  })
}

// 新增学生成绩视图
export function addGrades(data) {
  return request({
    url: '/view/grades',
    method: 'post',
    data: data
  })
}

// 修改学生成绩视图
export function updateGrades(data) {
  return request({
    url: '/view/grades',
    method: 'put',
    data: data
  })
}

// 删除学生成绩视图
export function delGrades(stuId) {
  return request({
    url: '/view/grades/' + stuId,
    method: 'delete'
  })
}
