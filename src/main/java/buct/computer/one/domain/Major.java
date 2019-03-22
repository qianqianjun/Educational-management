package buct.computer.one.domain;

/**
 * @author  高谦
 * 数据库专业实体
 */
public class Major {
    private String major;
    private String college;

    public Major(String major, String college) {
        this.major = major;
        this.college = college;
    }
    public Major(){}

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
