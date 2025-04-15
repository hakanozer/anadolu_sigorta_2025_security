package com.works.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        String[] freeUrls = {"/", "/about", "/contact"};
        boolean loginStatus = true;
        for (String freeUrl : freeUrls) {
            if (uri.equals(freeUrl)) {
                loginStatus = false;
                break;
            }
        }

        if (loginStatus) {
            // login gerekli
            Object loginObj = req.getSession().getAttribute("customer");
            if (loginObj != null) {
                filterChain.doFilter(req, res);
            }else {
                res.sendRedirect("/");
            }
        }else {
            // login gerekli değil
            filterChain.doFilter(req, res);
        }


    }

}
