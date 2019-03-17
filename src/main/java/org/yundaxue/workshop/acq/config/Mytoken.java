package org.yundaxue.workshop.acq.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author 耿志强
 * 2019/2/20
 * 14:07
 */
public class Mytoken extends  UsernamePasswordToken{
    private String loginType;

    public Mytoken(String username, String password, final String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
