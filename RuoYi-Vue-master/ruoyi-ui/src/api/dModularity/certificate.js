import request from '@/utils/request'

// 查询D2 证书表列表
export function listCertificate(query) {
  return request({
    url: '/dModularity/certificate/list',
    method: 'get',
    params: query
  })
}

// 查询D2 证书表详细
export function getCertificate(certificateId) {
  return request({
    url: '/dModularity/certificate/' + certificateId,
    method: 'get'
  })
}

// 新增D2 证书表
export function addCertificate(data) {
  return request({
    url: '/dModularity/certificate',
    method: 'post',
    data: data
  })
}

// 修改D2 证书表
export function updateCertificate(data) {
  return request({
    url: '/dModularity/certificate',
    method: 'put',
    data: data
  })
}

// 删除D2 证书表
export function delCertificate(certificateId) {
  return request({
    url: '/dModularity/certificate/' + certificateId,
    method: 'delete'
  })
}
