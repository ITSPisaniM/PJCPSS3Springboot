package it.kennedy.cpss.springbootcpss.config;

import io.jsonwebtoken.*;
import it.kennedy.cpss.springbootcpss.dao.UtentiDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private static final String JWT_SECRET = "zdtlD3JK56m6wTTgsNFhqzjqP";

    public String generateAccessToken(UtentiDao user) {
        return Jwts.builder().setSubject(format("%s,%s", user.getUserId(), user.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000)) // 1 ora
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }

    public String getUserId(String token) {
        var claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        var claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        var claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException | ExpiredJwtException ex) {
            return false;
        }
    }

    public String renew(String token) {
        var user = new UtentiDao();
        user.setUsername(this.getUsername(token));
        user.setUserId(Integer.parseInt(this.getUserId(token)));

        var exp = this.getExpirationDate(token);

        if ((System.currentTimeMillis() - exp.getTime()) < 60000) {
            return this.generateAccessToken(user);
        }

        return null;
    }
}