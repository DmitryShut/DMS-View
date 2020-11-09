package db;

import entity.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static db.DataSource.getConnection;

public class ReportDao {

    public static List<Report> tripsByPassengers() {
        List<Report> reports = new ArrayList<>();
        String query = "select count(*) as 'count', pi.name \n" +
                "from bookups join passenger_information pi on pi.id = bookups.passenger_id\n" +
                "group by pi.name;";
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                reports.add(new Report(resultSet.getInt("count"), resultSet.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

}
