package com.buong.springwebservice.web.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (authentication == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            PrintWriter pw = httpServletResponse.getWriter();
            pw.write(userDetails.getUsername() + " logout success");
            pw.flush();
        }
    }
}
