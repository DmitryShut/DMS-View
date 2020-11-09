package db;

import entity.Bookup;
import entity.Passenger;
import entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class BookupDao {

    public static List<Bookup> findAll() {
        List<Bookup> bookups = new ArrayList<>();
        String query = "SELECT * FROM bookups";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                bookups.add(new Bookup(TripDao.findById(resultSet.getInt("trip_id")),
                        PassengerDao.findById(resultSet.getInt("passenger_id"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookups;
    }

    public static List<Bookup> findByPassengerId(Integer passengerId) {
        List<Bookup> bookups = new ArrayList<>();
        String query = "SELECT * FROM bookups WHERE passenger_id=" + passengerId;
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                bookups.add(new Bookup(TripDao.findById(resultSet.getInt("trip_id")),
                        PassengerDao.findById(resultSet.getInt("passenger_id"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookups;
    }

    public static void add(Integer tripId, Integer passengerId) {
        String query = "INSERT INTO bookups (trip_id, passenger_id) VALUES (?,?)";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, tripId);
            st.setInt(2, passengerId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(Integer passengerId, Integer tripId) {
        String query = "DELETE FROM bookups WHERE trip_id=? AND passenger_id=?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, passengerId);
            st.setInt(2, tripId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
