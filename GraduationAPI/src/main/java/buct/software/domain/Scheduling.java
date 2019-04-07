package buct.software.domain;

import lombok.Data;

/**
 * @author 高谦
 * 排课表实体类
 */
@Data
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
    public Scheduling(){}
}
