package buct.software.domain;

import lombok.Data;


/**
 * @author 高谦
 * 排课表实体类
 * 修改了这里的 cno 和 tno 的类型。
 */
@Data
public class Scheduling {
    private Integer semesterId;

    private Integer cno;
    private Integer tno;

    private String status;
    private Integer capacity;
    private String address;
    private String courseTime;
    // 分数占比，成绩管理系统系统设置值，排课系统不用 care
    private String percent;
    private String majorGrade;

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }


    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setMajorGrade(String majorGrade) {
        this.majorGrade = majorGrade;
    }

    public Integer getSemesterId() {
        return semesterId;

    }

    public Integer getCno() {
        return cno;
    }


    public Integer getTno() {
        return tno;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getAddress() {
        return address;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public String getPercent() {
        return percent;
    }

    public String getMajorGrade() {
        return majorGrade;
    }


    public Scheduling(Integer semesterId, Integer cno,
                      Integer tno, String status,

                      Integer capacity, String address,
                      String courseTime, String percent,
                      String majorGrade) {
        if(semesterId !=-1)
            this.semesterId = semesterId;
        else
            this.semesterId = null;
        this.cno = cno;
        this.tno = tno;
        this.status = status;
        if(capacity!=-1)
            this.capacity = capacity;
        else
            this.capacity = null;
        this.address = address;
        this.courseTime = courseTime;
        this.percent = percent;
        this.majorGrade = majorGrade;
    }

    public Scheduling(){}
}
