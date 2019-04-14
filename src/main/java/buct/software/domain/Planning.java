package buct.software.domain;

/**
 * planning实体类
 */
public class Planning {
    private Integer semesterId;
    private String start;
    private String end;
    private String semester;

    public Planning(Integer semesterId, String start, String end, String semester) {
        this.semesterId = semesterId;
        this.start = start;
        this.end = end;
        this.semester = semester;
    }
}
