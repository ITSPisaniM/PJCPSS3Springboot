package it.kennedy.cpss.springbootcpss.config;

import org.springframework.security.authentication.AuthenticationDetailsSource;

public class ServletTokenDetailsSource implements AuthenticationDetailsSource<ServletTokenSource, ServletTokenDetails> {
    @Override
    public ServletTokenDetails buildDetails(ServletTokenSource context) {
        return new ServletTokenDetails(context);
    }
}
