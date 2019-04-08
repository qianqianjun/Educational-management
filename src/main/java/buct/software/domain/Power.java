package buct.software.domain;

import lombok.Data;

/**
 * 学期权限控制相关
 * @author 孟庆强
 */
@Data
public class Power {
    private Integer selectCourse;
    private Integer score;
    private Integer abnormal;
}
