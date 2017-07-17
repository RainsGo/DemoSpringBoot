package com.rain.demo.dao;

import com.rain.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rain on 17-7-16.
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private final static String SQL_MATCH_COUNT = " SELECT count(*) FROM t_user  " +
            " WHERE user_name =? and password=? ";

    private final static String SQL_UPDATE_LOGIN_INFO = " UPDATE t_user SET " +
            " last_visit=?,last_ip=?,credits=?  WHERE user_id =?";

    private final static String SQL_FIND_USER_BY_USERNAME = " SELECT user_id,user_name,credits "
            + " FROM t_user WHERE user_name =? ";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password){
        return jdbcTemplate.queryForObject(SQL_MATCH_COUNT, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(final String userName){
        final User user = new User();

        jdbcTemplate.query(SQL_FIND_USER_BY_USERNAME, new Object[]{userName},
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(SQL_UPDATE_LOGIN_INFO, new Object[]{user.getLastVisit()},
                user.getLastIp(), user.getCredits(), user.getUserId());
    }
}
