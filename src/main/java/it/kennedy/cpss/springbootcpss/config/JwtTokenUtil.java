package it.kennedy.cpss.springbootcpss.config;

import static java.lang.String.format;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import it.kennedy.cpss.springbootcpss.dao.UtentiDao;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private static final String JWT_SECRET = "zdtlD3JK56m6wTTgsNFhqzjqP";

    public String generateAccessToken(UtentiDao user) {
        long expiration = 60000;

        if (user.getUsername().equals("admin")) {
            return Jwts.builder().setSubject(format("%s,%s", user.getUserId(), user.getUsername()))
                    .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
        } else {
            return Jwts.builder().setSubject(format("%s,%s", user.getUserId(), user.getUsername()))
                    .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expiration)) // 1 ora
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
        }
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

        if (this.getUsername(token).equals("admin")) {
            return null;
        }
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