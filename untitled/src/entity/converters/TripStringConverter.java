package entity.converters;

import db.AirplaneDao;
import db.TripDao;
import entity.Airplane;
import entity.Trip;
import javafx.util.StringConverter;

public class TripStringConverter extends StringConverter<Trip> {

    public TripStringConverter() {
    }

    public Trip fromString(String name) {
        if (name == null) {
            return null;
        } else {
            name = name.trim();
            return name.length() < 1 ? null : new Trip();
        }
    }

    public String toString(Trip trip) {
        return trip == null ? "" : trip.toString();
    }
}
