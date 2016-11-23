package com.tj.tema5.filters

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
class AuthenticationFilter extends HttpFilter {
    @Override
    void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest.getRequestURI() == "/login.xhtml" ||
            servletRequest.getRequestURI() == "/" ||
            servletRequest.getRequestURI() == "/javax.faces.resource/main.css.xhtml" ||
            servletRequest.getRequestURI() == "/javax.faces.resource/jquery-3.1.1.min.js.xhtml" ||
            servletRequest.getRequestURI() == "/javax.faces.resource/js/bootstrap.min.js.xhtml" ||
            servletRequest.getRequestURI() == "/javax.faces.resource/css/bootstrap.min.css.xhtml") {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (servletRequest.getSession().getAttribute("authenticationBean")?.userName) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.sendRedirect("/login.xhtml");
        }
    }
}
