package buct.software.views;
import lombok.Data;

@Data
public class GradeManagementView {
    //公共字段
    private Integer semesterId;
    private Integer cno;
    private Integer sno;
    private Integer tno;

    //课程表特有字段
    private String cname;
    private String college;
    private String percent;

    //选课表
    private String totalscore;
    private String detail;

    //planning表
    private Integer credit;

    //semester表
    private String start;
    private String end;
    private String semester;

}
