package buct.software.views;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("TeaCourseView")
public class TeaCourseView{
    private int semesterid;
    private int cno;
    private String courseName;
    private int capacity;
    private String address;
    private String courseTime;

    public TeaCourseView(int semesterid,int cno,String courseName,int capacity,String address,String courseTime){
        this.semesterid = semesterid;
        this.cno = cno;
        this.courseName = courseName;
        this.capacity = capacity;
        this.address = address;
        this.courseTime = courseTime;
    }
}