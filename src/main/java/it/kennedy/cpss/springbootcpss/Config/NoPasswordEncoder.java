package it.kennedy.cpss.springbootcpss.Config;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class NoPasswordEncoder implements PasswordEncoder {
    private static final PasswordEncoder INSTANCE = new NoPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }
}
