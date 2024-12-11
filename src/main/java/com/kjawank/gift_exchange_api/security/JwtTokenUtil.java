package com.kjawank.gift_exchange_api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "your-secret-key"; // Debes cambiar esto por una clave secreta más segura
    private static final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos

    // Método para generar un token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Método para obtener el nombre de usuario del token
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Método para verificar si el token ha expirado
    public static boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }
}

