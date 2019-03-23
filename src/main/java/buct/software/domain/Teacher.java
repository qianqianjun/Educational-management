package buct.software.domain;

/**
 * @author 高谦
 * 教师实体类
 */
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

    public Teacher() {
    }

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

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
