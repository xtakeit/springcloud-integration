package cn.edu.dgut.integration.auth.service;

import cn.edu.dgut.integration.auth.dao.UserDao;
import cn.edu.dgut.integration.auth.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private PasswordEncoder bCryptPasswordEncoder;

    public Boolean register(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Integer row = userDao.insert(user);
        if(row == 1){
            return true;
        }
        return false;
    }

}
