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

    //public TeaCourseView(){}

    public TeaCourseView(int semesterid,int cno,int capacity,String address,String courseTime,String courseName){
        this.semesterid = semesterid;
        this.cno = cno;
        this.courseName = courseName;
        this.capacity = capacity;
        this.address = address;
        this.courseTime = courseTime;
//        System.out.println(semesterid);
//        System.out.println(cno);
//        System.out.println(courseName);
//        System.out.println(capacity);
//        System.out.println(address);
//        System.out.println(courseTime);
    }
}