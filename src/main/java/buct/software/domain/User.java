package buct.software.domain;

import lombok.Data;

/**
 * @author  高谦
 * 登录的用户表，用于存储教师登录的工号，学生登录的学号
 * 标识用户的类别（学生，老师）
 */
@Data
public class User {
    private Integer account;
    private String password;
    private Integer type;
    private Integer status;

    public User(Integer account, String password, Integer type, Integer status) {
        this.account = account;
        this.password = password;
        this.type = type;
        this.status = status;
    }
    public User(){}
}
