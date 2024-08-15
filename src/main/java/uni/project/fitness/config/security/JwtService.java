//package uni.project.fitness.config.security;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//import uni.project.fitness.entity.UserEntity;
//
//
//import java.util.Date;
//import java.util.Map;
//
//@Service
//public class JwtService {
//
//    @Value("${jwt.access-expiry}")
//    private Integer accessExpiry;
//
//    @Value("${jwt.refresh-expiry}")
//    private Integer refreshExpiry;
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    public String generateAccessToken(UserEntity user) {
//        Date iat = new Date();
//        return Jwts.builder()
//                .setSubject(user.getId().toString())
//                .setIssuedAt(iat)
//                .setExpiration(new Date(iat.getTime() + accessExpiry))
//                .addClaims(getAuthorities(user))
//                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String generateRefreshToken(UserEntity user) {
//        Date iat = new Date();
//        return Jwts.builder()
//                .setSubject(user.getId().toString())
//                .setIssuedAt(iat)
//                .setExpiration(new Date(iat.getTime() + refreshExpiry))
//                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public Jws<Claims> extractToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
//                .build()
//                .parseClaimsJws(token);
//    }
//
//    public Map<String, Object> getAuthorities(UserEntity user) {
//        return Map.of("roles",
//                user.getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .toList());
//    }
//}
