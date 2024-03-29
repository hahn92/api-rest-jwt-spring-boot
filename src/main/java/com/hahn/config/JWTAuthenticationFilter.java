package com.hahn.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
    	
    	try {
			// listado de perdimos de roles
    		List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority("ADMIN"));
			// parametros de autenticacion
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			// creacion de token
			return authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		        	username,
					password,
					roles
		        )
			);
	    }
    	catch (Exception e) {
    		System.out.print(e);
    		return null;
		}
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
    	String token = JWT.create()
                .withIssuer(ConfigConstants.ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + ConfigConstants.EXPIRATION_TIME))
                //.withSubject(((Usuario) auth.getPrincipal()).getUsername())
                .withSubject(req.getParameter("username"))
                .sign(Algorithm.HMAC512(ConfigConstants.SECRET.getBytes()));
        res.addHeader(ConfigConstants.HEADER_STRING, ConfigConstants.TOKEN_PREFIX + token);
        
        res.getWriter().write(token);
        res.getWriter().flush();
    }
    
}