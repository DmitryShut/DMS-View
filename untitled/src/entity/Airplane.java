package entity;

public class Airplane {

    private int id;

    private String name;

    private AirplaneType airplaneType;

    private int capacity;

    public Airplane(int id, String name, AirplaneType airplaneType, int capacity) {
        this.id = id;
        this.name = name;
        this.airplaneType = airplaneType;
        this.capacity = capacity;
    }

    public Airplane(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
