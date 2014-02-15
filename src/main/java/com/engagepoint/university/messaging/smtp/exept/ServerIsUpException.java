package com.engagepoint.university.messaging.smtp.exept;

import org.subethamail.smtp.RejectException;

public class ServerIsUpException extends RejectException {
    int code;

    public ServerIsUpException()
    {
        super();
    }

    public ServerIsUpException(String message)
    {
        super(message);
    }

    public ServerIsUpException(int code, String message)
    {
        super(code, message);
    }
}
