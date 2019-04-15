package buct.software.domain;

import lombok.Data;

/**
 * @author  高谦
 * 数据库 schedule_major 数据表 domain 类
 */

@Data
public class ScheduleMajor {
    private Integer semesterId;
    private Integer cno;
    private Integer majorId;

    public ScheduleMajor(Integer semesterId, Integer cno, Integer majorId) {
        this.semesterId = semesterId;
        this.cno = cno;
        this.majorId = majorId;
    }

    public ScheduleMajor() {}
}
