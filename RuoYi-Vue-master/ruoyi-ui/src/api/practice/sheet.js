import request from '@/utils/request'

// 查询练习、测试评分表列表
export function listSheet(query) {
  return request({
    url: '/practice/sheet/list',
    method: 'get',
    params: query
  })
}

// 查询练习、测试评分表详细
export function getSheet(msId) {
  return request({
    url: '/practice/sheet/' + msId,
    method: 'get'
  })
}

// 新增练习、测试评分表
export function addSheet(data) {
  return request({
    url: '/practice/sheet',
    method: 'post',
    data: data
  })
}

// 修改练习、测试评分表
export function updateSheet(data) {
  return request({
    url: '/practice/sheet',
    method: 'put',
    data: data
  })
}

// 删除练习、测试评分表
export function delSheet(msId) {
  return request({
    url: '/practice/sheet/' + msId,
    method: 'delete'
  })
}
