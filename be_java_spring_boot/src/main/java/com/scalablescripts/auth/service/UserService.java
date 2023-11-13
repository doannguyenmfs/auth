package com.scalablescripts.auth.service;

import com.scalablescripts.auth.log.MyLog;
import com.scalablescripts.auth.mapper.UserMapper;
import com.scalablescripts.auth.model.Token;
import com.scalablescripts.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;

public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Token login(User reqUser) {
        var resUser = userMapper.getUser(reqUser.getId());

        String accessToken = "accessToken";
        String refreshToken = "refreshToken";

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");

        return new Token(accessToken);
    }
}
