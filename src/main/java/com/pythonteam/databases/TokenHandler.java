package com.pythonteam.databases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pythonteam.models.User;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TokenHandler implements BaseHandler<User,String>{

    private static final long ttl = TimeUnit.DAYS.toMillis(48);
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(String id) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("elcristian")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(id);
            final int userId = jwt.getClaim("userid").asInt();
            User user = new UserHandler().findOne(userId);
            return user ;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
    @Override
    public User update(User token) {
        return null;
    }

    @Override
    public User create(User user) throws SQLException {
        user = new UserHandler().checkPass(user);
        if (user != null)
        {
            final long nowMills = System.currentTimeMillis();
            final long expTime = nowMills + ttl;
            Algorithm algorithm = null;
            try {
                algorithm = Algorithm.HMAC256("secret");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String token = JWT.create()
                    .withExpiresAt(new Date(expTime))
                    .withClaim("userid", user.getId())
                    .withIssuer("elcristian")
                    .sign(algorithm);
            user.setToken(token);
            return user;
        }
        throw new SQLException("eres p");
    }

}
