/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
/**
 *
 * @author tiago
 */
public class RestAuthenticationFilter implements javax.servlet.Filter {

    public static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filter) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authCredentials = httpServletRequest
                    .getHeader(AUTHENTICATION_HEADER);
            
            AuthenticationService authenticationService = new AuthenticationService();

            boolean authenticationStatus = authenticationService
                    .authenticate(authCredentials);

            if (authenticationStatus) {
                filter.doFilter(request, response);
                response.setContentType("application/json");
            } else if (response instanceof HttpServletResponse) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Permissão negada verifique seu cpf ou senha");
                
                PrintWriter out = response.getWriter();
                out.println("SC_UNAUTHORIZED");
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
