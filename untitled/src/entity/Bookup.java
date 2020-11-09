package entity;

public class Bookup {

    private Trip trip;

    private Passenger passenger;

    public Bookup(Trip trip, Passenger passenger) {
        this.trip = trip;
        this.passenger = passenger;
    }

    public Bookup() {

    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
