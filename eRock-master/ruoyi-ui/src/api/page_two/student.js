import request from '@/utils/request'

export function studentClassBefore(stuId,num,queryHour) {
    return request({
        url: "/core/student/getDeduplicationCrDateList/" + stuId + '/' + num + '/' + queryHour,
        method: 'get'
    })
}

export function studentClassMid(stuId,queryDate,shotDribble) {
    return request({
        url: "/core/student/getStudentAchievementByStuId/" + stuId + '/' + queryDate + '/' + shotDribble,
        mtehod: 'get'
    })
}

export function studentClassAfter(stuId,num,queryHour) {
    return request({
        url: "/core/student/getDeduplicationCrDateList/" + stuId + '/' + num + '/' + queryHour,
        mtehod: 'get'
    })
}