package com.rain.demo.service;

import com.rain.demo.dao.LoginLogDao;
import com.rain.demo.dao.UserDao;
import com.rain.demo.domain.LoginLog;
import com.rain.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rain on 17-7-16.
 */
@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    public boolean haveMatchUser(String userName, String password){
        int matchCount =userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits( 5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

    @Autowired
    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public LoginLogDao getLoginLogDao() {
        return loginLogDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
}
