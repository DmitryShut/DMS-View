package entity;

import java.util.List;

public class Report {
    public Integer countOfTrips;
    public String passenger;
    public List<SubReport> subReports;

    public Report(Integer countOfTrips, String passenger) {
        this.countOfTrips = countOfTrips;
        this.passenger = passenger;
    }

    public Report(Integer countOfTrips, String passenger, List<SubReport> subReports) {
        this.countOfTrips = countOfTrips;
        this.passenger = passenger;
        this.subReports = subReports;
    }

    public Integer getCountOfTrips() {
        return countOfTrips;
    }

    public void setCountOfTrips(Integer countOfTrips) {
        this.countOfTrips = countOfTrips;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public List<SubReport> getSubReports() {
        return subReports;
    }

    public void setSubReports(List<SubReport> subReports) {
        this.subReports = subReports;
    }
}