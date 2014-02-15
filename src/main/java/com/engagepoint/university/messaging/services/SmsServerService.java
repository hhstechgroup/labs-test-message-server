package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.smpp.ServerMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
/*@ApplicationScoped*/
@Singleton
public class SmsServerService {
    private static final Logger logger = LoggerFactory.getLogger(SmsServerService.class);

    @Inject
    private ServerMain serverMain;
    public void startSmppServer(){
        logger.info("Try To START - startSmppServer");
        try{
            serverMain.startSmppServer();
            logger.info("START - startSmppServer");
        } catch (Exception ex){
            logger.info("Exception SMPP server..." + ex);
        }

    }
}
