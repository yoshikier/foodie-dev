package com.yoshiker.service.impl;
import com.yoshiker.enums.Sex;
import com.yoshiker.mapper.UsersMapper;
import com.yoshiker.pojo.Users;
import com.yoshiker.pojo.bo.UserBo;
import com.yoshiker.service.UserService;
import com.yoshiker.utils.DateUtil;
import com.yoshiker.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584594743806&di=f3a7a8897e1908d76a21e889380a1a9c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20200306%2F2513480353e949f0abc39a3ac2a0e91b.jpeg";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        Users result = usersMapper.selectOneByExample(userExample);


        return result == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {

        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBo.username);
        user.setNickname(userBo.username);
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1900-01-02"));
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users userLogin(UserBo userBo) {

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", userBo.username);
        userCriteria.andEqualTo("password", userBo.password);
        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }
}
