package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.service.MailService;

/**
 * @author 耿志强
 * 2018/10/30
 * 20:41
 */

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    /*
      *@Description: 发送邮件
      *@param: to
      *@param: idCode
      *@return: void
      *@Author: GengZhiQiang
      *@date: 2018/11/1
     * */
    public void sendMail(String to,String idCode){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(to);
        mailMessage.setSubject("激活邮件");
        mailMessage.setText("验证码:"+idCode);
        javaMailSender.send(mailMessage);
    }

}
