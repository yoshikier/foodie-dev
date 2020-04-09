package com.yoshiker.controller;

import com.yoshiker.pojo.Users;
import com.yoshiker.pojo.bo.UserBo;
import com.yoshiker.service.UserService;
import com.yoshiker.utils.CookieUtils;
import com.yoshiker.utils.JsonUtils;
import com.yoshiker.utils.MD5Utils;
import com.yoshiker.utils.ToolJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "登录注册", tags = {"用于登录注册的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在" )
    @GetMapping("/queryUsernameIsExist")
    public ToolJSONResult usernameIsExist(@RequestParam String username) {

        // 判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return ToolJSONResult.errorMsg("用户名不能为空！");
        }

        // 判断用户名是否存在
        Boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ToolJSONResult.errorMsg("用户名已经存在！");
        }

        // 用户名不存在 通过
        return ToolJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册" )
    @PostMapping("/regist")
    public ToolJSONResult regist(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) {

        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();


        // 判断用户名密码是否为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return ToolJSONResult.errorMsg("用户名或密码不能为空！");
        }

        // 判断密码是否相等
        if (!password.equals(confirmPassword)) {
            return ToolJSONResult.errorMsg("两次输入的密码不一致！");
        }

        // 判断密码长度是否大于6位
        if (password.length() < 6) {
            return ToolJSONResult.errorMsg("密码长度不能少于6");
        }

        // 判断用户名是否存在
        Boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ToolJSONResult.errorMsg("用户名已经存在！");
        }

        Users user = userService.createUser(userBo);

        Users UserPropery = setNullPropety(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(UserPropery), true);

        return ToolJSONResult.ok(user);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录" )
    @PostMapping("/login")
    public ToolJSONResult login(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBo.getUsername();
        String password = userBo.getPassword();

        // 判断用户名密码是否为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ToolJSONResult.errorMsg("用户名或密码不能为空！");
        }

        userBo.setPassword(MD5Utils.getMD5Str(password));

        Users user = userService.userLogin(userBo);

        if (user == null) {
            return ToolJSONResult.errorMsg("用户名或密码错误！");
        }

        Users UserPropery = setNullPropety(user);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(UserPropery), true);

        return ToolJSONResult.ok(user);
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录" )
    @PostMapping("/logout")
    public ToolJSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {

        CookieUtils.deleteCookie(request, response, "user");

        return ToolJSONResult.ok();
    }

    // 将用户隐私属性设置为空
    private Users setNullPropety(Users user) {

        user.setRealname(null);
        user.setCreatedTime(null);
        user.setBirthday(null);
        user.setPassword(null);
        user.setEmail(null);
        user.setMobile(null);

        return user;
    }
}
