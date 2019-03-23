package buct.software.domain;

/**
 * @author 高谦
 * 学生实体类
 */
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

    public Student() {}

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getComeYear() {
        return comeYear;
    }

    public void setComeYear(String comeYear) {
        this.comeYear = comeYear;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
