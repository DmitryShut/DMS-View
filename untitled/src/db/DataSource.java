package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl( "jdbc:sqlserver://DESKTOP-8NH6KA9;database=Air" );
        config.setUsername( "shut" );
        config.setPassword( "shut" );
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
