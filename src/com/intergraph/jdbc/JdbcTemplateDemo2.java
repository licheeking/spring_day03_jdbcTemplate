package com.intergraph.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.List;

public class JdbcTemplateDemo2 {


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

    //4 查询返回list
    @Test
    public void testList() {
//        设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_day03");
        dataSource.setUsername("root");
        dataSource.setPassword("laserjet");
//        创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        写sql语句，根据username查询
        String sql = "select * from user";
//        调用jdbcTemplate的方法实现
//        第二个参数是接口RowMapper，需要自己写类实现接口，自己来做数据封装
        List<User> list = jdbcTemplate.query(sql, new MyRowMapper());

        System.out.println(list);
    }



    //3 查询返回对象
    @Test
    public void testObject() {
//        设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_day03");
        dataSource.setUsername("root");
        dataSource.setPassword("laserjet");
//        创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        写sql语句，根据username查询
        String sql = "select * from user where username=?";
//        调用jdbcTemplate的方法实现
//        第二个参数是接口RowMapper，需要自己写类实现接口，自己来做数据封装
        User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), "mary");
        System.out.println(user);
    }

    class MyRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            //1 从结果集里面把数据拿到
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            //2 把得到数据封装到对象里面
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            return user;
        }
    }


    //2 jdbc实现代码
    @Test
    public void testJDBC() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //创建数据库连接
            conn = DriverManager.getConnection("jdbc:mysql:///spring_day03", "root", "laserjet");
            //编写sql语句
            String sql = "select * from user where username=?";
            //预编译sql
            psmt = conn.prepareStatement(sql);
            //设置参数值
            psmt.setString(1, "lucy");
            //执行sql
            rs = psmt.executeQuery();
            //遍历结果集
            while (rs.next()) {
                //得到返回结果集
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //1 查询表中有多少条记录
    @Test
    public void testCount() {
//        设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_day03");
        dataSource.setUsername("root");
        dataSource.setPassword("laserjet");
//        创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        调用方法得到记录数
        String sql = "select count(*) from user";
//        调用jdbcTemplate的方法
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

}
