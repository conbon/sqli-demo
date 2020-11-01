package com.wandisco.sqlidemo.respository;

import com.wandisco.sqlidemo.config.PGDataSource;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@FlywayTest
//@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
public class PostgresRepositoryTest {

    @Autowired
    private PGDataSource PGDataSource;

    @Test
    public void test() throws SQLException {
        Connection conn = PGDataSource.getConnection();
    }
}