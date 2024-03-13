import request from '@/utils/request'

// 查询班级列表
export function listGrade(query) {
  return request({
    url: '/userslist/grade/list',
    method: 'get',
    params: query
  })
}

// 查询班级详细
export function getGrade(classId) {
  return request({
    url: '/userslist/grade/' + classId,
    method: 'get'
  })
}

// 新增班级
export function addGrade(data) {
  return request({
    url: '/userslist/grade',
    method: 'post',
    data: data
  })
}

// 修改班级
export function updateGrade(data) {
  return request({
    url: '/userslist/grade',
    method: 'put',
    data: data
  })
}

// 删除班级
export function delGrade(classId) {
  return request({
    url: '/userslist/grade/' + classId,
    method: 'delete'
  })
}
