import request from '@/utils/request'

// 查询学生列表
export function listStudent(query) {
  return request({
    url: '/core/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生详细
export function getStudent(stuId) {
  return request({
    url: '/core/student/' + stuId,
    method: 'get'
  })
}

// 新增学生
export function addStudent(data) {
  return request({
    url: '/core/student',
    method: 'post',
    data: data
  })
}

// 修改学生
export function updateStudent(data) {
  return request({
    url: '/core/student',
    method: 'put',
    data: data
  })
}

// 删除学生
export function delStudent(stuId) {
  return request({
    url: '/core/student/' + stuId,
    method: 'delete'
  })
}
