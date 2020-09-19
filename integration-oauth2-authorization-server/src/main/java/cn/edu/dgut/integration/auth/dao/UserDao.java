package cn.edu.dgut.integration.auth.dao;


import cn.edu.dgut.integration.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);

}
