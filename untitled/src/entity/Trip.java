package entity;

public class Trip {

    private int id;

    private Airplane airplane;

    private String date;

    private Destination destination;

    private Destination arrival;

    public Trip(Airplane airplane, String date, Destination destination, Destination arrival_id) {
        this.airplane = airplane;
        this.date = date;
        this.destination = destination;
        this.arrival = arrival_id;
    }

    public Trip(int id, Airplane airplane, String date, Destination destination, Destination arrival_id) {
        this.id = id;
        this.airplane = airplane;
        this.date = date;
        this.destination = destination;
        this.arrival = arrival_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane_id) {
        this.airplane = airplane_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Destination getArrival() {
        return arrival;
    }

    public void setArrival(Destination arrival) {
        this.arrival = arrival;
    }
}
