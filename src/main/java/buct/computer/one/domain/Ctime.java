package buct.computer.one.domain;

/**
 * @author 高谦
 * 数据库 上课时间实体
 */
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

    public Ctime() {
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorkday() {
        return workday;
    }

    public void setWorkday(String workday) {
        this.workday = workday;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
