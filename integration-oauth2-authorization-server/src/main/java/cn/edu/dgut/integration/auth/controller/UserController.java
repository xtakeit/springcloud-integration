package cn.edu.dgut.integration.auth.controller;


import cn.edu.dgut.integration.auth.pojo.User;
import cn.edu.dgut.integration.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Boolean register(User user){
        return userService.register(user);
    }
}
