package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 教师实体类
 */
@Data
public class Teacher {
    private Integer tno;
    private String tname;
    private String sex;
    private String phone;
    private String email;
    //所在学院
    private Integer collegeId;
    //办公室地址
    private String office;
    //职称：教授，副教授
    private String rank;

    public Teacher() {}

    public Teacher(Integer tno, String tname,
                   String sex, String phone,
                   String email, Integer collegeId,
                   String office, String rank) {
        this.tno = tno;
        this.tname = tname;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.collegeId = collegeId;
        this.office = office;
        this.rank = rank;
    }
}
