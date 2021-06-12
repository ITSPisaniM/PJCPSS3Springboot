package it.kennedy.cpss.springbootcpss.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    @Value("${security.secret}")
    private String secret;

    @Value("${security.prefix}")
    private String prefix;

    public String createJwt() {
        return JWT.create()
                .withSubject("subject")
                .withIssuer("issuer")
                .withIssuedAt(DateTime.now().toDate())
                .withClaim("someClaim", "someClaimDesc")
                .withExpiresAt(DateTime.now().plusMonths(1).toDate())
                .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT decodeJwt(String jwt) {
        try {
            jwt = jwt.replace(prefix, "").trim();
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(jwt);
        } catch (Exception e) {
            return null;
        }
    }
}
