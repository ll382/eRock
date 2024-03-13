import request from '@/utils/request'

// 查询老师列表
export function listTeacher(query) {
  return request({
    url: '/userslist/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询老师详细
export function getTeacher(teaId) {
  return request({
    url: '/userslist/teacher/' + teaId,
    method: 'get'
  })
}

// 新增老师
export function addTeacher(data) {
  return request({
    url: '/userslist/teacher',
    method: 'post',
    data: data
  })
}

// 修改老师
export function updateTeacher(data) {
  return request({
    url: '/userslist/teacher',
    method: 'put',
    data: data
  })
}

// 删除老师
export function delTeacher(teaId) {
  return request({
    url: '/userslist/teacher/' + teaId,
    method: 'delete'
  })
}
