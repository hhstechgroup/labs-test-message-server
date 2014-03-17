package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.jms.JMSProducer;
import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.service.repository.JmsService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class JmsController {

    @Inject
    private JmsService jmsService;

    @Inject
    private JMSProducer jmsProducer;

    private List<JmsDTO> jmsDTOList;

    public List<JmsDTO> getJmsDTOList() {
        return jmsDTOList;
    }

    @PostConstruct
    public void init() {
        jmsDTOList = new ArrayList<JmsDTO>();
        jmsDTOList = jmsService.getAll();
    }

    public void refreshEmail() {
        jmsDTOList = new ArrayList<JmsDTO>();
        jmsDTOList = jmsService.getAll();
    }

    public void addJMS() {
        jmsProducer.sendMessage();
    }

}
