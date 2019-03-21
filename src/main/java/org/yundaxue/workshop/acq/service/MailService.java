package org.yundaxue.workshop.acq.service;

/**
 * Created by 耿志强 on 2019/3/21.
 */
public interface MailService {
    /*
     *@Description: 发送邮件
     *@param: to
     *@param: code
     *@return: void
     *@Author: GengZhiQiang
     *@date: 2018/11/1
    * */
    public void sendMail(String to,String code);

}
