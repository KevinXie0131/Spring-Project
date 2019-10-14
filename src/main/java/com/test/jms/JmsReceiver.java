package com.test.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

    private final static Logger log = LoggerFactory.getLogger(JmsReceiver.class);

    @JmsListener(destination = "mailbox", containerFactory = "myJmsFactory")
    public void receiveMessage(Email email) {
        log.info("JmsReceived <" + email + ">");
    }

}
