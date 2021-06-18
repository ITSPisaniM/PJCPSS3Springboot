package it.kennedy.cpss.springbootcpss.Config;

import io.jsonwebtoken.*;
import it.kennedy.cpss.springbootcpss.dao.UtentiDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import static java.lang.String.format;


@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";

    public String generateAccessToken(UtentiDao user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getUserId(), user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000)) // 1 ora
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException | ExpiredJwtException ex) {
            return false;
        }
    }

    public String renew(String token){
        UtentiDao user = new UtentiDao();
        user.setUsername(this.getUsername(token));
        user.setUserId(Integer.parseInt(this.getUserId(token)));

        Date exp = this.getExpirationDate(token);

        if((System.currentTimeMillis() - exp.getTime() < 60000)){
            return this.generateAccessToken(user);
        }

        return null;
    }
}
