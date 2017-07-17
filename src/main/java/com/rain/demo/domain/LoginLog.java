package com.rain.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rain on 17-7-16.
 */
public class LoginLog implements Serializable {
    private int loginLogId;

    private int userId;

    private String ip;

    private Date loginDate;

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLongDate() {
        return longDate;
    }

    public void setLongDate(Date longDate) {
        this.longDate = longDate;
    }

    private Date longDate;

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
