package buct.software.utils;

import buct.software.dao.CourseDao;
import buct.software.domain.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高谦
 * 排课表时间字符串解析的类。
 * 检查检查有无冲突
 */
public class CourseDataUtil{
    private Integer startweek;
    private Integer endweek;
    private Integer week;
    private Integer startStep;
    private Integer endStep;

    /**
     * 构造函数，不过貌似这个构造函数没有什么卵用。
     * @param startweek  教学开始周
     * @param endweek  教学结束周
     * @param week  在星期几上课
     * @param startStep  从第几节课开始上
     * @param endStep  上到第几节课
     */
    public CourseDataUtil(Integer startweek,
                          Integer endweek, Integer week,
                          Integer startStep, Integer endStep) {
        this.startweek = startweek;
        this.endweek = endweek;
        this.week = week;
        this.startStep = startStep;
        this.endStep = endStep;
    }

    /**
     * 根据数据库中的字符串表达式来构造，这个函数就很方便了
     * @param timeStr  (1-4,5,11-13) 1-4 周的 周五 晚上 11-13 节上课
     */
    public CourseDataUtil(String timeStr){
        //(1-4,5,11-13)  ->  ["(1-4","5","11-13)"]
        String[] parts=timeStr.split(",");

        // "(1-4" -> ["(1","4"]
        String[] weeks=parts[0].split("-");
        this.startweek=Integer.parseInt(weeks[0].substring(1,weeks[0].length()));
        this.endweek=Integer.parseInt(weeks[1]);

        this.week=Integer.parseInt(parts[1]);

        // "11-13)" ->  ["11","13)"]
        String[] steps=parts[2].split("-");
        this.startStep=Integer.parseInt(steps[0]);
        this.endStep=Integer.parseInt(steps[1].substring(0,steps[1].length()-1));
    }

    /**
     * 检查两个时间是不是冲突的（有层叠就是冲突）
     * @param other
     * @return
     */
    public Boolean checkConflict(CourseDataUtil other){
        if(this.startweek> other.endweek || this.endweek<other.startweek){
            // 课程的起始周和结束周都没有重叠，那还冲突个毛
            return false;
        }
        if(this.week!=other.week)
            return false;
        if(this.startStep>other.endStep || this.endStep<other.startStep){
            // 上课时间没有重叠，冲突个毛啊
            return false;
        }
        return true;
    }

    /**
     * // "(1-4,5,1-3);(8-10,4,11-13)"  ->  ["(1-4,5,1-3)","(8-10,4,11-13)"]
     * 判断是不是冲突了！
     * @param courseTime
     * @param courseDataStrList  数据库中读到时间 字符串数组
     * @return
     */
    public static Boolean isConflict(String courseTime, List<String> courseDataStrList){

        // 将数据库中读到的数据 解析成为多个 courseDataUtil 对象，生成一个列表。
        List<CourseDataUtil> dataBaseTiems=new ArrayList<>();
        for(String coursetime:courseDataStrList){
            String[] times=coursetime.split(";");
            for(String time:times){
                dataBaseTiems.add(new CourseDataUtil(time));
            }
        }

        String[] times=courseTime.split(";");
        for(String time:times){
            CourseDataUtil other=new CourseDataUtil(time);
            for(CourseDataUtil data: dataBaseTiems){
                if(data.checkConflict(other)){
                    return true;
                }
            }
        }
        return false;
    }
}
