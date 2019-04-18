package buct.software.views;

import lombok.Data;

/**
 * @author  高谦
 * 这里不是一个数据库的实体
 * 这是在具体的应用接口 api 中，由于多表连接查询所生成的 view
 * 这个包里面的所有类都是这个作用。
 * 这个类实现的是学生选课的view。
 */

@Data
public class SelectCourseView {

    //公共字段
    private Integer semesterId;
    private Integer cno;

    //课程表特有字段
    private String cname;
    private String college;
    private String description;
    private String courseStatus;
    // 排课表特有字段
    private Integer tno;
    private String status;
    private Integer capacity;
    private String address;
    private String courseTime;
    // 分数占比，成绩管理系统系统设置值，排课系统不用 care
    private String percent;
    private String majorGrade;

    //拆分时间；
    private Integer startWeek;
    private Integer endWeek;
    private String week;
    private Integer startStep;
    private Integer endStep;

    // 老师信息
    private String tname;

    // 专业信息
    private Integer majorId;
    
    // plan 表信息
    private Integer courseType;

    public SelectCourseView() {}
    public void setCourseTime(String time){
        this.courseTime=time;
        //"(1-4,5,1-3);(8-10,4,11-13)"
        try {
            String[] obj = time.split(";");
            String[] each=obj[0].split(",");
            if(each[1].equals("1")){
                this.week="一";
            }
            if(each[1].equals("2"))
                this.week="二";
            if(each[1].equals("3"))
                this.week="三";
            if(each[1].equals("4"))
                this.week="四";
            if(each[1].equals("5"))
                this.week="五";
            if(each[1].equals("6"))
                this.week="六";
            if(each[1].equals("7"))
                this.week="日";


            this.startStep=Integer.parseInt(each[2].split("-")[0]);
            String temp=each[2].split("-")[1];
            this.endStep=Integer.parseInt(temp.substring(0,temp.length()-1));
            temp=each[0].split("-")[0];
            this.startWeek=Integer.parseInt(temp.substring(1,temp.length()));
            this.endWeek=Integer.parseInt(each[0].split("-")[1]);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
