package it.kennedy.cpss.springbootcpss.config;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class ServletTokenDetails implements Serializable {

    public HttpServletRequest request;
    public String token;

    public ServletTokenDetails(ServletTokenSource source) {
        this.request = source.request;
        this.token = source.token;
    }
}
