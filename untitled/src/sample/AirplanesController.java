package sample;

import db.AirplaneDao;
import db.AirplaneTypeDao;
import entity.Airplane;
import entity.AirplaneType;
import entity.converters.AirplaneTypeStringConverter;
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
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class AirplanesController implements Initializable {

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;

    @FXML
    private javafx.scene.control.TableView<Airplane> tableView;

    @FXML
    private TableColumn<Airplane, Integer> idColumn;

    @FXML
    private TableColumn<Airplane, String> nameColumn;

    @FXML
    private TableColumn<Airplane, AirplaneType> airplaneTypeColumn;

    @FXML
    private TableColumn<Airplane, Integer> capacityColumn;

    @FXML
    private void insertButton() {
        Airplane trip = tableView.getItems().get(tableView.getItems().size() - 1);
        AirplaneDao.add(trip);
        updateView();
    }

    @FXML
    private void deleteButton() {
        int idToDelete = tableView.getSelectionModel().getSelectedItem().getId();
        AirplaneDao.delete(idToDelete);
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

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(e -> {
            if (e.getTableView().getItems().size() != e.getTablePosition().getRow()+1) {
                Airplane airplane = e.getTableView().getItems().get(e.getTablePosition().getRow());
                airplane.setName(e.getNewValue());
                AirplaneDao.update(airplane);
                updateView();
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
            }
        });

        airplaneTypeColumn.setCellValueFactory(new PropertyValueFactory<>("airplaneType"));
        airplaneTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new AirplaneTypeStringConverter(), FXCollections.observableArrayList(AirplaneTypeDao.findAll())));
        airplaneTypeColumn.setOnEditCommit(e -> {
            if (e.getTableView().getItems().size() != e.getTablePosition().getRow()+1) {
                Airplane airplane = e.getTableView().getItems().get(e.getTablePosition().getRow());
                airplane.setAirplaneType(e.getNewValue());
                AirplaneDao.update(airplane);
                updateView();
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAirplaneType(e.getNewValue());
            }
        });

        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        capacityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        capacityColumn.setOnEditCommit(e -> {
            if (e.getTableView().getItems().size() != e.getTablePosition().getRow()+1) {
                Airplane airplane = e.getTableView().getItems().get(e.getTablePosition().getRow());
                airplane.setCapacity(e.getNewValue());
                AirplaneDao.update(airplane);
                updateView();
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setCapacity(e.getNewValue());
            }
        });

        tableView.setEditable(true);
    }

    public void showAirplanes() {
        List<Airplane> trips = AirplaneDao.findAll();
        trips.add(new Airplane(trips.stream().max(Comparator.comparingInt(Airplane::getId)).get().getId() + 1));
        ObservableList<Airplane> list = FXCollections.observableArrayList(trips);
        tableView.setItems(list);
    }

    public void updateView() {
        showAirplanes();
    }
}
