package db;

import entity.Airplane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class AirplaneDao {

    public static List<Airplane> findAll(){
        List<Airplane> airplanes = new ArrayList<>();
        String query = "SELECT * FROM airplanes";
        try(Connection connection = getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery()) {
            while(resultSet.next()) {
                airplanes.add(new Airplane(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        AirplaneTypeDao.findById(resultSet.getInt("airplane_type_id")),
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
                        AirplaneTypeDao.findById(resultSet.getInt("airplane_type_id")),
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
                        AirplaneTypeDao.findById(resultSet.getInt("airplane_type_id")),
                        resultSet.getInt("capacity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplane;
    }

    public static void add(Airplane airplane) {
        String query = "INSERT INTO airplanes (name,airplane_type_id,capacity) VALUES (?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, airplane.getName());
            st.setInt(2, airplane.getAirplaneType().getId());
            st.setInt(3, airplane.getCapacity());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Airplane airplane) {
        String query = "UPDATE airplanes SET name=?,airplane_type_id=?,capacity=? WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, airplane.getName());
            st.setInt(2, airplane.getAirplaneType().getId());
            st.setInt(3, airplane.getCapacity());
            st.setInt(4, airplane.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int idToDelete) {
        String query = "DELETE FROM airplanes WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, idToDelete);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
