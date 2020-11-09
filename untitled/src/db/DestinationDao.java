package db;

import entity.Destination;
import entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class DestinationDao {

    public static Destination findById(Integer id){
        Destination destination = null;
        String query = "SELECT * FROM destinations WHERE id="+id;
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                return new Destination(resultSet.getInt("id"), resultSet.getString("country"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destination;
    }

    public static Destination findByCountry(String country){
        Destination destination = null;
        String query = "SELECT * FROM destinations WHERE country='"+country+"'";
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                return new Destination(resultSet.getInt("id"), resultSet.getString("country"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destination;
    }

    public static List<Destination> findAll(){
        List<Destination> destinations = new ArrayList<>();
        String query = "SELECT * FROM destinations";
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                destinations.add(new Destination(resultSet.getInt("id"),
                        resultSet.getString("country")));
            }
        } catch (Exception e) {
        e.printStackTrace();
    }
        return destinations;
    }

}
