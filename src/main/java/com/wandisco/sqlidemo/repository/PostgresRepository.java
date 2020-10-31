package com.wandisco.sqlidemo.repository;

import com.google.common.collect.Lists;
import com.wandisco.sqlidemo.config.DataSource;
import com.wandisco.sqlidemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class PostgresRepository implements UserRepository {

    @Autowired
    public PostgresRepository() {
    }

    @Override
    public User getUser(String id) {
        try (Connection conn = DataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                String sql = "SELECT * from sqli_demo.user_details WHERE user_id = " + id + ";";
                return mapRs(statement.executeQuery(sql));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findUsers(String search) {
        try (Connection conn = DataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                String sql = "SELECT * from sqli_demo.user_details WHERE first_name LIKE '" + search + ";";
                List<User> users = Lists.newArrayList();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    users.add(mapRs(rs));
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        try (Connection conn = DataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                String sql = "SELECT * from sqli_demo.user_details;";
                List<User> users = Lists.newArrayList();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    users.add(mapRs(rs));
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        int result = 0;
        try (Connection conn = DataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                String sql = "INSERT INTO sqli_demo.user_details(id, first_name, favourite_colour, favourite_animal) " +
                             "VALUES ('"+ user.getId() +"', '"+ user.getFirstName() +"', '"+ user.getFavouriteColour() +"', '"+ user.getFavouriteAnimal() +"');";
                result = statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result == 0;
    }

    private User mapRs(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getString("id"))
                .firstName(rs.getString("first_name"))
                .favouriteColour(rs.getString("favourite_colour"))
                .favouriteAnimal(rs.getString("favourite_animal"))
                .build();
    }
}
