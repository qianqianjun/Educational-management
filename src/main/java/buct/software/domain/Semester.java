package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 学期实体类
 */
@Data
public class Semester {
    private String start;
    private String end;
    private String semester;

    public Semester(String start, String end, String semester) {
        this.start = start;
        this.end = end;
        this.semester = semester;
    }

    public Semester() {}
}
