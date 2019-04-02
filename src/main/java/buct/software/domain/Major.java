package buct.software.domain;

import lombok.Data;

/**
 * @author  高谦
 * 数据库专业实体
 */
@Data
public class Major {
    private Integer majorId;
    private String majorName;
    private String college;

    public Major(Integer majorId,String majorName, String college) {
        this.majorId=majorId;
        this.majorName = majorName;
        this.college = college;
    }
    public Major(){}
}
