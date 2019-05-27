package buct.software.views;

import lombok.Data;

@Data
public class UserAddView {
    private Integer userAccount;
    private String userPassword;
    private Integer userType;
    private Integer userStatus;

}
