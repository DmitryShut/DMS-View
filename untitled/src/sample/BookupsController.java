package sample;

import db.BookupDao;
import db.PassengerDao;
import db.TripDao;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lombok.SneakyThrows;
import utils.Util;

import java.net.URL;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class BookupsController implements Initializable {

    @FXML
    public TextField dateFromFilter;

    @FXML
    public TextField dateToFilter;

    @FXML
    public TextField airplaneSearch;

    @FXML
    public ComboBox<Destination> destinationSearch;

    @FXML
    private Button insertPassengerButton;

    @FXML
    private Button updatePassengerButton;

    @FXML
    private Button deletePassengerButton;

    @FXML
    private Button insertBookupButton;
    @FXML
    private Button deleteBookupButton;

    @FXML
    private TextField numberField;

    @FXML
    private ComboBox<Trip> tripField;

    private ObservableList<Passenger> passengers;

    private ObservableList<Trip> trips;

    private Passenger passenger;

    private Integer number;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private javafx.scene.control.TableView<Trip> tableView;

    @FXML
    private TableColumn<Trip, Integer> idColumn;

    @FXML
    private TableColumn<Trip, Airplane> airplaneColumn;

    @FXML
    private TableColumn<Trip, Destination> destinationColumn;

    @FXML
    private TableColumn<Trip, Destination> arrivalColumn;

    @FXML
    private TableColumn<Trip, String> dateColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTable();
        numberField.setText("1");
        updateView();
        nameField.setEditable(false);
        nameField.setStyle("-fx-control-inner-background: #"+Paint.valueOf("A9A9A9").toString().substring(2));
        phoneField.setEditable(false);
        phoneField.setStyle("-fx-control-inner-background: #"+Paint.valueOf("A9A9A9").toString().substring(2));
        insertPassengerButton.setDisable(true);
        updatePassengerButton.setDisable(true);
    }

    private void configureTable() {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        airplaneColumn.setCellValueFactory(new PropertyValueFactory<>("airplane"));
        airplaneColumn.setComparator(Comparator.comparing(Airplane::getName));

        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        destinationColumn.setComparator(Comparator.comparing(Destination::getCountry));

        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        arrivalColumn.setComparator(Comparator.comparing(Destination::getCountry));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setComparator((String date1,String date2) -> {
            try {
                Date d1 = Util.DATE_FORMAT.parse(date1);
                Date d2 = Util.DATE_FORMAT.parse(date2);
                return d1.compareTo(d2);
            } catch (ParseException ignored) {
            }
            return 1;
        });
    }

    @FXML
    private void insertPassengerButton() {
        PassengerDao.add(new Passenger(nameField.getText(), phoneField.getText()));
        updateView();
    }


    @FXML
    private void updatePassengerButton() {
        PassengerDao.update(new Passenger(passenger.getId(), nameField.getText(), phoneField.getText()));
        updateView();
    }

    @FXML
    private void deletePassengerButton() {
        PassengerDao.delete(passenger.getId());
        numberField.setText(String.valueOf(Integer.parseInt(numberField.getText())+1));
        updateView();
    }

    private void updateView() {
        passengers = FXCollections.observableArrayList(PassengerDao.findAll());
        tripField.setItems(FXCollections.observableArrayList(TripDao.findAll()));
        this.passenger = passengers.get(Integer.parseInt(numberField.getText())-1);
        nameField.setText(this.passenger.getName());
        phoneField.setText(this.passenger.getPhone());
        updateTable();
        List<String> destinationNames = new ArrayList<>();
        List<Destination> destinationList = tableView.getItems().stream().map(Trip::getDestination).collect(Collectors.toList());
        for (Iterator<Destination> iterator = destinationList.iterator(); iterator.hasNext(); ) {
            Destination destination = iterator.next();
            if(!destinationNames.contains(destination.getCountry()))
                destinationNames.add(destination.getCountry());
            else
                iterator.remove();
        }
        destinationSearch.setItems(FXCollections.observableArrayList(destinationList.stream().filter(destination -> destinationNames.contains(destination.getCountry())).collect(Collectors.toList())));
    }

    private void updateTable() {
        trips = FXCollections.observableArrayList(BookupDao.findByPassengerId(Integer.parseInt(numberField.getText())).stream().map(Bookup::getTrip).collect(Collectors.toList()));
        tableView.setItems(FXCollections.observableArrayList(BookupDao.findByPassengerId(Integer.parseInt(numberField.getText())).stream().map(Bookup::getTrip).collect(Collectors.toList())));
    }

    @FXML
    public void insertBookupButton() {
        BookupDao.add(Integer.valueOf(tripField.getId()), Integer.parseInt(numberField.getText()));
        updateView();
    }

    @FXML
    public void deleteBookupButton() {
        Trip selectedItem = tableView.getSelectionModel().getSelectedItem();
        BookupDao.delete(Integer.parseInt(numberField.getText()), selectedItem.getId());
        updateView();
    }

    public void setEditable() {
        Paint gray = Paint.valueOf("A9A9A9");
        Paint white = Paint.valueOf("FFFFFF");
        Boolean value = nameField.editableProperty().getValue();
        if(value){
            nameField.setEditable(false);
            nameField.setStyle("-fx-control-inner-background: #"+gray.toString().substring(2));
            phoneField.setEditable(false);
            phoneField.setStyle("-fx-control-inner-background: #"+gray.toString().substring(2));
            insertPassengerButton.setDisable(true);
            updatePassengerButton.setDisable(true);
            nameField.setText(this.passenger.getName());
            phoneField.setText(this.passenger.getPhone());
        }else{
            nameField.setEditable(true);
            nameField.setStyle("-fx-control-inner-background: #"+white.toString().substring(2));
            phoneField.setEditable(true);
            phoneField.setStyle("-fx-control-inner-background: #"+white.toString().substring(2));
            insertPassengerButton.setDisable(false);
            updatePassengerButton.setDisable(false);
        }
    }

    public void toFirst() {
        numberField.setText("1");
        updateView();
    }

    public void toPrevious() {
        int i = Integer.parseInt(numberField.getText());
        if(i!=1)
            numberField.setText(String.valueOf(i-1));
        updateView();
    }

    public void toNext() {
        int i = Integer.parseInt(numberField.getText());
        if(i!=passengers.size())
            numberField.setText(String.valueOf(i+1));
        updateView();
    }

    public void toLast() {
        numberField.setText(String.valueOf(passengers.size()));
        updateView();
    }

    public void destinationSearch() {
        Destination selectedItem = destinationSearch.getSelectionModel().getSelectedItem();
        List<Trip> collect = trips.stream().filter(t -> t.getDestination().getCountry().equals(selectedItem.getCountry())).collect(Collectors.toList());
        tableView.setItems(FXCollections.observableArrayList(collect));
    }

    public void airplaneSearch() {
        List<Trip> collect = trips.stream().filter(t -> t.getAirplane().getName().contains(airplaneSearch.getText())).collect(Collectors.toList());
        tableView.setItems(FXCollections.observableArrayList(collect));
    }

    @SneakyThrows
    public void dateFilter() {
        Date dateFrom = null;
        String dateFromString = dateFromFilter.getText();
        if(dateFromString.length()!=0)
            dateFrom = Util.DATE_FORMAT.parse(dateFromString);
        Date dateTo = null;
        String dateToString = dateFromFilter.getText();
        if(dateToString.length()!=0)
            dateTo = Util.DATE_FORMAT.parse(dateToString);
        dateTo = Util.DATE_FORMAT.parse(dateToFilter.getText());
        List<Trip> dateTrips = new ArrayList<>();
        for (Trip trip : trips) {
            Date date = Util.DATE_FORMAT.parse(trip.getDate());
            if(Util.dateBetween(dateFrom, dateTo, date)){
                dateTrips.add(trip);
            }
        }
        tableView.setItems(FXCollections.observableArrayList(dateTrips));
    }

    public void resetFilters() {
        tableView.setItems(trips);
    }
}
