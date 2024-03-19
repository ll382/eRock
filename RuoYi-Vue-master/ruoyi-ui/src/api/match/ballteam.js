import request from '@/utils/request'

// 查询球队参赛列表
export function listBallteam(query) {
  return request({
    url: '/match/ballteam/list',
    method: 'get',
    params: query
  })
}

// 查询球队参赛详细
export function getBallteam(balId) {
  return request({
    url: '/match/ballteam/' + balId,
    method: 'get'
  })
}

// 新增球队参赛
export function addBallteam(data) {
  return request({
    url: '/match/ballteam',
    method: 'post',
    data: data
  })
}

// 修改球队参赛
export function updateBallteam(data) {
  return request({
    url: '/match/ballteam',
    method: 'put',
    data: data
  })
}

// 删除球队参赛
export function delBallteam(balId) {
  return request({
    url: '/match/ballteam/' + balId,
    method: 'delete'
  })
}
