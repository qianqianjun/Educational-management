package buct.computer.one.repository;

/**
 * @author  高谦
 * 专业数据表 major 数据库操作类
 */
public class MajorDao {
    private String major;
    private String college;

    public MajorDao(String major, String college) {
        this.major = major;
        this.college = college;
    }
    public MajorDao(){}

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
