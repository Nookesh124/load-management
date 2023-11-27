package management.load.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    private String createToken(Map<String,Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*2))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    public static  final String SECRET = "uZ/O5hSdqkqMSFOb+V1ziiwKz3cGqCAxLlmhnIK45DHCdT71/W6rNG5m5Dnbf1h+8a2qxNYxTG2btYAsJYIE3zbKq2COjFqanFtNUQR17ATRXk2Yjsl+p4k3YgtphXUmhOv9OwXHHSDk/QQBta9mztJvQ9HySch10cwzYvJ3jlaisLpAiYYw9SuMltgWZG3wCpZKVcT0UjHrM63DNXxpSbIQ/greaE3JI5fvoVCOx16CI+R/A3yTCzcWZejv5ccFIkAjCLRYDxRZRBnHLljb8JKFSA9t5vdiVUypf5+McOASA4cTr5Dn1pHIO6n9vkI7EHUHSqJ0RPpyYynymI1UGldg95uVotEFO73I700ziE8=\n";


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
