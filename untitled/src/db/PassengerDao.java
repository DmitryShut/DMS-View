package db;

import entity.Passenger;
import entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class PassengerDao {

    public static Passenger findById(int id) {
        String query = "SELECT * FROM passenger_information WHERE id="+id;
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                return new Passenger(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Passenger> findAll() {
        List<Passenger> passengers = new ArrayList<>();
        String query = "SELECT * FROM passenger_information";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                passengers.add(new Passenger(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("phone")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public static void add(Passenger passenger) {
        String query = "INSERT INTO passenger_information (name, phone) VALUES (?,?)";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, passenger.getName());
            st.setString(2, passenger.getPhone());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Passenger passenger) {
        String query = "UPDATE passenger_information SET name=?, phone=? WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, passenger.getName());
            st.setString(2, passenger.getPhone());
            st.setInt(3, passenger.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM passenger_information WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
