package db;

import entity.AirplaneType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static db.DataSource.getConnection;

public class AirplaneTypeDto {

    public static AirplaneType findById(Integer id){
        AirplaneType airplaneType = null;
        String query = "SELECT * FROM airplane_type WHERE id="+id;
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplaneType = new AirplaneType(resultSet.getInt("id"),
                        resultSet.getString("airplane_type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplaneType;
    }
    
}
