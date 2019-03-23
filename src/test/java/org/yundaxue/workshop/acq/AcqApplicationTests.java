package org.yundaxue.workshop.acq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yundaxue.workshop.acq.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcqApplicationTests {

    @Autowired
    MailService mailService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void sendMail() {
        mailService.sendMail("01112026@wisedu.com", "123456");
    }
}
