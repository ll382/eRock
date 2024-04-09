package com.ruoyi.core.service.impl;

import com.ruoyi.core.domain.Student;
import com.ruoyi.core.service.SelectUser;
import com.ruoyi.practice.mapper.SelectUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author heye
 * @Date 2024/4/9
 */
@Service
public class SelectUserImpl implements SelectUser {
    @Autowired
    SelectUserMapper selectUserMapper;

    @Override
    public Student selectUser(Long stuId){
        return selectUserMapper.selectUser(stuId);
    }
}
