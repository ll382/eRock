import request from '@/utils/request'

export function studentRader(query) {
    return request({
        url: `/practice/ABallExam/list`,
        method: 'get',
        params: query
    })
}

export function studentRaderDetail(enumId,stuId,shotDribble,queryDate) {
    return request({
        url: '/practice/ABallExam/' + enumId + '/' + stuId + '/' + shotDribble + '/' + queryDate,
        method: 'get',
    })
}

export function studentVideo(msId,enumId) {
    return request({
        url: '/practice/sheet/' + msId + '/' + enumId,
        method: 'get',
    })
}