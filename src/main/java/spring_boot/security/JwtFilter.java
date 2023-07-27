package spring_boot.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring_boot.entity.UserDetail;
import spring_boot.exception.UnauthorizedException;
import spring_boot.service.UserDetailService;

public class JwtFilter extends OncePerRequestFilter {

	@Autowired
    private UserDetailService userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = JwtProvider.resolveToken(request);
			if (jwt != null && JwtProvider.validateAccessToken(jwt)) {
				
				Map<String, String> inforUser = JwtProvider.getInforUserJWT(jwt);
				String username = inforUser.get("username");
				
				 UserDetail userDetail = (UserDetail) userDetailService.loadUserByUsername(username);
				 if(userDetail != null) {
					 UsernamePasswordAuthenticationToken
                     authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
					 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authentication);
				 }
			
			}

		} catch (JwtException e) {
			throw new UnauthorizedException("Unauthorized");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
