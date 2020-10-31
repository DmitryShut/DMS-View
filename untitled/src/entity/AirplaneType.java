package entity;

public class AirplaneType {

    private int id;

    private String type;

    @Override
    public String toString() {
        return type;
    }

    public AirplaneType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
