package com.pythonteam.dao;

import com.pythonteam.models.Token;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.ArrayList;

public interface TokenDao {

    @SqlQuery("SELECT * FROM tokens")
    @RegisterBeanMapper(Token.class)
    ArrayList<Token> list();

    @SqlQuery("select * from tokens where token = :token and userid = :userid")
    @RegisterBeanMapper(Token.class)
    Token findOne(@Bind("token") String token, @Bind("userid") int userid);

    @SqlUpdate("INSERT INTO tokens(token, userid ) VALUES (:token, :userid);")
    boolean create(@Bind("token") String token, @Bind("userid") int userid);


}
