package fr.web.recipy.interceptor;

import fr.web.recipy.tools.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("======== inside Token Interceptor ========");
//		System.out.println("URI =" + request.getRequestURI());k
//		System.out.println("Header (authorization) :" + request.getHeader("Authorization"));
        if (!request.getMethod().equals("OPTIONS")) {
            if (!request.getRequestURI().equals("/authenticate")) {
                String headerAuth = request.getHeader("Authorization");
                if (headerAuth == null || headerAuth.trim().equals("") || headerAuth.length() < 7) {
                    throw new TokenException("Erreur : jeton absent ou invalide !");
                }

                String token = headerAuth.substring(7);
                // validation le token et extraire les infos
                if (jwtTokenUtil.isTokenExpired(token))
                    throw new TokenException("Erreur : jeton expiré !");

                String email = jwtTokenUtil.getUsernameFromToken(token);
                System.out.println("======== EMAIL : " + email);
                if (!TokenSaver.tokensByEmail.containsKey(email) || !TokenSaver.tokensByEmail.get(email).equals(token))
                    throw new TokenException("Erreur : jeton non reconnu !");

                // TODO autres extractions du jeton ou autres traitements

            }
        }
        return true;
    }

}

