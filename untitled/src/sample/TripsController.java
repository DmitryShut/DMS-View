package sample;

import db.AirplaneDao;
import db.DestinationDao;
import db.TripDao;
import entity.Airplane;
import entity.Destination;
import entity.Trip;
import entity.converters.AirplaneStringConverter;
import entity.converters.DestinationStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class TripsController implements Initializable {

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;

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

    @FXML
    private void insertButton() {
        Trip trip = tableView.getItems().get(tableView.getItems().size() - 1);
        TripDao.add(trip);
        updateView();
    }

    @FXML
    private void deleteButton() {
        int idToDelete = tableView.getSelectionModel().getSelectedItem().getId();
        TripDao.delete(idToDelete);
        updateView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        updateView();
    }

    private void configureTable() {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        airplaneColumn.setCellValueFactory(new PropertyValueFactory<>("airplane"));
        airplaneColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new AirplaneStringConverter(), FXCollections.observableArrayList(AirplaneDao.findAll())));
        airplaneColumn.setOnEditCommit(e -> {
            if (e.getTableView().getItems().size() != e.getTablePosition().getRow()+1) {
                Trip trip = e.getTableView().getItems().get(e.getTablePosition().getRow());
                trip.setAirplane(e.getNewValue());
                TripDao.update(trip);
                updateView();
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAirplane(e.getNewValue());
            }
        });

        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        destinationColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DestinationStringConverter(), FXCollections.observableArrayList(DestinationDao.findAll())));
        destinationColumn.setOnEditCommit(e -> {
            if (e.getTableView().getItems().size() != e.getTablePosition().getRow()+1) {
                Trip trip = e.getTableView().getItems().get(e.getTablePosition().getRow());
                trip.setDestination(e.getNewValue());
                TripDao.update(trip);
                updateView();
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setDestination(e.getNewValue());
            }
        });

        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        arrivalColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DestinationStringConverter(), FXCollections.observableArrayList(DestinationDao.findAll())));
        arrivalColumn.setOnEditCommit(e -> {
            if (e.getTableView().getItems().size() != e.getTablePosition().getRow()+1) {
                Trip trip = e.getTableView().getItems().get(e.getTablePosition().getRow());
                trip.setArrival(e.getNewValue());
                TripDao.update(trip);
                updateView();
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setArrival(e.getNewValue());
            }
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(e -> {
            if (e.getTableView().getItems().size() != e.getTablePosition().getRow()+1) {
                Trip trip = e.getTableView().getItems().get(e.getTablePosition().getRow());
                trip.setDate(e.getNewValue());
                TripDao.update(trip);
                updateView();
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate(e.getNewValue());
            }
        });

        tableView.setEditable(true);
    }

    public void showTrips() {
        List<Trip> trips = TripDao.findAll();
        trips.add(new Trip(trips.stream().max(Comparator.comparingInt(Trip::getId)).get().getId() + 1));
        ObservableList<Trip> list = FXCollections.observableArrayList(trips);
        tableView.setItems(list);
    }

    public void updateView() {
        showTrips();
    }

}
