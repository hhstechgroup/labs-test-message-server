package com.engagepoint.university.messaging;

/**
 * Created with IntelliJ IDEA.
 * User: Toronaga
 * Date: 31.01.14
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class Car {

    int id;
    String to;
    String  from;
    String  subject;
    String text;

    public Car( int id,String to,String from, String subject, String text) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}

