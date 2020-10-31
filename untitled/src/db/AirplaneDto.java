package db;

import entity.Airplane;
import entity.AirplaneType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class AirplaneDto {

    public static List<Airplane> findAll(){
        List<Airplane> airplanes = new ArrayList<>();
        String query = "SELECT * FROM airplanes";
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplanes.add(new Airplane(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        AirplaneTypeDto.findById(resultSet.getInt("airplane_type_id")),
                        resultSet.getInt("capacity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplanes;
    }

    public static Airplane findById(Integer id){
        Airplane airplane = null;
        String query = "SELECT * FROM airplanes WHERE id="+id;
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplane = new Airplane(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        AirplaneTypeDto.findById(resultSet.getInt("airplane_type_id")),
                        resultSet.getInt("capacity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplane;
    }

    public static Airplane findByName(String name){
        Airplane airplane = null;
        String query = "SELECT * FROM airplanes WHERE name='"+name+"'";
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplane = new Airplane(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        AirplaneTypeDto.findById(resultSet.getInt("airplane_type_id")),
                        resultSet.getInt("capacity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplane;
    }

}
