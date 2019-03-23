package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 学生实体类
 */
@Data
public class Student {
    private String sno;
    private String password;
    private String sname;
    private String sex;
    private String major;
    // 班级：
    private String klass;
    private String comeYear;
    private String phone;

    public Student(String sno, String password,
                   String sname, String sex, String major,
                   String klass, String comeYear, String phone) {
        this.sno = sno;
        this.password = password;
        this.sname = sname;
        this.sex = sex;
        this.major = major;
        this.klass = klass;
        this.comeYear = comeYear;
        this.phone = phone;
    }
    public Student(){}

}
