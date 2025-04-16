package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final InfoRepository infoRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String username = "";
        String roles = "";
        String sessionID = request.getSession().getId();
        String url = request.getRequestURI();
        Long time = System.currentTimeMillis();
        String agent = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            username = auth.getName();
            roles = auth.getAuthorities().toString();
        }

        Info info = new Info(username, roles, sessionID, url, time, agent, ip);
        infoRepository.save(info);

        filterChain.doFilter(request, response);
    }

}
