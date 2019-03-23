package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 教师实体类
 */
@Data
public class Teacher {
    private String tno;
    private String password;
    private String tname;
    private String sex;
    private String phone;
    private String email;
    //所在学院
    private String college;
    //办公室地址
    private String office;
    //职称：教授，副教授
    private String rank;

    public Teacher() {}

    public Teacher(String tno, String password, String tname,
                   String sex, String phone, String email,
                   String college, String office, String rank) {
        this.tno = tno;
        this.password = password;
        this.tname = tname;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.college = college;
        this.office = office;
        this.rank = rank;
    }

}
