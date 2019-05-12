package buct.software.views;
import lombok.Data;

@Data
public class GradeReturnView {
    //公共字段
    private Integer cno;

    //课程表特有字段
    private String cname;
    private String college;

    //选课表
    private String totalscore;

    //planning表
    private Integer credit;

    //semester表
    private String start;
    private String semester;

}
