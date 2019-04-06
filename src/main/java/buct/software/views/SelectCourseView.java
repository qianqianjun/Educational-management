package buct.software.views;

import lombok.Data;

/**
 * @author  高谦
 * 这里不是一个数据库的实体
 * 这是在具体的应用接口 api 中，由于多表连接查询所生成的 view
 * 这个包里面的所有类都是这个作用。
 *
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
    private String tno;
    private String status;
    private Integer capacity;
    private String address;
    private String courseTime;
    // 分数占比，成绩管理系统系统设置值，排课系统不用 care
    private String percent;
    private String majorGrade;

    // 老师信息
    private String tname;

    public SelectCourseView() {}
}
