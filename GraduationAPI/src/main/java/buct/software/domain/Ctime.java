package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 数据库 上课时间实体
 */
@Data
public class Ctime {
    private String semester;
    private String cno;
    private String major;
    private String workday;
    private Integer start;
    private Integer end;

    public Ctime(String semester, String cno, String major, String workday, Integer start, Integer end) {
        this.semester = semester;
        this.cno = cno;
        this.major = major;
        this.workday = workday;
        this.start = start;
        this.end = end;
    }
    public Ctime(){}
}
