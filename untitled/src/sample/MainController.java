package sample;

import db.BookupDao;
import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.SneakyThrows;
import utils.Util;

import java.util.*;
import java.util.stream.Collectors;

public class MainController {
    @FXML
    private Button reportCustomersButton;

    @FXML
    private void reportCustomersButton() {
        Reporter.reportCustomers(reportList());
    }

    public List<Report> reportList() {
        List<Report> reports = new ArrayList<>();
        List<Bookup> all = BookupDao.findAll();
        Map<Passenger, List<Trip>> map = new HashMap<>();
        for (Bookup bookup : all) {
            Optional<Passenger> passenger = map.keySet().stream().filter(p -> p.getId() == bookup.getPassenger().getId()).findFirst();
            if (passenger.isEmpty()) {
                map.put(bookup.getPassenger(), new ArrayList<>() {{
                    add(bookup.getTrip());
                }});
            } else {
                map.get(passenger.get()).add(bookup.getTrip());
            }
        }
        map.forEach((p, t) -> reports.add(new Report(
                t.size(),
                p.getName(),
                t.stream().map(trip-> new SubReport(trip.getDate(), trip.getDestination().getCountry())).collect(Collectors.toList())
        )));
        return reports;
    }
}
