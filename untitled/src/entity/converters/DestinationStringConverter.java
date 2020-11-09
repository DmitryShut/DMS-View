package entity.converters;

import db.DestinationDao;
import entity.Destination;
import javafx.util.StringConverter;

public class DestinationStringConverter extends StringConverter<Destination> {

    public DestinationStringConverter() {
    }

    public Destination fromString(String name) {
        if (name == null) {
            return null;
        } else {
            name = name.trim();
            return name.length() < 1 ? null : DestinationDao.findByCountry(name);
        }
    }

    public String toString(Destination airplane) {
        return airplane == null ? "" : airplane.toString();
    }
}
