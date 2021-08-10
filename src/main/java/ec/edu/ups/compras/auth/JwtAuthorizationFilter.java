package ec.edu.ups.compras.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader =  response.getHeader(TokenProvider.HEADER_STRING);
        if (!requiredAuthenticacion(authorizationHeader)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken =  null;
        if (TokenProvider.validarToken(authorizationHeader)) {
            authenticationToken = new UsernamePasswordAuthenticationToken(TokenProvider.obtenerUserName(authorizationHeader), TokenProvider.obtenerRoles(authorizationHeader));
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }


    protected boolean requiredAuthenticacion(String header){
        if(header == null || !header.startsWith(TokenProvider.TOKEN_PREFIX)) {
            return false;
        }
        return true;
    }
}
