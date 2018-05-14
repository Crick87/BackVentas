package com.pythonteam.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface FireDao {

    @SqlUpdate("INSERT INTO tokens(token) VALUES (:token);")
    int add(@Bind("token") String token);

}
