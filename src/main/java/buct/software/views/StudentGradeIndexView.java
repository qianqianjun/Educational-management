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
    private Integer type;
    private String typeStr;
    public StudentGradeIndexView(){}
    public void setType(Integer type1){
        if(type1==0)
            this.typeStr="专业必修";
        if(type1==1)
            this.typeStr="专业选修";
        if(type1==2)
            this.typeStr="通识教育";
        this.type=type1;
    }
}
