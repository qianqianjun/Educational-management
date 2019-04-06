package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 排课表实体类
 */
@Data
public class Scheduling {
    private Integer semesterId;
    private String cno;
    private String tno;
    private String status;
    private Integer capacity;
    private String address;
    private String courseTime;
    // 分数占比，成绩管理系统系统设置值，排课系统不用 care
    private String percent;
    private String majorGrade;

    public Scheduling(Integer semesterId, String cno,
                      String tno, String status,
                      Integer capacity, String address,
                      String courseTime, String percent,
                      String majorGrade) {
        this.semesterId = semesterId;
        this.cno = cno;
        this.tno = tno;
        this.status = status;
        this.capacity = capacity;
        this.address = address;
        this.courseTime = courseTime;
        this.percent = percent;
        this.majorGrade = majorGrade;
    }

    public Scheduling(){}
}
