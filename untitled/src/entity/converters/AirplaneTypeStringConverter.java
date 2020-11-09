package entity.converters;

import db.AirplaneTypeDao;
import entity.AirplaneType;
import javafx.util.StringConverter;

public class AirplaneTypeStringConverter extends StringConverter<AirplaneType> {

    public AirplaneTypeStringConverter() {
    }

    public AirplaneType fromString(String type) {
        if (type == null) {
            return null;
        } else {
            type = type.trim();
            return type.length() < 1 ? null : AirplaneTypeDao.findByType(type);
        }
    }

    public String toString(AirplaneType airplane) {
        return airplane == null ? "" : airplane.toString();
    }
}
