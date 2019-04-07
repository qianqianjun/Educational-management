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
}
