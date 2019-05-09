package com.mmall.common;

/**
 * @Author: zyx
 * @Date: 2019/5/9 14:38
 * @Version 1.0
 */
public class Const {
    public static final String CURRENT_USER="currentUser";

    public static final String EMAL="email";
    public static final String USERNAME="username";


    public interface Role{
        int ROLE_CUSTOMER=0;//普通用户
        int ROLE_ADMIN=1;//管理员
    }

}
