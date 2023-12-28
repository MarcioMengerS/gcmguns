package br.com.gcm.sac.setor_armamento.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gcm.sac.setor_armamento.exceptions.TokenInvalidException;
import br.com.gcm.sac.setor_armamento.repository.UserRepository;
import br.com.gcm.sac.setor_armamento.service.TokenService;

@Component
public class FilterToken extends OncePerRequestFilter{

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{                                
            String token;
            var authorizationHeader = request.getHeader("Authorization");
            
            if(authorizationHeader != null){
                token = authorizationHeader.replace("Bearer ", "");
                var subject = this.tokenService.getSubject(token);

                var usuario = this.userRepository.findByUsername(subject);

                var authentication = new UsernamePasswordAuthenticationToken(usuario,
                    null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
            
        }catch(TokenInvalidException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(e.getLocalizedMessage());
        }
    }
}
