package buct.software.domain;
import lombok.Data;
/**
 * @author  高谦
 * 登录的用户表，用于存储教师登录的工号，学生登录的学号
 * 标识用户的类别（学生，老师）
 */
@Data
public class User {
    /**
     * 下面是数据表中的字段
     */
    private Integer account;
    private String password;
    private Integer type;
    private Integer status;

    /**
     * 下面是额外可能用到的字段，用于保存更多的信息在session 中
     */

    private String sname;
    private String major;
    private Integer majorid;
    private String grade;
    private String tname;

    private String college;
    private String collegeId;
    private String klass;

    public User(Integer account, String password, Integer type, Integer status) {
        this.account = account;
        this.password = password;
        this.type = type;
        this.status = status;
    }
    public User(){}
}
