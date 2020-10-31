package db;

import entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Function;

import static db.DataSource.getConnection;

public abstract class Dto {

    public <R> R execute(Function<ResultSet, R> function, String query){
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            return function.apply(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet execute(String query){
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
