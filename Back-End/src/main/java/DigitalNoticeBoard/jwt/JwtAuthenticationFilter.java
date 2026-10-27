package DigitalNoticeBoard.jwt;

import DigitalNoticeBoard.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JWTService jwtService;


    private final UserRepository userRepository;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       // todo: get the Header
        final String authHeader  = request.getHeader("Authorization");


          final String jwtToken;
          final String username;

         // todo: check if Authorization header is present and starts with "Bearer"

         if(authHeader == null || !authHeader.startsWith("Bearer ")){
             filterChain.doFilter(request,response);
             return;
         }

         //EXTRACT JWT token from header

        jwtToken  = authHeader.substring(7);
         username  = jwtService.extractUsername(jwtToken);
         // TODO:check if we have username and no authentication exists yet
         if(username != null  && SecurityContextHolder.getContext().getAuthentication() == null){
              //TODO: Get the User Details from db;
             var userDetails  = userRepository.findByUsername(username)
                     .orElseThrow(()-> new RuntimeException("User not  found"));

            // todo: Validate the token
             if(jwtService.isTokenValid(jwtToken,userDetails)){
                 List<SimpleGrantedAuthority> authorities = userDetails.getRoles()
                                                 .stream()
                                            .map(SimpleGrantedAuthority::new)
                         .collect(Collectors.toList());

                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                         userDetails,null,authorities
                 );

                 //Set Authentication details

                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                 //TODO: update Security context with authentication

                 SecurityContextHolder.getContext().setAuthentication(authToken);
             }
         }
         filterChain.doFilter(request,response);
    }

}
