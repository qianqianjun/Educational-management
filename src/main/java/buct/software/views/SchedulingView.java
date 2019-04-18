package buct.software.views;

import lombok.Data;

@Data
public class SchedulingView {
    private String tno;
    private String address;
    private String time;
    private String status;
    private Integer capacity;

    public SchedulingView(String tno, String address, String time, String status, Integer capacity) {
        this.tno = tno;
        this.address = address;
        this.time = time;
        this.status = status;
        this.capacity = capacity;
    }
}
