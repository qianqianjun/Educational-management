package buct.software.domain;

/**
 * @author 高谦
 * 学期实体类
 */
public class Semester {
    private String start;
    private String end;
    private String semester;

    public Semester(String start, String end, String semester) {
        this.start = start;
        this.end = end;
        this.semester = semester;
    }

    public Semester() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
