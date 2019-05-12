package buct.software.domain;
import lombok.Data;
/**
 * @author 高谦
 * 课程实体类
 */
@Data
public class Course {
    private Integer cno;
    private String cname;
    private String college;
    private String description;
    private String status;

    public Course(Integer cno, String cname, String college, String description, String status) {
        this.cno = cno;
        this.cname = cname;
        this.college = college;
        this.description = description;
        this.status = status;
    }
    public Course(){}
}
