package com.vipac.authentication_service.configs;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.vipac.authentication_service.domains.User;
import com.vipac.authentication_service.services.CustomUserDetailService;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
    	

        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);
        //response.sendRedirect("/dashboard");
        String username = authentication.getName().replaceAll(" [^a-zA-Z_-]+ ", "");
        //response.sendRedirect("http://localhost:8082/getAll");
        response.sendRedirect("/lectures");

        /*for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ADMIN".equals(auth.getAuthority())) {
                response.sendRedirect("/dashboard");
            }
            else if ("USER".equals(auth.getAuthority())) {
            	response.sendRedirect("/lessons");
            }
        }*/
    }

}