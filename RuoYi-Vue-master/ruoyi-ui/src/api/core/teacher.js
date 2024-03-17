import request from '@/utils/request'

// 查询老师列表
export function listTeacher(query) {
  return request({
    url: '/core/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询老师详细
export function getTeacher(teaId) {
  return request({
    url: '/core/teacher/' + teaId,
    method: 'get'
  })
}

// 新增老师
export function addTeacher(data) {
  return request({
    url: '/core/teacher',
    method: 'post',
    data: data
  })
}

// 修改老师
export function updateTeacher(data) {
  return request({
    url: '/core/teacher',
    method: 'put',
    data: data
  })
}

// 删除老师
export function delTeacher(teaId) {
  return request({
    url: '/core/teacher/' + teaId,
    method: 'delete'
  })
}
