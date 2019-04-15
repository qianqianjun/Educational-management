package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 学期实体类
 */
@Data
public class Semester {
    private Integer semesterId;
    private String start;
    private String end;
    private String semester;

    public Semester(Integer semesterId,String start, String end, String semester) {
        this.semesterId=semesterId;
        this.start = start;
        this.end = end;
        this.semester = semester;
    }

    public Semester() {}
    @Override
    public String toString(){
        return this.start+"-"+this.end+"学年第"+this.semester+"学期";
    }
}
