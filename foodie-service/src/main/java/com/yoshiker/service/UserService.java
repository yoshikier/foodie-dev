package com.yoshiker.service;

import com.yoshiker.pojo.Users;
import com.yoshiker.pojo.bo.UserBo;

public interface UserService {
    /**
     * 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     */
    public Users createUser(UserBo userBo);

    /**
     * 用户登陆
     */
    public Users userLogin(UserBo userBo);
}
