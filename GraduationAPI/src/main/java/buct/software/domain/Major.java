package buct.software.domain;

import lombok.Data;

/**
 * @author  高谦
 * 数据库专业实体
 */
@Data
public class Major {
    private String major;
    private String college;

    public Major(String major, String college) {
        this.major = major;
        this.college = college;
    }
    public Major(){}
}
