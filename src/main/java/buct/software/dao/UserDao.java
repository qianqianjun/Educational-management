package buct.software.dao;

import buct.software.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高谦
 * 用户 User 表 数据库操作类。
 */

@Repository
public interface UserDao {
    public User Login(User user);
}
