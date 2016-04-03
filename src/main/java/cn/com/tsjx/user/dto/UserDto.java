package cn.com.tsjx.user.dto;

import cn.com.tsjx.user.entity.User;

public class UserDto extends User {
    /**
     * 
     */
    private static final long serialVersionUID = -7906260972542532381L;
    /**
     * 旧密码
     */
    private String oldPassword;
    
    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

     
}
