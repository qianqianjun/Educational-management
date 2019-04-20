package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 数据库选课对应domain 类
 */
@Data
public class SelectCourse {
    private Integer semesterId;
    private Integer cno;
    private Integer sno;
    private Integer totalScore;
    private String detail;
    // 这个参数别管他，没用！但是也别删！
    private Integer addition;

    public SelectCourse(Integer semesterId, Integer cno, Integer sno, Integer grade, String detail) {
        this.semesterId = semesterId;
        this.cno = cno;
        this.sno = sno;
        this.totalScore = grade;
        this.detail = detail;
    }
    public SelectCourse(){}

}
