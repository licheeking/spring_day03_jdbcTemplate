package com.intergraph.c3p0;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    //得到jdbcTemplate对象
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add() {
        String sql = "insert into user values(?,?)";
        jdbcTemplate.update(sql, "李雷", "520");

    }
}
