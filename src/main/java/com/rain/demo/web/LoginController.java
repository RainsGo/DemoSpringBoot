package com.rain.demo.web;

import com.rain.demo.domain.User;
import com.rain.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by rain on 17-7-18.
 */
@RestController
public class LoginController {
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "Welcome to RainGo Website.";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(HttpServletRequest request, LoginCommand loginCommand){
        return "Success: " + loginCommand.getUserName() + ", " + loginCommand.getPassword();
        /*
        boolean isValidUser = userService.haveMatchUser(loginCommand.getUserName(), loginCommand.getPassword());

        if(isValidUser){
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }else{
            return new ModelAndView("login", "error", "用户名或密码错误.");
        }
        */
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
