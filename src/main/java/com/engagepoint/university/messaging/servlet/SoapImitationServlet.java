package com.engagepoint.university.messaging.servlet;

import com.engagepoint.university.messaging.controller.SoapImitationController;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class SoapImitationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OutputStream responseStream = resp.getOutputStream();
        Map<String, String> reqResps = getReqRespByURL(req);

        if (reqResps == null) {
            String message = "Error 404: Not Found! \nURL: " + req.getRequestURI() + " not FOUND!";
            responseStream.write(message.getBytes());
            return;
        }

        String request = IOUtils.toString(req.getInputStream());

        if (request.isEmpty()) {
            String message = "Error 400: Bad Request! \nRequest is Empty!!!";
            responseStream.write(message.getBytes());
            return;
        } else {
            String response = reqResps.get(request);
            if (response == null || response.isEmpty()) {
                responseStream.write(request.getBytes());
            } else {
                responseStream.write(response.getBytes());
            }
        }
    }

    private Map<String, String> getReqRespByURL(HttpServletRequest req) {
        Map<String, Map<String, String>> urls = SoapImitationController.getStaticMatchingReqResp();
        String[] urlPath = req.getRequestURI().split("/");
        for (int i = 0; i < urlPath.length; i++) {
            if (urlPath[i].equals("soap")) {
                if (urlPath.length > i + 1) {
                    return urls.get(urlPath[i + 1]);
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
