package db;

import entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class TripDao {

    public static List<Trip> findAll() {
        List<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM trips";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            Trip trip;
            while (resultSet.next()) {
                trip = new Trip(resultSet.getInt("id"), AirplaneDao.findById(resultSet.getInt("airplane_id")), resultSet.getString("date"), DestinationDao.findById(resultSet.getInt("destination_id")), DestinationDao.findById(resultSet.getInt("arrival_id")));
                trips.add(trip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trips;
    }

    public static void add(Trip trip) {
        String query = "INSERT INTO trips (airplane_id,date,destination_id,arrival_id) VALUES (?,CONVERT(DATETIME, ?),?,?)";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, trip.getAirplane().getId());
            st.setString(2, trip.getDate());
            st.setInt(3, trip.getDestination().getId());
            st.setInt(4, trip.getArrival().getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Trip trip) {
        String query = "UPDATE trips SET airplane_id=?,date=?,destination_id=?,arrival_id=? WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, trip.getAirplane().getId());
            st.setString(2, trip.getDate());
            st.setInt(3, trip.getDestination().getId());
            st.setInt(4, trip.getArrival().getId());
            st.setInt(5, trip.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM trips WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Trip findById(int id) {
        String query = "SELECT * FROM trips WHERE id="+id;
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                return new Trip(resultSet.getInt("id"), AirplaneDao.findById(resultSet.getInt("airplane_id")), resultSet.getString("date"), DestinationDao.findById(resultSet.getInt("destination_id")), DestinationDao.findById(resultSet.getInt("arrival_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
