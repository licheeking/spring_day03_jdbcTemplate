package com.intergraph.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateDemo1 {


    /**
     * QueryRunner runner = new QueryRuner(dataSource);
     * 返回对象
     * runner.query(sql, new BeanHandler<User>(User.class));
     *
     * 返回list集合
     * runner.query(sql, new BeanListHander<User>(User.class));
     *
     * 1 在dbutils时候，有接口ResultSetHandler
     * dbutils提供了针对不同返回结果实现类
     *
     * 2 jdbcTemplate实现查询，有接口RowMapper
     * jdbcTemplate针对这个接口没有提供实现类，得到不同的类型数据需要自己进行数据封装
     *
     */


//    1 添加操作
    @Test
    public void add() {
//        设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_day03");
        dataSource.setUsername("root");
        dataSource.setPassword("laserjet");
//        创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        调用jdbcTemplate对象里面的方法实现操作
        //创建sql语句
        String sql = "insert into user values(?,?)";
        int rows = jdbcTemplate.update(sql, "lucy", "250");
        System.out.println(rows);

    }

    // 2 修改操作
    @Test
    public void update() {
//        设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_day03");
        dataSource.setUsername("root");
        dataSource.setPassword("laserjet");
//        创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        调用jdbcTemplate对象里面的方法实现update操作
        //创建sql语句
        String sql = "UPDATE user SET password=? WHERE username=?";
        int rows = jdbcTemplate.update(sql, "1314", "lucy");
        System.out.println(rows);
    }

    //3 删除操作
    @Test
    public void delete() {
        //        设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_day03");
        dataSource.setUsername("root");
        dataSource.setPassword("laserjet");
//        创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        调用jdbcTemplate对象里面的方法实现删除操作
        //创建sql语句
        String sql = "DELETE FROM user WHERE username=?";
        int rows = jdbcTemplate.update(sql, "lucy");
        System.out.println(rows);
    }

}
