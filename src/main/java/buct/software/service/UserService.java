package buct.software.service;

import buct.software.dao.CollegeDao;
import buct.software.dao.UserDao;
import buct.software.domain.College;
import buct.software.domain.User;
import buct.software.utils.ResponseMessage;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  高谦
 * 数据表 user 服务层。
 * 以下代码还可以继续优化。
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    /**
     * 根据账户名和密码来调用数据库查询操作来实现登录
     * @param account  账户
     * @param password  密码
     * @return
     */
    public User LoginFun(String account,String password) {

        if(password==null||account==null)
            return null;
        User user=new User();
        user.setAccount(Integer.parseInt(account));
        user.setPassword(password);
        User Result=userDao.Login(user);
        if(Result!=null){
            return Result;
        }
        else{
            return null;
        }
    }
}
