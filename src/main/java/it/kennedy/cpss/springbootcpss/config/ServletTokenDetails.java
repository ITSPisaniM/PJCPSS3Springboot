package it.kennedy.cpss.springbootcpss.config;

import org.apache.catalina.Server;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class ServletTokenDetails implements Serializable {

    public HttpServletRequest request;
    public String token;

    public ServletTokenDetails(ServletTokenSource source) {
        this.request = source.request;
        this.token = source.token;
    }
}
