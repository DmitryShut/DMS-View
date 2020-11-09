package entity.converters;

import db.AirplaneDao;
import entity.Airplane;
import javafx.util.StringConverter;

public class AirplaneStringConverter extends StringConverter<Airplane> {

    public AirplaneStringConverter() {
    }

    public Airplane fromString(String name) {
        if (name == null) {
            return null;
        } else {
            name = name.trim();
            return name.length() < 1 ? null : AirplaneDao.findByName(name);
        }
    }

    public String toString(Airplane airplane) {
        return airplane == null ? "" : airplane.toString();
    }
}
