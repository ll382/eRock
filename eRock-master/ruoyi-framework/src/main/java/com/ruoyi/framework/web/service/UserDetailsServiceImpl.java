package com.ruoyi.framework.web.service;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.core.service.SelectUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.system.service.ISysUserService;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SelectUser selectUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException(MessageUtils.message("user.blocked"));
        }

// 获取用户角色ID，可能为null
        Long roleId = user.getRoleId();

        if ("0".equals(LoginBody.loginStatus))
        {
            Long aLong = selectUser.selectTeacherTeaId(user.getUserId());
            user.seteRockId(aLong);

            // 判断用老师端登录的是不是老师
            if (roleId != null && 101 == roleId.longValue()) {
                log.info("登录用户为学生：{} 登录错误，请移步至学生端.", username);
                throw new BaseException("不存在该老师账号：" + username);
            }
        }
        else if ("1".equals(LoginBody.loginStatus))
        {
            Long aLong = selectUser.selectStudentStuId(user.getUserId());
            user.seteRockId(aLong);

            // 判断用学生端登录的是不是学生
            if (roleId != null && 100 == roleId.longValue()) {
                log.info("登录用户为老师：{} 登录错误，请移步至老师端.", username);
                throw new BaseException("不存在该学生账号：" + username);
            }
        }

        passwordService.validate(user);

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
