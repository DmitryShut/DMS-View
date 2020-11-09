package entity;

public class SubReport {

    public String date;
    public String destination;

    public SubReport(String date, String destination) {
        this.date = date;
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
