package sample;

import db.AirplaneDto;
import db.DestinationDto;
import db.TripDto;
import entity.Airplane;
import entity.Destination;
import entity.Trip;
import entity.converters.AirplaneStringConverter;
import entity.converters.DestinationStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private ComboBox<Integer> idField;

    @FXML
    private ComboBox<Airplane> airplaneField;

    @FXML
    private ComboBox<Destination> destinationField;

    @FXML
    private ComboBox<Destination> arrivalField;

    @FXML
    private TextField dateField;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

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
        TripDto.add(new Trip(airplaneField.getValue(), dateField.getText(), destinationField.getValue(), arrivalField.getValue()));
        updateView();
    }


    @FXML
    private void updateButton() {
        TripDto.add(new Trip(idField.getValue(), airplaneField.getValue(), dateField.getText(), destinationField.getValue(), arrivalField.getValue()));
        updateView();
    }

    @FXML
    private void deleteButton() {
        TripDto.delete(idField.getValue());
        updateView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        airplaneColumn.setCellValueFactory(new PropertyValueFactory<>("airplane"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        airplaneColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new AirplaneStringConverter(), FXCollections.observableArrayList(AirplaneDto.findAll())));
        destinationColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DestinationStringConverter(), FXCollections.observableArrayList(DestinationDto.findAll())));
        arrivalColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DestinationStringConverter(), FXCollections.observableArrayList(DestinationDto.findAll())));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.setEditable(true);
        updateView();
    }

    private void showArrivals() {
        ObservableList<Destination> list = FXCollections.observableArrayList(DestinationDto.findAll());
        arrivalField.setItems(list);
    }

    private void showDestinations() {
        ObservableList<Destination> list = FXCollections.observableArrayList(DestinationDto.findAll());
        destinationField.setItems(list);
    }

    private void showAirplanes() {
        ObservableList<Airplane> list = FXCollections.observableArrayList(AirplaneDto.findAll());
        airplaneField.setItems(list);
    }

    private void showIds() {
        ObservableList<Trip> list = FXCollections.observableArrayList(TripDto.findAll());
        idField.setItems(FXCollections.observableList(list.stream().map(Trip::getId).collect(Collectors.toList())));
    }

    public void showTrips() {
        TripDto.findAll();
        ObservableList<Trip> list = FXCollections.observableArrayList();
        tableView.setItems(list);
    }

    public void updateView() {
        showTrips();
        showIds();
        showAirplanes();
        showDestinations();
        showArrivals();
    }

}
