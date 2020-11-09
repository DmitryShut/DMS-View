package db;

import entity.AirplaneType;
import entity.Destination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class AirplaneTypeDao {

    public static AirplaneType findById(Integer id){
        AirplaneType airplaneType = null;
        String query = "SELECT * FROM airplane_type WHERE id="+id;
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplaneType = new AirplaneType(resultSet.getInt("id"),
                        resultSet.getString("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplaneType;
    }

    public static AirplaneType findByType(String type) {
        AirplaneType airplaneType = null;
        String query = "SELECT * FROM airplane_type WHERE type="+type;
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplaneType = new AirplaneType(resultSet.getInt("id"),
                        resultSet.getString("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplaneType;
    }

    public static List<AirplaneType> findAll() {
        List<AirplaneType> airplaneTypes = new ArrayList<>();
        String query = "SELECT * FROM airplane_type";
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplaneTypes.add(new AirplaneType(resultSet.getInt("id"),
                        resultSet.getString("type")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplaneTypes;
    }
}
