package buct.software.views;

import lombok.Data;

/**
 * @author 高谦
 * 首页显示的成绩栏目的 view
 */
@Data
public class StudentGradeIndexView {
    private Integer start;
    private Integer end;
    private Integer semester;
    private Integer cno;
    private String cname;
    private Integer totalScore;
    public StudentGradeIndexView(){}
}
