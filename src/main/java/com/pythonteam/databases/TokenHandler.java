package com.pythonteam.databases;

import com.pythonteam.dao.TokenDao;
import com.pythonteam.models.Token;
import com.pythonteam.models.User;
import com.pythonteam.util.Hash;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TokenHandler implements BaseHandler<User,String>{
    private static final Key key = MacProvider.generateKey();
    private static final SignatureAlgorithm signatureAlgo = SignatureAlgorithm.HS512;
    private static final long ttl = TimeUnit.DAYS.toMillis(48);
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(String id) {
        try {

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(id).getBody();
            final int userId = Integer.parseInt(claims.getSubject());
            return new UserHandler().findOne(userId);
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
        if (new UserHandler().checkPass(user) != null)
        {
            final long nowMills = System.currentTimeMillis();
            final long expTime = nowMills + ttl;
            String token = Jwts.builder().setSubject(String.valueOf(user.getId()))
                    .signWith(signatureAlgo, key)
                    .setIssuedAt(new Date(nowMills))
                    .setExpiration(new Date(expTime))
                    .compact();
            user.setToken(token);
            return user;
        }
        throw new SQLException("eres p");
    }

}
