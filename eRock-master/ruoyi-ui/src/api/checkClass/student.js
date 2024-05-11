import request from '@/utils/request'

// 查询学生名单列表
export function listStudent(query) {
  return request({
    url: '/checkClass/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生名单详细
export function getStudent(id) {
  return request({
    url: '/checkClass/student/' + id,
    method: 'get'
  })
}

// 新增学生名单
export function addStudent(data) {
  return request({
    url: '/checkClass/student',
    method: 'post',
    data: data
  })
}

// 修改学生名单
export function updateStudent(data) {
  return request({
    url: '/checkClass/student',
    method: 'put',
    data: data
  })
}

// 删除学生名单
export function delStudent(id) {
  return request({
    url: '/checkClass/student/' + id,
    method: 'delete'
  })
}
