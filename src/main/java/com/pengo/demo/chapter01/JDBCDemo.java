package com.pengo.demo.chapter01;

import java.sql.*;

/**
 * @author Benpeng
 * @date 2023/3/19
 */
public class JDBCDemo {
    public static void main(String[] args) {
        JDBCDemo jdbcDemo = new JDBCDemo();
        Role role = jdbcDemo.getRole(1L);
        System.out.println(role);
    }

    Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mybatis?serverTimezone=Asia/Shanghai";
            String username = "root";
            String password = "rootroot";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    Role getRole(Long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("select id,role_name,note from t_role where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long roleId = resultSet.getLong("id");
                String roleName = resultSet.getString("role_name");
                String note = resultSet.getString("note");
                Role role = new Role(roleId, roleName, note);
                role.setId(roleId);
                role.setRoleName(roleName);
                role.setNote(note);
                return role;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {

                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
