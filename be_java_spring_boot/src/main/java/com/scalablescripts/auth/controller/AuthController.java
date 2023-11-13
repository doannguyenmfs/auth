package com.scalablescripts.auth.controller;

import com.scalablescripts.auth.exporter.ErrorExporter;
import com.scalablescripts.auth.log.MyLog;
import com.scalablescripts.auth.mapper.ErrorMapper;
import com.scalablescripts.auth.mapper.UserMapper;
import com.scalablescripts.auth.model.Error;
import com.scalablescripts.auth.model.Token;
import com.scalablescripts.auth.model.User;
import com.scalablescripts.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AuthController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ErrorMapper errorMapper;

    private UserService userService = new UserService();

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping(value = "/login")
    public Token login(@RequestBody User reqUser, HttpServletResponse response) {
        var resUser = userMapper.getUser(reqUser.getId());

        String accessToken = "accessToken_23423jrb2j3grsdfds";
        String refreshToken = "refreshToken_%d";

        Cookie cookie = new Cookie("refresh_token", String.format(refreshToken, reqUser.getId()));
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");

        response.addCookie(cookie);

        return new Token("default_token");
    }

    @PostMapping(value = "/get_error")
    public ArrayList<Error> getError(HttpServletRequest request) {
        Cookie cookie = request.getCookies()[0];
        int userId = Integer.parseInt(cookie.getValue().split("_")[1]);

        ArrayList<Error> errors = errorMapper.getError(userId);

        return errors;
    }

    @GetMapping(value = "/insert_error")
    public void insertError(HttpServletRequest request) {
        errorMapper.insertError("1", "2", 1);
    }

    @CrossOrigin
    @GetMapping(value = "download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("appication/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=error.xlsx";

        response.setHeader(headerKey, headerValue);

        int userId = 1;
        ArrayList<Error> errors = errorMapper.getError(userId);

        ErrorExporter errorExporter = new ErrorExporter(errors);
        errorExporter.export(response);
    }
}
