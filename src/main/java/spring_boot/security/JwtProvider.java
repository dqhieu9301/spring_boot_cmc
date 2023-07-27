package spring_boot.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import spring_boot.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

public class JwtProvider {
	private static final String JWT_SECRET_ACCESSTOKEN = "hieudq";
    private static final String JWT_SECRET_REFRESHTOKEN = "dqhieu";
    
    private static final long JWT_EXPIRATION_ACCESSTOKEN = 3600000L;
    private static final long JWT_EXPIRATION_REFRESHTOKEN = 604800000L;
    
    public static String resolveToken(HttpServletRequest req) {
    	String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
          }
          return null;

    }
    
    public static String generateAccessToken(int id, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_ACCESSTOKEN);
        return Jwts.builder()
                   .setSubject(id + " " + username)
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SignatureAlgorithm.HS512, JWT_SECRET_ACCESSTOKEN)
                   .compact();
    }
    
    public static String generateRefreshToken(int id, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_REFRESHTOKEN);
        return Jwts.builder()
                   .setSubject(id + username)
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SignatureAlgorithm.HS512, JWT_SECRET_REFRESHTOKEN)
                   .compact();
    }
    
    public static Map<String, String> getInforUserJWT(String token) {
    	Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET_ACCESSTOKEN)
                .parseClaimsJws(token)
                .getBody();
    	
    	String payload = claims.getSubject();
    	String[] splits = payload.split("\\s");
    	
    	Map<String, String> map = new HashMap<>();
    	map.put("id", splits[0]);
    	map.put("username", splits[1]);
    	
    	return map;
    }
    
    public static boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_ACCESSTOKEN).parseClaimsJws(token);
            return true;
          } catch (JwtException | IllegalArgumentException e) {
            throw new UnauthorizedException("Unauthorized");
          }
    }
}
