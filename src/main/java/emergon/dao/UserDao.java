package emergon.dao;

import emergon.entity.MyUser;

public interface UserDao {
    
    MyUser findByUsername(String username);
}
