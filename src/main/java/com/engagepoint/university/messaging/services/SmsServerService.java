package com.engagepoint.university.messaging.services;

/*
 * #%L
 * labs-test-message-server
 * %%
 * Copyright (C) 2012 - 2014 Cloudhopper by Twitter
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.engagepoint.university.messaging.smpp.ServerMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 13.02.14
 * Time: 10:23
 * To change this template use File | Settings | File Templates.
 */

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
