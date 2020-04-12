package com.xjtu.happy.ticket.service.login;

import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.mapper.login.loginMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class LoginService {
    @Resource
    loginMapper loginMapper;
    public boolean Identity(String userName, String password){
        if(loginMapper.Identity(userName, password)!=null){
         return true;
        }
        else {
            return false;
        }
    }
    public boolean Check(String userName){
        if(loginMapper.Check(userName)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public  boolean FindByname(String name) {
        if(loginMapper.FindByname(name)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public User GetUserByname(String name) {
        return loginMapper.FindByname(name);
    }
}
