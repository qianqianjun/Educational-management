package buct.software.domain;

/**
 * @author 高谦
 * 课程实体类
 */
public class Course {
    private String cno;
    private String cname;
    private String college;
    private String description;
    private String status;

    public Course(String cno, String cname, String college, String description, String status) {
        this.cno = cno;
        this.cname = cname;
        this.college = college;
        this.description = description;
        this.status = status;
    }
    public Course(){}

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
