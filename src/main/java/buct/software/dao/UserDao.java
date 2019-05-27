package buct.software.dao;

import buct.software.domain.User;
import buct.software.views.UserAddView;
import org.springframework.stereotype.Repository;

/**
 * @author 高谦
 * 用户 User 表 数据库操作类。
 * UserDao
 */

@Repository
public interface UserDao {
    public User Login(User user);
    void addUser(UserAddView user);
    void updateUserPassword(UserAddView user);
}
