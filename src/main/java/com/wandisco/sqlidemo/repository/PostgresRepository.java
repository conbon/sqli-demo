package com.wandisco.sqlidemo.repository;

import com.google.common.collect.Lists;
import com.wandisco.sqlidemo.config.PGDataSource;
import com.wandisco.sqlidemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class PostgresRepository implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresRepository.class);

    private PGDataSource PGDataSource;

    @Autowired
    public PostgresRepository(PGDataSource PGDataSource) {
        this.PGDataSource = PGDataSource;
    }

    @Override
    public User getUser(String id) {
        LOGGER.info("Getting user by id: {}", id);
        try (Connection conn = PGDataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                String sql = "SELECT * from sqli_demo.user_details WHERE user_id = " + id + ";";
                return mapRs(statement.executeQuery(sql));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL error: {}", e.getSQLState());
        }
        return null;
    }

    @Override
    public List<User> findUsers(String search) {
        LOGGER.info("Finding users by search term: {}", search);
        try (Connection conn = PGDataSource.getConnection()) {
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
            LOGGER.error("SQL error: {}", e.getSQLState());
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        LOGGER.info("Getting users");
        try (Connection conn = PGDataSource.getConnection()) {
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
            LOGGER.error("SQL error: {}", e.getSQLState());
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        LOGGER.info("Saving user: {}", user.getFirstName());

        int result = 0;
        try (Connection conn = PGDataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                String sql = "INSERT INTO sqli_demo.user_details(id, first_name, favourite_colour, favourite_animal) " +
                             "VALUES ('"+ user.getId() +"', '"+ user.getFirstName() +"', '"+ user.getFavouriteColour() +"', '"+ user.getFavouriteAnimal() +"');";
                result = statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL error: {}", e.getSQLState());
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
