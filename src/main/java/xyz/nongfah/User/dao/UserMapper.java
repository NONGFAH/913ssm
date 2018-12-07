package xyz.nongfah.User.dao;


import base.annotation.MybatisDao;
import xyz.nongfah.User.model.User;

import java.util.List;

@MybatisDao
public interface UserMapper {
    List<User> select();
    Integer isNameExist(String name);
    Integer State(String name);
    Integer unableLogin(User user);
    void logon(User user);

}
