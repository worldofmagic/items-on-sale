package com.rbc.itemsonsale.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    /**
     * AuthenticationEntryPoint.Commence(..) will be called when a user trying to hit the
     * secured endpoint wihtout login
     * @param request request from user
     * @param response response send to user
     * @param authException authentication exception
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
    }
}
