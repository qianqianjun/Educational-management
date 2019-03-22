package buct.computer.one.domain;

/**
 * @author 高谦
 * 排课表实体类
 */
public class Scheduling {
    private String semester;
    private String cno;
    private String major;
    private String tno;
    private String status;
    private Integer capacity;
    private String address;
    private Integer startweek;
    private Integer endweek;

    public Scheduling(String semester, String cno,
                      String major, String tno, String status, Integer capacity,
                      String address, Integer startweek, Integer endweek) {
        this.semester = semester;
        this.cno = cno;
        this.major = major;
        this.tno = tno;
        this.status = status;
        this.capacity = capacity;
        this.address = address;
        this.startweek = startweek;
        this.endweek = endweek;
    }

    public Scheduling() {}

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

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStartweek() {
        return startweek;
    }

    public void setStartweek(Integer startweek) {
        this.startweek = startweek;
    }

    public Integer getEndweek() {
        return endweek;
    }

    public void setEndweek(Integer endweek) {
        this.endweek = endweek;
    }
}
