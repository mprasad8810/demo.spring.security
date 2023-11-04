package com.example.spring.security.demo.spring.security.service.impl;

import com.example.spring.security.demo.spring.security.entity.User;
import com.example.spring.security.demo.spring.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private final String sectretkey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";


    public String username(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }
    public String generatedJwt(UserDetails userDetails){
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ (1000*60)))
                .signWith(getSignInKey(), SignatureAlgorithm.ES256)
                .compact();
    }

    public String generatedRefreshJwt(Map<String, Object> extractclaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extractclaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60)))
                .signWith(getSignInKey(), SignatureAlgorithm.ES256)
                .compact();
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllCliams(jwt);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllCliams(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
    }

    private Key getSignInKey() {
        byte[] keys = Decoders.BASE64.decode(sectretkey);
        return Keys.hmacShaKeyFor(keys);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = username(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractClaim(jwt, Claims::getExpiration).before(new Date());
    }
}
