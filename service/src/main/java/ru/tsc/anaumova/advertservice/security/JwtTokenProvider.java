package ru.tsc.anaumova.advertservice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.service.UserDetailsServiceImpl;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

import static java.util.stream.Collectors.joining;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;

    private final long validityInMilliseconds = 3600000;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtTokenProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createToken(Authentication authentication) {
        String username = authentication.getName();
        User user = this.userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        Long userId = user.getUserId();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("userId", userId);
        if (!authorities.isEmpty()) {
            claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(",")));
        }
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(this.secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Long getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("userId", Long.class);
    }

    public boolean validateTokenExpiration(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

}